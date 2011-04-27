### MailChimp API for Java

##### About

mc4j is a MailChimp library built in Java intended for use within Maven-enabled Spring-based applications.  mc4j was tested against version 1.3 of the MailChimp API though most of the methods will work with version 1.2. (But I really don't know why you would use it ;)
The Maven part is not mandatory, of course - feel free to download the source and build yourself the library.  The Spring part is also not mandatory, as long as you deploy the library in a container that understands the `@PostConstruct` annotation, or you manually invoke `MailChimpService::init()` after construction.

##### Runtime Info

To run the service in a Spring-based application you have have to do two things:

1. Integrate the config file `mc4j-spring-config.xml` in your app.It is part of the distro an can be found in the classpath.

		<import resource="classpath:mc4j-spring-config.xml" />

2. Create a file called `mc4j.properties` and add it to the classpath. This file is used to configure the MailChimpService and has the following entries:

		mc.username=your_username
		mc.password=your_password
		mc.apiKey=your_api_key
		mc.apiVersion=version_to_use (1.3 is the current one)
		mc.ssl=https (true|false)

	An example of this file can be found under `/src/test/resources/mc4j.properties` which is used for the integration tests.


##### Test Info

If you want to run the integration tests (`mc4j.service.TestMailChimpService`) and have them work properly, you need to edit the files `/src/test/resources/mc4j.properties` and `/src/test/resources/mc4j-test.properties`.
In the later one you need to set the id of an existing list and an emailaddress of an already subscribed user of that list.

##### Maven

If you wish to use the mc4j API with Maven, please add the following to your POM:

######  Repository

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


###### Dependency

	<dependency>
		<groupId>me.limone.mc4j</groupId>
		<artifactId>mc4j</artifactId>
		<version>0.1-SNAPSHOT</version>
	</dependency>
