/**
 * Copyright 2010 Michael Laccetti
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
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