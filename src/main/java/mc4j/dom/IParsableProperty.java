package mc4j.dom;

/**
 * Marker interface to show the {@link mc4j.service.impl.MailChimpParser} that the implementing class should be parsed.
 *
 * The parser will throw an exception if it has to try to set a complex type property. If the properties type implements
 * this interface and if the parsed value is of type {@link java.util.Map} the parser will try to parse and set the
 * property.
 *
 * Author: Eike Hirsch (me at eike-hirsch dot net)
 * Date: 01.05.11
 * Time: 13:06
 */
public interface IParsableProperty {
  // empty
}