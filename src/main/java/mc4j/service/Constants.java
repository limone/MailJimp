package mc4j.service;

import java.util.HashMap;
import java.util.Map;

public class Constants {
	private Map<String,Object> constants = new HashMap<String,Object>();

	public Map<String, Object> getConstants() {
		return constants;
	}

	public void setConstants(Map<String, Object> constants) {
		this.constants = constants;
	}
	
	public Object getConstant(String key) {
		return constants.get(key);
	}
}