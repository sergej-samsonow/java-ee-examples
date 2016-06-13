### DependsOn ejb singletons
- Start application: mvn clean package org.codehaus.cargo:cargo-maven2-plugin:run
- Application url: http://localhost:8080/singleton-chain/example

### Notes
- First application start (mvn command) take's several minutes cargo plugin need to download wildfly.
- Application call take's several seconds because it contains to sleep timers.