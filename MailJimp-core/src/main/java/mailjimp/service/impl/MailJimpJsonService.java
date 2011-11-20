package mailjimp.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
import mailjimp.dom.response.ListsResponse;
import mailjimp.dom.response.MailJimpErrorResponse;
import mailjimp.dom.response.MailJimpResponse;
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
    s.withDateFormat(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss"));
    m.setSerializationConfig(s);
    
    DeserializationConfig d = m.getDeserializationConfig();
    d.withDateFormat(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss"));
    m.setDeserializationConfig(d);
    
    m.setDateFormat(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss"));
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
    ListsResponse response = performRequest("lists", new ListsRequest(apiKey), ListsResponse.class);
    log.debug("List info: {}", response);
    return null;
  }

  private <T extends MailJimpResponse> T performRequest(String method, Object param, Class<T> clazz) throws MailJimpException {
    String json = null;
    try {
      json = m.writeValueAsString(param);
    } catch (Exception ex) {
      log.error("Could not convert lists request to JSON.", ex);
      throw new MailJimpException("Could not convert parameter to JSON.", ex);
    }

    ClientResponse response = resource.queryParam("method", method).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, json);

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
    if (responseJson.startsWith("{\"error\":")) {
      try {
        MailJimpErrorResponse err = m.readValue(responseJson, MailJimpErrorResponse.class);
        throw new MailJimpException(String.format("Error while performing action (%s).  Message: %s.  Code: %s.", method, err.getError(), err.getCode()));
      } catch (IOException ie) {
        log.error("Could not convert error from JSON to Object.", ie);
        throw new MailJimpException("Could not convert error from JSON to Object.", ie);
      }
    }

    log.debug("Output from Server .... \n{}", responseJson);
    try {
      T val = m.readValue(responseJson, clazz);
      return val;
    } catch (Exception ex) {
      log.error(String.format("Could not convert JSON to expected type (%s).", clazz.getCanonicalName()), ex);
      throw new MailJimpException(String.format("Could not convert JSON to expected type (%s).", clazz.getCanonicalName()), ex);
    }
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