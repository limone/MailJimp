package mc4j.xml;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import mc4j.dom.MailChimpError;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Component
public class MailChimpParser implements Serializable {
	private transient final Logger log = LoggerFactory.getLogger(getClass());
	
	public MailChimpParser() {
		// empty
	}
	
	public Document convertToDocument(Response r) throws Exception {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			return dBuilder.parse((InputStream)r.getEntity());
		} catch (Exception ex) {
			log.error("Could not parse response.", ex);
			throw ex;
		}
	}
	
	public List<MailChimpError> checkErrors(Document doc) {
		NodeList errors = doc.getElementsByTagName("error");
		NodeList codes = doc.getElementsByTagName("code");
		if (errors == null || errors.getLength() == 0) {
			return null;
		}
		
		if (errors.getLength() < codes.getLength()) {
			log.warn("More errors than error codes?  That shouldn't be possible.");
			throw new RuntimeException("More errors than error codes.  Ruh roh.");
		}
		
		List<MailChimpError> mcErrors = new ArrayList<MailChimpError>();
		for (int i=0; i<errors.getLength(); i++) {
			Node e = errors.item(i);
			Node c = codes.item(i);
			
			MailChimpError mc = new MailChimpError(e.getTextContent(), Integer.getInteger(c.getTextContent()));
			mcErrors.add(mc);
		}
		return mcErrors;
	}
}