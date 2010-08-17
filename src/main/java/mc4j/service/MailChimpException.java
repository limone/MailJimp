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

import java.util.ArrayList;
import java.util.List;

import mc4j.dom.MailChimpError;

public class MailChimpException extends Exception {
	private final int statusCode;
	private List<MailChimpError> errors = new ArrayList<MailChimpError>();
	
	public MailChimpException(int statusCode, String message) {
		super(message);
		this.statusCode = statusCode;
	}

	public MailChimpException(int statusCode, String message, Throwable cause) {
		super(message, cause);
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public List<MailChimpError> getErrors() {
		return errors;
	}

	public void setErrors(List<MailChimpError> errors) {
		this.errors = errors;
	}
}