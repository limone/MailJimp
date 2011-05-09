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
package mailjimp.service;

/**
 * Exception which is thrown every time we encounter an unexpected response.
 * Chances are good that the api version is not supported.
 * 
 * User: Eike Hirsch Date: 21.04.11 Time: 16:32
 */
@SuppressWarnings("serial")
public class UnexpectedMailChimpResponseException extends MailChimpException {
  public UnexpectedMailChimpResponseException() {
    super();
  }

  public UnexpectedMailChimpResponseException(String s) {
    super(s);
  }

  public UnexpectedMailChimpResponseException(String s, Throwable throwable) {
    super(s, throwable);
  }

  public UnexpectedMailChimpResponseException(Throwable throwable) {
    super(throwable);
  }
}