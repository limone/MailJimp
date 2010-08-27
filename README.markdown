### MailChimp API for Java

##### About

mc4j is a MailChimp library build in Java intended for use within Maven-enabled Spring-based applications.  The Maven part is not mandatory, of course - feel free to download the source and build yourself the library.  The Spring part is also not mandatory, as long as you deploy the library in a container that understands the @PostConstruct@ annotation, or you manually invoke  @MailChimpService::init()@ after construction.

##### Runtime Info

If you want to run the Maven tests and have them work properly, you need to pass a VM property: -Dconfig=file:///some/dir - to do this properly, please use the Maven command -DargLine.  The full command would be as follows: -DargLine="-Dconfig=file:///C:/Users/Michael/workspace/limone/limone-config"

##### Maven Repository

If you wish to use the mc4j API with Maven, please add the following repository to your POM:

	<repository>
		<id>sonatype-nexus-snapshots</id>
		<name>Sonatype Nexus Snapshots</name>
		<url>https://oss.sonatype.org/content/repositories/snapshots</url>
		<releases>
			<enabled>false</enabled>
		</releases>
		<snapshots>
			<enabled>true</enabled>
		</snapshots>
	</repository>