package mc4j.service.impl;
/*
 * Copyright 2011 Eike Hirsch
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import static org.junit.Assert.*;
import static org.hamcrest.collection.IsMapContaining.*;

import mc4j.dom.IParsableProperty;
import mc4j.service.MailChimpException;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Eike Hirsch (me at eike-hirsch dot net)
 * Date: 27.04.11
 * Time: 06:38
 */
public class TestMailChimpParser {

	/**
	 * A Class with only simple values.
	 */
	public static class SimpleValues implements IParsableProperty {
		private int myInt;
		private boolean myBool;
		private String myString;

		public int getMyInt() {
			return myInt;
		}

		// Watch out! The parameter is an Integer not an int. tThanks to auto boxing this wont be a problem.
		public void setMyInt(Integer myInt) {
			this.myInt = myInt;
		}

		public boolean isMyBool() {
			return myBool;
		}

		public void setMyBool(Boolean myBool) {
			this.myBool = myBool;
		}

		public String getMyString() {
			return myString;
		}

		public void setMyString(String myString) {
			this.myString = myString;
		}
	}

	/**
	 * A class with a more complex object as an instance variable.
	 */
	private class SimpleValuesWrapper {
		private long id;
		private SimpleValues values;

		public long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public SimpleValues getValues() {
			return values;
		}

		public void setValues(SimpleValues values) {
			this.values = values;
		}
	}

	/**
	 * A class with an array of more complex objects as an instance variable.
	 */
	public static class SimpleValuesArrayWrapper {
		private long id;
		private SimpleValues[] valuesArray;

		public long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public SimpleValues[] getValuesArray() {
			return valuesArray;
		}

		public void setValuesArray(SimpleValues[] valuesArray) {
			this.valuesArray = valuesArray;
		}
	}

	/**
	 * Guess what - a map!
	 */
	private class MappedValues {
		private String id;
		private Map<String,Object> mergeVars;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public Map<String, Object> getMergeVars() {
			return mergeVars;
		}

		public void setMergeVars(Map<String, Object> mergeVars) {
			this.mergeVars = mergeVars;
		}
	}

	private MailChimpParser parser = new MailChimpParser();



	@Test
	public void setVarsSimpleValues() throws Exception {
		SimpleValues sv = new SimpleValues();
		Map<String, Object> simpleValuesResponse = createSimpleValuesMap(42);

		parser.setVars(simpleValuesResponse, sv);

		assertSimpleValues(sv, 42);
	}

	// creating a map representing a simple values object.
	private Map<String, Object> createSimpleValuesMap(final int id) {
		Map<String,Object> simpleValuesMap = new HashMap<String,Object>();

		simpleValuesMap.put("my_int", id);
		simpleValuesMap.put("my_bool", true);
		simpleValuesMap.put("my_string", "mc4j"+id);
		return simpleValuesMap;
	}

	private void assertSimpleValues(SimpleValues sv, final int expectedId) {
		assertEquals(expectedId, sv.getMyInt());
		assertTrue(sv.isMyBool());
		assertEquals("mc4j"+expectedId, sv.getMyString());
	}

	@Test
	public void setVarsWrappedObject() throws Exception {
		SimpleValuesWrapper svw = new SimpleValuesWrapper();
		Map<String,Object> simpleValuesWrapperMap = new HashMap<String,Object>();
		simpleValuesWrapperMap.put("id", 23l);
		simpleValuesWrapperMap.put("values", createSimpleValuesMap(42));

		parser.setVars(simpleValuesWrapperMap, svw );

		assertEquals(23l, svw.getId());
		assertSimpleValues(svw.getValues(), 42);
	}

	@Test
	public void setVarsWrappedObjectArray() throws Exception {
		SimpleValuesArrayWrapper svaw = new SimpleValuesArrayWrapper();

		Map<String,Object> simpleValuesArrayWrapperMap = new HashMap<String,Object>();
		simpleValuesArrayWrapperMap.put("id", 23l);

		Object[] array = new Object[]{
				createSimpleValuesMap(42),
				createSimpleValuesMap(23),
				createSimpleValuesMap(5)
		};
		simpleValuesArrayWrapperMap.put("values_array", array);

		parser.setVars(simpleValuesArrayWrapperMap, svaw );

		assertEquals(23l, svaw.getId());
		assertEquals(3, svaw.getValuesArray().length);

		assertSimpleValues( svaw.getValuesArray()[0], 42);
		assertSimpleValues( svaw.getValuesArray()[1], 23);
		assertSimpleValues( svaw.getValuesArray()[2], 5);
	}

	@Test
	public void setVarsMappedValues() throws Exception {
	    MappedValues mv = new MappedValues();

		Map<String,Object> mappedValuesMap = new HashMap<String, Object>();
		mappedValuesMap.put("id", null);
		mappedValuesMap.put("merge_vars", createSimpleValuesMap(33));

		parser.setVars(mappedValuesMap, mv);

		assertNull(mv.getId());
		assertThat(mv.getMergeVars(), hasEntry("my_int", (Object)33));
		assertThat(mv.getMergeVars(), hasEntry("my_bool", (Object) true));
		assertThat(mv.getMergeVars(), hasEntry("my_string", (Object)"mc4j33"));
	}

	@Test
	public void testParseListSubscribeNormalFlow() throws Exception {
		assertTrue( parser.parseListSubscribe(true));
		assertFalse(parser.parseListSubscribe(false));
	}

	@Test(expected = MailChimpException.class)
	public void parseListSubscribeFailure() throws Exception {
	    parser.parseListSubscribe("true");
	}

	@Test(expected = MailChimpException.class)
	public void parseListSubscribeCanHandleNull() throws Exception {
	    parser.parseListSubscribe(null);
	}

	@Test
	public void testParseListUnsubscribeNormalFlow() throws Exception {
		assertTrue( parser.parseListUnsubscribe(true));
		assertFalse(parser.parseListUnsubscribe(false));
	}

	@Test(expected = MailChimpException.class)
	public void parseListUnsubscribeFailure() throws Exception {
		parser.parseListUnsubscribe("true");
	}

	@Test(expected = MailChimpException.class)
	public void parseListUnsubscribeCanHandleNull() throws Exception {
		parser.parseListUnsubscribe(null);
	}

	@Test
	public void testParseListUpdateMemberNormalFlow() throws Exception {
		assertTrue( parser.parseListUpdateMember(true));
		assertFalse(parser.parseListUpdateMember(false));
	}

	@Test(expected = MailChimpException.class)
	public void parseListUpdateMemberFailure() throws Exception {
		parser.parseListUpdateMember("true");
	}

	@Test(expected = MailChimpException.class)
	public void parseListUpdateMemberCanHandleNull() throws Exception {
		parser.parseListUpdateMember(null);
	}


}
