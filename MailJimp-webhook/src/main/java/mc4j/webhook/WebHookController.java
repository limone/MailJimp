package mc4j.webhook;

import mc4j.dom.MemberInfo;
import mc4j.service.impl.MailChimpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 *
 * Author: Eike Hirsch (me at eike-hirsch dot net)
 * Date: 03.05.11
 * Time: 09:29
 */
@Controller
public class WebHookController {

	// TODO: move to core
	public static class Group {
	}


	private static Pattern DATA_PATTERN = Pattern.compile("data\\[(\\w+)\\](\\[(\\w+)\\](\\[(\\d+)\\]\\[(\\w+)\\])?)?");
	private static int INDEX_PARAM_NAME = 1;
	private static int INDEX_MERGES_NAME = 3;
	private static int INDEX_GROUP_INDEX = 5;
	private static int INDEX_GROUP_PARAM = 6;

	@Autowired
	private IWebHookAdapter webHookAdapter;

	// TODO: refactor the used methods out of this class
	private MailChimpParser parser = new MailChimpParser();


	@RequestMapping(params = "type=subscribe")
	public @ResponseBody String subscribe(WebRequest request) {
		Map<String,String[]> parameterMap = request.getParameterMap();
		Map<String,Object> convertible = new HashMap<String, Object>();
		Map<String,Object> merges = new HashMap<String, Object>();
		convertible.put("merges", merges);

		for (String key : parameterMap.keySet()) {
			Matcher matcher = DATA_PATTERN.matcher(key);
			if(matcher.matches()) {
				final String value = parameterMap.get(key)[0];
				if( null == matcher.group(INDEX_MERGES_NAME)) {
					// simple values
					convertible.put(matcher.group(INDEX_PARAM_NAME), value);
				} else if( null == matcher.group(INDEX_GROUP_INDEX)) {
					// simple merges
					merges.put(matcher.group(INDEX_MERGES_NAME), value );
				} else {
					// groupings

				}
			}
		}
		debug(convertible);
		webHookAdapter.userSubscribed(null);
		return "Copy that!";
	}

	private void debug(Map<String, Object> convertible) {
		for( String key : convertible.keySet() ) {
			final Object value = convertible.get(key);
			if( Map.class.isAssignableFrom(value.getClass())) {
				debug((Map<String, Object>) value);
			} else {
				System.out.format("\n%s\t%s", key, value);
			}
		}
		System.out.print('\n');
	}

	private void bindMemberInfo(MemberInfo memberInfo, Matcher matcher, String value) {


	}


}
