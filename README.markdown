### MailChimp API for Java

#### About

MailJimp is a MailChimp library built in Java intended for use within Maven-enabled Spring-based applications.  MailJimp was tested against version 1.3 of the MailChimp API though most of the methods will work with version 1.2. (But I really don't know why you would use it ;)
The Maven part is not mandatory, of course - feel free to download the source and build yourself the library.  The Spring part is also not mandatory, as long as you deploy the library in a container that understands the `@PostConstruct` annotation, or you manually invoke `MailChimpService::init()` after construction.

#### Runtime Info

To run the service in a Spring-based application you have to do two things:

1. Integrate the config file `mailjimp-spring-config.xml` in your app. It is part of the distro and can be found in the classpath.

		<import resource="classpath:mailjimp-spring-config.xml" />

2. Create a file called `mailjimp.properties` and add it to the classpath. This file is used to configure the MailChimpService and has the following entries:

		mj.username=your_username
		mj.password=your_password
		mj.apiKey=your_api_key
		mj.apiVersion=version_to_use (1.3 is the current one)
		mj.ssl=https (true|false)

	An example of this file can be found under `/src/test/resources/mailjimp.properties` which is used for the integration tests.


#### Test Info

If you want to run the integration tests (`mailjimp.service.TestMailChimpService`) and have them work properly, you need to edit the files `/src/test/resources/mailjimp.properties` and `/src/test/resources/mailjimp-test.properties`.
In the later one you need to set the id of an existing list and an email address of an already subscribed user of that list.

#### Maven

If you wish to use the MailJimp API with Maven, please add the following to your POM:

#####  Repository

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


##### Dependency

	<dependency>
		<groupId>net.mailjimp</groupId>
		<artifactId>mailjimp-core</artifactId>
		<version>0.2-SNAPSHOT</version>
	</dependency>
