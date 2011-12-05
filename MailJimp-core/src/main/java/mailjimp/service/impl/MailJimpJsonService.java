package mailjimp.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.MediaType;

import mailjimp.dom.enums.EmailType;
import mailjimp.dom.enums.MemberStatus;
import mailjimp.dom.request.list.ListMemberInfoRequest;
import mailjimp.dom.request.list.ListMembersRequest;
import mailjimp.dom.request.list.ListSubscribeRequest;
import mailjimp.dom.request.list.ListUnsubscribeRequest;
import mailjimp.dom.request.list.ListUpdateMemberRequest;
import mailjimp.dom.request.list.ListsRequest;
import mailjimp.dom.response.MailJimpErrorResponse;
import mailjimp.dom.response.list.BatchSubscribeResponse;
import mailjimp.dom.response.list.Groups;
import mailjimp.dom.response.list.ListMemberInfoResponse;
import mailjimp.dom.response.list.ListMembersResponse;
import mailjimp.dom.response.list.ListsResponse;
import mailjimp.dom.response.list.MailingList;
import mailjimp.dom.response.list.MemberInfo;
import mailjimp.dom.response.list.MemberResponseInfo;
import mailjimp.dom.security.ApiKey;
import mailjimp.service.MailJimpException;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class MailJimpJsonService extends AbstractMailJimpService {
  private final Logger log = LoggerFactory.getLogger(getClass());

  private final ObjectMapper m = new ObjectMapper();

  private Client client;
  private WebResource resource;

  public MailJimpJsonService() {
    super();
  }

  public MailJimpJsonService(String username, String password, String apiKey, String apiVersion, boolean ssl) {
    super(username, password, apiKey, apiVersion, ssl);
  }

  @PostConstruct
  public void init() {
    // check if everything is configured.
    checkConfig();
    log.info("Creating MailChimp integration client.");
    String url = buildServerURL();
    log.info("Server URL is: {}", url);
    client = Client.create();
    resource = client.resource(url);
    
    SerializationConfig s = m.getSerializationConfig();
    s.setSerializationInclusion(Inclusion.NON_NULL);
    s.withDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    m.setSerializationConfig(s);
    
    DeserializationConfig d = m.getDeserializationConfig();
    d.withDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    m.setDeserializationConfig(d);
    
    m.setDateFormat(new SimpleDateFormat("yyyy-MM-MM HH:mm:ss"));
  }
  
  private <T> T performRequest(String method, Object param, Class<T> clazz) throws MailJimpException {
    String requestJson = null;
    try {
      requestJson = m.writeValueAsString(param);
    } catch (Exception ex) {
      log.error("Could not convert lists request to JSON.", ex);
      throw new MailJimpException("Could not convert parameter to JSON.", ex);
    }

    log.debug("Outbound request: {}", requestJson);
    ClientResponse response = resource.queryParam("method", method).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, requestJson);

    if (response.getStatus() != 200) {
      log.error("Failed : HTTP error code : " + response.getStatus());
      String entity = null;
      try {
        IOUtils.toString(response.getEntityInputStream());
      } catch (Exception ex) {
        log.warn("Could not convert entity to String.", ex);
      }
      throw new MailJimpException(String.format("Could not perform action (%s).  %s", method, entity));
    }

    String responseJson = response.getEntity(String.class);
    log.debug("Inbound response: {}", responseJson);
    
    if (responseJson.startsWith("{\"error\":")) {
      try {
        MailJimpErrorResponse err = m.readValue(responseJson, MailJimpErrorResponse.class);
        throw new MailJimpException(String.format("Error while performing action (%s).  Message: %s.  Code: %s.", method, err.getError(), err.getCode()));
      } catch (IOException ie) {
        log.error("Could not convert error from JSON to Object.", ie);
        throw new MailJimpException("Could not convert error from JSON to Object.", ie);
      }
    }

    try {
      T val = m.readValue(responseJson, clazz);
      return val;
    } catch (Exception ex) {
      log.error(String.format("Could not convert JSON to expected type (%s).", clazz.getCanonicalName()), ex);
      throw new MailJimpException(String.format("Could not convert JSON to expected type (%s).", clazz.getCanonicalName()), ex);
    }
  }
  @Override
  public List<ApiKey> keyList() throws MailJimpException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<ApiKey> keyList(boolean includeExpired) throws MailJimpException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String keyAdd() throws MailJimpException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean keyExpire() throws MailJimpException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public List<MailingList> lists() throws MailJimpException {
    ListsResponse response = performRequest("lists", new ListsRequest(apiKey), ListsResponse.class);
    log.debug("List info: {}", response);
    return response.getLists();
  }

  @Override
  public List<MemberResponseInfo> listMembers(String listId, MemberStatus memberStatus, Date since, Integer start, Integer limit) throws MailJimpException {
    ListMembersResponse response = performRequest("listMembers", new ListMembersRequest(apiKey, listId, memberStatus.getStatus(), since, start, limit), ListMembersResponse.class);
    log.debug("List info: {}", response);
    return response.getMembers();
  }

  @Override
  public List<MemberInfo> listMemberInfo(String listId, List<String> emailAddresses) throws MailJimpException {
    ListMemberInfoResponse response = performRequest("listMemberInfo", new ListMemberInfoRequest(apiKey, listId, emailAddresses), ListMemberInfoResponse.class);
    log.debug("List member info response: {}", response);
    return response.getMembers();
  }

  @Override
  public boolean listSubscribe(String listId, String emailAddress, Map<String, Object> mergeVars, EmailType emailType, boolean doubleOptin, boolean updateExisting, boolean replaceInterests, boolean sendWelcome) throws MailJimpException {
    Boolean response = performRequest("listSubscribe", new ListSubscribeRequest(apiKey, listId, emailAddress, mergeVars, emailType, doubleOptin, updateExisting, replaceInterests, sendWelcome), Boolean.class);
    log.debug("List subscribe response: {}", response);
    return response;
  }

  @Override
  public BatchSubscribeResponse listBatchSubscribe(String listId, Object[] batch, boolean doubleOptin, boolean updateExisting, boolean replaceInterests) throws MailJimpException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean listUpdateMember(String listId, String emailAddress, Map<String, Object> mergeVars, EmailType emailType, boolean replaceInterests) throws MailJimpException {
    Boolean response = performRequest("listUpdateMember", new ListUpdateMemberRequest(apiKey, listId, emailAddress, mergeVars, emailType, replaceInterests), Boolean.class);
    log.debug("List update member response: {}", response);
    return response;
  }

  @Override
  public boolean listUnsubscribe(String listId, String emailAddress, boolean deleteMember, boolean sendGoodbye, boolean sendNotify) throws MailJimpException {
    Boolean response = performRequest("listUnsubscribe", new ListUnsubscribeRequest(apiKey, listId, emailAddress, deleteMember, sendGoodbye, sendNotify), Boolean.class);
    log.debug("List unsubscribe response: {}", response);
    return response;
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