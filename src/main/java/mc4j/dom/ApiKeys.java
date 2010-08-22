package mc4j.dom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="", namespace="")
@XStreamAlias("")
public class ApiKeys implements Serializable {
	@XmlElement(name="")
	@XStreamImplicit(itemFieldName="")
	public Collection<ApiKey> apiKeys = new ArrayList<ApiKey>();

	public Collection<ApiKey> getApiKeys() {
		return apiKeys;
	}

	public void setApiKeys(Collection<ApiKey> apiKeys) {
		this.apiKeys = apiKeys;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("ApiKeys [apiKeys=");
		if (apiKeys == null) {
			sb.append("null");
		} else {
			for (ApiKey key : apiKeys) {
				sb.append("apikey: ").append(key.toString()).append("; ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
}