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
package mailjimp.dom;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * Handy constants, which are used in this project or by projects using this
 * one.
 */
public interface MailJimpConstants extends Serializable {
  SimpleDateFormat SDF              = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  String           MERGE_FNAME      = "FNAME";
  String           MERGE_LNAME      = "LNAME";
  String           MERGE_EMAIL      = "EMAIL";
  String           MERGE_EMAIL_TYPE = "EMAIL_TYPE";
  String           MERGE_GROUPINGS  = "GROUPINGS";
  String           MERGE_GROUPS     = "groups";
  
  // Campaign Consts
  
  final String			CAMPAIGNTYPE_REGULAR = "regular";
  final String			CAMPAIGNTYPE_PLAINTEXT = "plaintext";
  final String			CAMPAIGNTYPE_ABSPLIT = "absplit";
  final String			CAMPAIGNTYPE_RSS = "rss";
  final String			CAMPAIGNTYPE_AUTO = "auto";
  
  final String 			CAMPAIGNSTATUS_SENT = "sent";
  final String 			CAMPAIGNSTATUS_SAVE = "save";
  final String 			CAMPAIGNSTATUS_PAUSED = "paused";
  final String 			CAMPAIGNSTATUS_SCHEDULE = "schedule";
  final String 			CAMPAIGNSTATUS_SENDING = "sending";
  
  final String 			CAMPAIGNMEMBER_STATUS_SENT = "sent";
  final String			CAMPAIGNMEMBER_STATUS_HARDBOUNCE = "hard";
  final String			CAMPAIGNMEMBER_STATUS_SOFTBOUNCE = "soft";
  final String			CAMPAIGNMEMBER_STATUS_ALL = "";
}