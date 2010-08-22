package mc4j.service;

import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;

import javax.ws.rs.core.MediaType;

import mc4j.dom.ApiKey;
import mc4j.dom.ApiKeys;
import mc4j.dom.MailChimpError;
import mc4j.service.impl.MailChimpService;

import org.apache.cxf.jaxrs.provider.JSONProvider;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/spring-config.xml" })
public class TestMailChimpService {
	private transient final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private MailChimpService mSvc;

	@Test
	@Ignore
	public void testListApiKeys() {
		try {
			String content = mSvc.keyList();
			log.debug("List Content: {}", content);
		} catch (MailChimpException mce) {
			processError(mce);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
	}

	@Test
	@Ignore
	public void testKeyAdd() {
		try {
			String content = mSvc.keyAdd();
			log.debug("Add Content: {}", content);
		} catch (MailChimpException mce) {
			processError(mce);
		}
	}

	@Test
	@Ignore
	public void testKeyExpire() {
		try {
			Boolean content = mSvc.keyExpire();
			log.debug("Expire Content: {}", content);
		} catch (MailChimpException mce) {
			processError(mce);
		}
	}

	@Test
	@Ignore
	public void testGenerateJson() {
		JSONProvider jp = new JSONProvider();
		jp.setJaxbElementClassNames(Arrays.asList(new String[] { "ApiKeys", "ApiKey" }));
		ApiKeys keys = new ApiKeys();
		for (int i = 0; i < 5; i++) {
			keys.getApiKeys().add(new ApiKey(Integer.toString(i), new Date(), null));
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			jp.writeTo(keys, ApiKey.class, ApiKey.class, null, MediaType.APPLICATION_JSON_TYPE, null, baos);
			log.debug("JSON: {}", baos.toString());
		} catch (Exception ex) {
			log.error("Could not convert to JSON.", ex);
		}
	}

	@Test
	@Ignore
	public void testGenerateXstreamJson() {
		ApiKeys keys = new ApiKeys();
		for (int i = 0; i < 5; i++) {
			keys.getApiKeys().add(new ApiKey(Integer.toString(i), new Date(), null));
		}
		XStream xstream = new XStream(new JettisonMappedXmlDriver());
		xstream.autodetectAnnotations(true);
		xstream.setMode(XStream.NO_REFERENCES);
		log.debug("JSON: {}", xstream.toXML(keys));
	}

	@Test
	public void testXmlRpc() {
		try {
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setServerURL(new URL("http://api.mailchimp.com/1.2/"));
		XmlRpcClient client = new XmlRpcClient();
		client.setConfig(config);
		Object[] params = new Object[] { "mlaccetti", "Ea7myass2k3^^", "33a3cfd5dbdefff6c3d6ccb18f860682-us1", false};
		Object result = client.execute("apikeys", params);
		log.debug("Done");
		} catch (Exception ex) {
			log.error("Could not invoke XML RPC.", ex);
		}
	}

	private void processError(MailChimpException mce) {
		log.error("Exception while trying to process MailChimp call.");
		if (mce.getErrors() != null && mce.getErrors().size() > 0) {
			for (MailChimpError e : mce.getErrors()) {
				log.warn("Mail chimp error: {}", e.getError());
			}
		}
	}
}