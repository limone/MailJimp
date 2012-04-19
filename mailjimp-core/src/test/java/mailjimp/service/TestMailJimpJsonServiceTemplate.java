/*
 * Copyright 2011 Michael Laccetti
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

import mailjimp.dom.response.template.TemplateInfoResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/mailjimp-test-spring-config.xml" })
@Configurable
public class TestMailJimpJsonServiceTemplate extends AbstractServiceTester {
  @Autowired
  private IMailJimpService mSvc;

  
  private static String templateName = RandomStringUtils.randomAlphabetic(8);
  private static String templateNewName = RandomStringUtils.randomAlphabetic(8);
  private static Integer templateId = -1;

  @BeforeClass
  public static void setup() {
  }

  @After
  public void after() {
    System.out.println("\n\n\n");
  }
  
    
  @Test
  public void testTemplateAdd(){
	  try {
		  log.debug("Test template add");
		  Integer response = mSvc.templateAdd(templateName, "<html>Test Case Template</html>");
		  templateId = response;
		  log.debug("Template addTemplate: {}", response);
	  } catch (MailJimpException mje) {
		  processError(mje);
	  }
  }
  
  @Test
  public void testTemplateUpdate(){
	  try {
		  log.debug("Test template add");
		  Boolean response = mSvc.templateUpdate(templateId, templateNewName, "<html>Test Case Template NEW</html>");		  
		  log.debug("Template updateTemplate: {}", response);
		  Assert.assertTrue(response);
	  } catch (MailJimpException mje) {
		  processError(mje);
	  }
  }
  
  @Test
  public void testTemplateInfo(){
	  try {
		  log.debug("Test template info");
		  TemplateInfoResponse response = mSvc.templateInfo(templateId, null);		  
		  log.debug("Template infoTemplate: {}", response);
		  
		  Assert.assertTrue(response.getSource().equals("<style type=\"text/css\">\n</style><html>Test Case Template NEW</html>"));
		  Assert.assertTrue(response.getPreview().equals("<style type=\"text/css\">\n</style><html>Test Case Template NEW</html>"));
		  
	  } catch (MailJimpException mje) {
		  processError(mje);
	  }
  }
  
    
  
  @Test
  public void testTemplateDel(){
	  try {
		  log.debug("Test template del");
		  boolean response = mSvc.templateDel(templateId);		  
		  log.debug("Template delTemplate: {}", response);
		  Assert.assertTrue(response);
	  } catch (MailJimpException mje) {
		  processError(mje);
	  }	  
  }
  
  
}