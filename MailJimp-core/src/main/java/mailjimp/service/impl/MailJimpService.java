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

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import mailjimp.dom.BatchResult;
import mailjimp.dom.EmailType;
import mailjimp.dom.list.Groups;
import mailjimp.dom.list.MailingList;
import mailjimp.dom.list.MemberInfo;
import mailjimp.dom.list.MemberStatus;
import mailjimp.dom.security.ApiKey;
import mailjimp.service.MailJimpException;

import org.apache.commons.lang3.ClassUtils;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The brains of the operation!
 */
@SuppressWarnings("serial")
public class MailJimpService extends AbstractMailJimpService {
  private static transient final Logger log = LoggerFactory.getLogger(MailJimpService.class);
  private transient XmlRpcClient client;
  private transient final MailJimpParser mp = new MailJimpParser();

  public MailJimpService() {
    super();
  }

  public MailJimpService(String username, String password, String apiKey, String apiVersion, boolean ssl) {
    super(username, password, apiKey, apiVersion, ssl);
  }

  @PostConstruct
  public void init() {
    // check if everything is configured.
    checkConfig();
    log.info("Creating MailChimp integration client.");
    String url = buildServerURL();
    log.info("Server URL is: {}", url);
    XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
    try {
      config.setEnabledForExtensions(true);
      config.setServerURL(new URL(url));
    } catch (MalformedURLException mue) {
      log.warn("MailChimp API URL was invalid.");
    }
    client = new XmlRpcClient();
    client.setConfig(config);
  }

  @SuppressWarnings("unchecked")
  private <T> T invoke(String method, Object[] params, String methodName) throws MailJimpException {
    try {
      Method m = ClassUtils.getPublicMethod(MailJimpParser.class, methodName, new Class[] { Object.class });
      return (T) m.invoke(mp, client.execute(method, params));
    } catch (Exception ex) {
      log.error("Could not invoke XML RPC client.", ex);
      throw new MailJimpException("Could not invoke XML RPC client.", ex);
    }
  }

  @Override
  public String keyAdd() throws MailJimpException {
    if (username == null || username.length() == 0 || password == null || password.length() == 0) {
      return null;
    }
    Object[] params = new Object[] { username, password, apiKey };
    return invoke("apikeyAdd", params, "createApiKey");
  }

  @Override
  public boolean keyExpire() throws MailJimpException {
    if (username == null || username.length() == 0 || password == null || password.length() == 0) {
      return false;
    }
    Object[] params = new Object[] { username, password, apiKey };
    return (Boolean) invoke("apikeyExpire", params, "expireApiKey");
  }

  @Override
  public List<ApiKey> keyList() throws MailJimpException {
    return keyList(true);
  }

  @Override
  public List<ApiKey> keyList(boolean includeExpired) throws MailJimpException {
    if (username == null || username.length() == 0 || password == null || password.length() == 0) {
      return new ArrayList<ApiKey>();
    }
    Object[] params = new Object[] { username, password, apiKey, includeExpired };
    return invoke("apikeys", params, "parseApiKeys");
  }

  @Override
  public List<MailingList> getLists() throws MailJimpException {
    Object[] params = new Object[] { apiKey };
    return invoke("lists", params, "parseLists");
  }

  @Override
  public Map<String, Date> getListMembers(String listId, MemberStatus memberStatus, Date since, Integer start, Integer limit) throws MailJimpException {
    List<Object> p = new ArrayList<Object>();
    p.add(apiKey);
    p.add(listId);
    p.add(memberStatus.getStatus());
    if (since != null) {
      p.add(MailJimpConstants.SDF.format(since));
    }
    if (start != null) {
      p.add(start);
    }
    if (limit != null) {
      p.add(limit);
    }
    return invoke("listMembers", p.toArray(), "parseListMembers");
  }

  @Override
  public MemberInfo getMemberInfo(String listId, String emailAddress) throws MailJimpException {
    Object address = emailAddress;
    if ("1.3".equals(apiVersion)) {
      address = new Object[] { address };
    }
    Object[] params = new Object[] { apiKey, listId, address };
    return invoke("listMemberInfo", params, "parseListMemberInfo");
  }

  @Override
  public boolean listSubscribe(String listId, String emailAddress, Map<String, Object> mergeVars, EmailType emailType, boolean doubleOptin, boolean updateExisting, boolean replaceInterests, boolean sendWelcome) throws MailJimpException {
    Object[] params = new Object[] { apiKey, listId, emailAddress, mergeVars, emailType.getEmailType(), doubleOptin, updateExisting, replaceInterests, sendWelcome };
    return (Boolean) invoke("listSubscribe", params, "parseListSubscribe");
  }

  @Override
  public BatchResult listBatchSubscribe(String listId, Object[] batch, boolean doubleOptin, boolean updateExisting, boolean replaceInterests) throws MailJimpException {
    Object[] params = new Object[] { apiKey, listId, batch, doubleOptin, updateExisting, replaceInterests };
    return (BatchResult) invoke("listBatchSubscribe", params, "parseListBatchSubscribe");
  }

  @Override
  public boolean listUnsubscribe(String listId, String emailAddress, boolean deleteMember, boolean sendGoodbye, boolean sendNotify) throws MailJimpException {
    Object[] params = new Object[] { apiKey, listId, emailAddress, deleteMember, sendGoodbye, sendNotify };
    return (Boolean) invoke("listUnsubscribe", params, "parseListUnsubscribe");
  }

  @Override
  public boolean listUpdateMember(String listId, String emailAddress, Map<String, Object> mergeVars, EmailType emailType, boolean replaceInterests) throws MailJimpException {
    Object[] params = new Object[] { apiKey, listId, emailAddress, mergeVars, emailType.getEmailType(), replaceInterests };
    return (Boolean) invoke("listUpdateMember", params, "parseListUpdateMember");
  }

  @Override
  public List<Groups> listInterestGroupings(String listId) throws MailJimpException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean listInterestGroupAdd(String listId, String groupId, Integer groupingId) throws MailJimpException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean listInterestGroupDel(String listId, String groupName, Integer groupingId) throws MailJimpException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean listInterestGroupUpdate(String listId, String oldName, String newName) throws MailJimpException {
    // TODO Auto-generated method stub
    return false;
  }
}