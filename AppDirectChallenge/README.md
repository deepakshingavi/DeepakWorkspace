This application serves as a minimal integration of AppDirect's APIs and includes the following:
1. Integration of AppDirect's subscription management API for Create and Cancel.
2. Spring Security - protects restricted URLs, validates OAuth 1.0 and OpenID headers and maintains session
3. OpenID authentication
4. OAuth 1.0 one-legged authentication of incoming requests from AppDirect
5. OAuth 1.0 signing of out going requests to AppDirect
6. Java DB SQL database

Inorder to execute the project execute 
java $JAVA_OPTS -jar -Dlocal.server.port=8080 target/appdirect-0.1.0.jar