/*
 * Copyright 2010-2011 Michael Laccetti
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
package mailjimp.service;

import java.util.ArrayList;
import java.util.List;

import mailjimp.dom.MailJimpError;

@SuppressWarnings("serial")
public class MailJimpException extends Exception {
  private int                  statusCode;
  private List<MailJimpError> errors = new ArrayList<MailJimpError>();

  public MailJimpException() {
    super();
  }

  public MailJimpException(String message, Throwable cause) {
    super(message, cause);
  }

  public MailJimpException(String message) {
    super(message);
  }

  public MailJimpException(Throwable cause) {
    super(cause);
  }

  public MailJimpException(int statusCode, String message) {
    super(message);
    this.statusCode = statusCode;
  }

  public MailJimpException(int statusCode, String message, Throwable cause) {
    super(message, cause);
    this.statusCode = statusCode;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public List<MailJimpError> getErrors() {
    return errors;
  }

  public void setErrors(List<MailJimpError> errors) {
    this.errors = errors;
  }
}