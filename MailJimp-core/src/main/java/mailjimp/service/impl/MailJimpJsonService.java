package mailjimp.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.MediaType;

import mailjimp.dom.BatchResult;
import mailjimp.dom.EmailType;
import mailjimp.dom.list.Groups;
import mailjimp.dom.list.MailingList;
import mailjimp.dom.list.MemberInfo;
import mailjimp.dom.list.MemberStatus;
import mailjimp.dom.request.ListsRequest;
import mailjimp.dom.security.ApiKey;
import mailjimp.service.MailJimpException;

import org.codehaus.jackson.map.ObjectMapper;
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
  public List<MailingList> getLists() throws MailJimpException {
    ListsRequest lr = new ListsRequest(apiKey, 0, 25);
    String json = null;
    try {
      json = m.writeValueAsString(lr);
    } catch (Exception ex) {
      log.error("Could not convert lists request to JSON.", ex);
      throw new MailJimpException("Could not convert lists request to JSON.", ex);
    }
    
    ClientResponse response = resource.queryParam("method", "lists").accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);

    if (response.getStatus() != 200) {
      log.error("Failed : HTTP error code : " + response.getStatus());
      throw new MailJimpException("Could not retrieve lists.");
    }

    String output = response.getEntity(String.class);

    log.debug("Output from Server .... \n{}", output);
    return null;
  }

  @Override
  public Map<String, Date> getListMembers(String listId, MemberStatus memberStatus, Date since, Integer start, Integer limit) throws MailJimpException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public MemberInfo getMemberInfo(String listId, String emailAddress) throws MailJimpException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean listSubscribe(String listId, String emailAddress, Map<String, Object> mergeVars, EmailType emailType, boolean doubleOptin, boolean updateExisting, boolean replaceInterests, boolean sendWelcome) throws MailJimpException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public BatchResult listBatchSubscribe(String listId, Object[] batch, boolean doubleOptin, boolean updateExisting, boolean replaceInterests) throws MailJimpException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean listUpdateMember(String listId, String emailAddress, Map<String, Object> mergeVars, EmailType emailType, boolean replaceInterests) throws MailJimpException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean listUnsubscribe(String listId, String emailAddress, boolean deleteMember, boolean sendGoodbye, boolean sendNotify) throws MailJimpException {
    // TODO Auto-generated method stub
    return false;
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