/*
 * Copyright 2011 Michael Laccetti and Tim Gilbert
 *
 * This file is part of MailJimp and forked MailJimp under https://github.com/knaak/MailJimp
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

import mailjimp.dom.MailJimpConstants;
import mailjimp.dom.request.campaign.CampaignCreateRequest;
import mailjimp.dom.response.campaign.CampaignListResponse;
import mailjimp.dom.response.campaign.CampaignListResponseItem;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/mailjimp-test-spring-config.xml" })
@Configurable
public class TestMailJimpJsonServiceCampaign extends AbstractServiceTester {


@Autowired
  private IMailJimpService mSvc;

  @Value("${mj.test.listId}")
  private String listId;

  @Value("${mj.test.templateId}")
  private Integer templateId;
  
  private static final String TEMPLATEHTML = "<style type=\"text/css\">\n</style><html>Test Case Template NEW</html>";  
  private static final String TEMPLATETEXT = "Test Case Template NEW";
  
  private static String campaignId;

  @BeforeClass
  public static void setup() {
  }

  @After
  public void after() {
    System.out.println("\n\n\n");
  }
  
  
  @Test
  public void testCreateCampaign()
  {
	  try {
		  log.debug("Test campaign create");
		  String response = mSvc.campaignCreate(MailJimpConstants.CAMPAIGNTYPE_REGULAR, 
				  CampaignCreateRequest.buildOptions(listId, "Test Campaign created by Test case", "Test Subject", "tim.gilbert@morningstar.com", "TestCase User", "Dear Customer"), 
				  CampaignCreateRequest.buildContentFromString(TEMPLATEHTML, TEMPLATETEXT));
		  campaignId = response;
		  log.debug("Template updateTemplate: {}", response);
		  Assert.assertTrue(response != null);
	  } catch (MailJimpException mje) {
		  processError(mje);
	  }
  }
  
  @Test 
  public void testListCampaign() throws InterruptedException
  {
	  try {		  
		  boolean found = false;
		  log.debug("Test campaign list");
		  
		  CampaignListResponse response = mSvc.campaignList(null, 0, 10);
		  
		  // find the campaign I just created
		  for (CampaignListResponseItem item : response.getItems())
		  {
			  log.debug(item.toString());
			  if (item.getId().equals(campaignId))
				 found = true;				
		  }
		  
		  
		  log.debug("Template list campaigns: {}", response);
		  Assert.assertTrue(response != null);
		  Assert.assertTrue(found);
	  } catch (MailJimpException mje) {
		  processError(mje);
	  } 
  }
  
  
  @Test
  public void testDeleteCampaign()
  {
	  try {
		  log.debug("Test campaign delete :" + campaignId);
		  Boolean response = mSvc.campaignDelete(campaignId);

		  log.debug("Template updateTemplate: {}", response);
		  Assert.assertTrue(response);
	  } catch (MailJimpException mje) {
		  processError(mje);
	  }
  }
  
}