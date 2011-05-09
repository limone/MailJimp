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
package mailjimp.service.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * Handy constants, which are used in this project or by projects using this
 * one.
 */
public interface MailChimpConstants extends Serializable {
  SimpleDateFormat SDF              = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  String           MERGE_FNAME      = "FNAME";
  String           MERGE_LNAME      = "LNAME";
  String           MERGE_EMAIL      = "EMAIL";
  String           MERGE_EMAIL_TYPE = "EMAIL_TYPE";
  String           MERGE_GROUPINGS  = "GROUPINGS";
  String           MERGE_GROUPS     = "groups";
}