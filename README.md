# Getting Started
This is a maven multi module project.
```
[INFO] City Management Tool ............................... SUCCESS [  4.344 s]
[INFO] city-management-back ............................... SUCCESS [  3.862 s]
[INFO] city-management-front .............................. SUCCESS [  0.187 s]
```

## city-management-back
Spring Boot application.

Backend runs on the port 8181 and has Swagger(http://localhost:8181/swagger-ui/index.html) available with all endpoints documented.

The app interacts with H2 in-memory DB and use it to operate with cities.
At the startup of the app the initial list of cities is obtained from the csv file located in the application classpath.

The security configured with in memmory users configuration and JWT authentication mechanism.
Two users are ready for use:

```
admin/admin ['USER','ADMIN','ALLOW_EDIT']
user/user ['USER']
```
Only the user with role 'ALLOW_EDIT' is allowed to open city to edit and save it.

## city-management-front
React based frontend application


## Building the project

To build the project run the following command on the command line:
```
mvn clean package
```

If you prefer run it without tests
```
mvn clean package -DskipTests
```

The output should look like:
```
[INFO] Reactor Summary for City Management Tool 1.0-SNAPSHOT:
[INFO]
[INFO] City Management Tool ............................... SUCCESS [  0.006 s]
[INFO] city-management-back ............................... SUCCESS [ 10.536 s]
[INFO] city-management-front .............................. SUCCESS [02:11 min]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  02:21 min
[INFO] Finished at: 2023-04-05T13:04:45+02:00
[INFO] ------------------------------------------------------------------------

```

## Running the backend

We could run it either like a Spring Boot configuration form InteliJ or using spring-boot maven plugin.

### Running with spring-boot maven plugin
To run the project run the following command on the command line:

```
mvn spring-boot:run -f .\city-management-back\pom.xml

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.0.4)

2023-04-05T13:12:08.967+02:00  INFO 29004 --- [  restartedMain] c.h.c.CityManagementApplication          : Starting CityManagementApplication using Java 17.0.6 with PID 29004 (C:\workspace\city-management-tool\
city-management-back\target\classes started by user in C:\workspace\city-management-tool\city-management-back)
2023-04-05T13:12:08.975+02:00  INFO 29004 --- [  restartedMain] c.h.c.CityManagementApplication          : No active profile set, falling back to 1 default profile: "default"
2023-04-05T13:12:09.060+02:00  INFO 29004 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
2023-04-05T13:12:09.060+02:00  INFO 29004 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
2023-04-05T13:12:10.513+02:00  INFO 29004 --- [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2023-04-05T13:12:10.635+02:00  INFO 29004 --- [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 110 ms. Found 1 JPA repository interfaces.
2023-04-05T13:12:11.739+02:00  INFO 29004 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8181 (http)
2023-04-05T13:12:11.765+02:00  INFO 29004 --- [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2023-04-05T13:12:11.766+02:00  INFO 29004 --- [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.5]
2023-04-05T13:12:11.922+02:00  INFO 29004 --- [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2023-04-05T13:12:11.925+02:00  INFO 29004 --- [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 2862 ms
2023-04-05T13:12:11.996+02:00  INFO 29004 --- [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2023-04-05T13:12:12.384+02:00  INFO 29004 --- [  restartedMain] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection conn0: url=jdbc:h2:mem:testdb user=SA
2023-04-05T13:12:12.386+02:00  INFO 29004 --- [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.                                          
2023-04-05T13:12:12.401+02:00  INFO 29004 --- [  restartedMain] o.s.b.a.h2.H2ConsoleAutoConfiguration    : H2 console available at '/h2'. Database available at 'jdbc:h2:mem:testdb'
2023-04-05T13:12:12.611+02:00  INFO 29004 --- [  restartedMain] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2023-04-05T13:12:12.699+02:00  INFO 29004 --- [  restartedMain] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 6.1.7.Final
2023-04-05T13:12:13.173+02:00  INFO 29004 --- [  restartedMain] SQL dialect                              : HHH000400: Using dialect: org.hibernate.dialect.H2Dialect
2023-04-05T13:12:14.149+02:00  INFO 29004 --- [  restartedMain] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]
2023-04-05T13:12:14.163+02:00  INFO 29004 --- [  restartedMain] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2023-04-05T13:12:14.477+02:00  WARN 29004 --- [  restartedMain] ocalVariableTableParameterNameDiscoverer : Using deprecated '-debug' fallback for parameter name resolution. Compile the affected code with '-parameters' instead or avo
id its introspection: com.helmes.citymanagement.repository.CityRepository
2023-04-05T13:12:15.330+02:00  INFO 29004 --- [  restartedMain] c.h.c.dataloader.DBCityDataSaver         : 1000 cities were loaded into the DB
2023-04-05T13:12:15.587+02:00  WARN 29004 --- [  restartedMain] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly
 configure spring.jpa.open-in-view to disable this warning
2023-04-05T13:12:17.310+02:00  INFO 29004 --- [  restartedMain] o.s.s.web.DefaultSecurityFilterChain     : Will secure any request with [org.springframework.security.web.session.DisableEncodeUrlFilter@1c940061, org.springframework.s
ecurity.web.context.request.async.WebAsyncManagerIntegrationFilter@49dafa95, org.springframework.security.web.context.SecurityContextHolderFilter@2feeebf6, org.springframework.security.web.header.HeaderWriterFilter@2dd92555, org.spr
ingframework.web.filter.CorsFilter@3ca842c9, org.springframework.security.web.authentication.logout.LogoutFilter@503622a5, org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter@5cf52e
41, org.springframework.security.web.authentication.www.BasicAuthenticationFilter@7b24a374, org.springframework.security.web.savedrequest.RequestCacheAwareFilter@217d019d, org.springframework.security.web.servletapi.SecurityContextH
olderAwareRequestFilter@1cf423f7, org.springframework.security.web.authentication.AnonymousAuthenticationFilter@6051eab4, org.springframework.security.web.session.SessionManagementFilter@7b3bafbb, org.springframework.security.web.ac
cess.ExceptionTranslationFilter@400de3c5, org.springframework.security.web.access.intercept.AuthorizationFilter@6d4c8588]
2023-04-05T13:12:17.693+02:00  INFO 29004 --- [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2023-04-05T13:12:17.754+02:00  INFO 29004 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8181 (http) with context path ''
2023-04-05T13:12:17.773+02:00  INFO 29004 --- [  restartedMain] c.h.c.CityManagementApplication          : Started CityManagementApplication in 9.43 seconds (process running for 10.222)

```

### Running the frontend
Open a terminal/cmd and move to: city-management-front\ui
From there, please execute next comand:
```
npm start
```
The development server will be started on localhost:3000 and will be able to interact with backend running on the localhost:8181

### Install Development Tools
Assuming that a Java SE 17 is configured on the JAVA_HOME environment variable,
you need to add the following tools:

* [Maven](https://maven.apache.org/)
* [Node.js with npm](https://nodejs.org/en/)

### Download and install Maven
* Download maven from [here](https://maven.apache.org/download.cgi)
* Extract distribution archive in any directory
```
unzip apache-maven-3.8.8-bin.zip
```
* Add the bin directory of the created directory apache-maven-3.8.8 to the PATH environment variable
* Confirm with mvn -v in a new shell. The result should look similar to
```
Apache Maven 3.8.8 (4c87b05d9aedce574290d1acc98575ed5eb6cd39)                                                
Maven home: C:\maven\apache-maven-3.8.8                                                
Java version: 17.0.6, vendor: GraalVM Community, runtime: C:\Java\Graalvm\graalvm-ce-17
Default locale: en_US, platform encoding: Cp1251                                                             
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows" 
```

### Download and install Node.js & npm
The proper versions of Node.js and npm will be automatically downloaded and installed with mvn install command.

If you want to do it manually follow next steps:
Download the Node.js source code or a pre-built installer for your platform and follow the instruction by the installer.

To see if you already have Node.js and npm installed and check the installed version, run the following commands:
```
> node -v
v16.15.0
> npm -v
8.12.0
```
To download the latest version of npm, on the command line, run the following command:
```
npm install -g npm
```
