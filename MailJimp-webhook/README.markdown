## MailChimp WebHook API for Java

### About

This module provides a generic way to handle MailChimps callbacks a.k.a. WebHooks. In contrast to the core module the webhooks require some spring dependencies at runtime. (We use @Controller and other annotations here)

Basically this modules consists out of two classes and one interface. Both classes (WebHookController and WebHookSecurityInterceptor) make use of the interface (IWebHookAdapter).

Your task is to implement that interface and to make it available in your application context. 

### Runtime Info

Everything that is true for the core module is true for this one with one addition:

	<import resource="classpath:mailjimp-webhook-spring-config.xml" />

This will auto configure the controller and the interceptor. Keep in mind that this will issue an error if no or more than one instance of the IWebHookAdapter is found.

### Maven dependency

To use this module add the following to you pom.

	<dependency>
		<groupId>net.mailjimp</groupId>
		<artifactId>mailjimp-webhook</artifactId>
		<version>0.2</version>
	</dependency>

