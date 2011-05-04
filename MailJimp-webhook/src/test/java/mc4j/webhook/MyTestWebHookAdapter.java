package mc4j.webhook;

import mc4j.dom.MemberInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: Eike Hirsch (me at eike-hirsch dot net)
 * Date: 03.05.11
 * Time: 14:03
 */
@Service
public class MyTestWebHookAdapter implements IWebHookAdapter {

	private static final Logger LOG = LoggerFactory.getLogger( MyTestWebHookAdapter.class );


	@Override
	public boolean isValidRequest(HttpServletRequest request) {
		return true;
	}

	@Override
	public void userSubscribed(MemberInfo memberInfo) {
		LOG.info("A new member of the jungle.");
	}
}
