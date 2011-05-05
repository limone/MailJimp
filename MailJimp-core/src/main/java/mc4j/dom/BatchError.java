/*
 * Copyright 2011 Eike Hirsch
 * 
 * This file is part of MailJimp.
 * 
 * MailJimp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 * 
 * MailJimp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with MailJimp.  If not, see <http://www.gnu.org/licenses/>.
 */
package mc4j.dom;

/**
* Author: Eike Hirsch (me at eike-hirsch dot net)
* Date: 27.04.11
* Time: 11:44
*/
public class BatchError implements IParsableProperty {
	String email;
	int code;
	String message;

	@Override
	public String toString() {
		return "BatchError{" +
				"email='" + email + '\'' +
				", code='" + code + '\'' +
				", message='" + message + '\'' +
				'}';
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
