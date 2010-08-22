package mc4j.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mc4j.dom.ApiKey;
import mc4j.service.MailChimpException;

public class MailChimpParser {
	private transient final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public List<ApiKey> parseApiKeys(Object results) throws MailChimpException {
		List<ApiKey> keys = new ArrayList<ApiKey>();
		if (results instanceof Object[]) {
			for (Object o : (Object[])results) {
				if (o instanceof Map<?,?>) {
					@SuppressWarnings("unchecked")
					Map<String,String> m = (Map<String,String>)o;
					try {
						String apiKey = m.get("apikey");
						String createdAt = m.get("created_at");
						String expiredAt = m.get("expired_at");
						keys.add(new ApiKey(apiKey, sdf.parse(createdAt), (expiredAt == null || expiredAt.length() == 0) ? null : sdf.parse(expiredAt)));
					} catch (ParseException pe) {
						throw new MailChimpException("Could not parse date.", pe);
					}
				}
			}
		}
		return keys;
	}
}