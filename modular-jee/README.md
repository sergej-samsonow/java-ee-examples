# Modular javaee application design with ejb jars
This project is example application how to use portable JNDI and ejb jars to
design modular javaee applications.

## Precoditions
JDK and maven installations are needed to build and execute this example. 
This application was tested with Wildfly application server, wildfly 
installation is described below. Before you start open your favorite shell
and check ``JAVA_HOME`` and ``M2_HOME`` check environment variables set those
if not happen yet. For the more check ``java`` and ``mvn`` commands are in 
excution path.

## Wildfly installation
1. Download wildfly application server
   [http://wildfly.org/downloads/](http://wildfly.org/downloads/)
2. Unpack wildfly application server in you favorite folder
3. Create environment variable ``JBOSS_HOME`` that contains path to you 
   wildfly installation folder (step above)
4. Open shell an go into ``JBOSS_HOME`` and then into ``bin`` folder
5. Start wildfly with command ``standalone`` open http://localhost:8080
   in you favorite browser anything is fine if you see wildfly welcome page
6. To stop wildfly server enter ``Ctr+Shift+C`` in wildfly shell you started
   at point 4


## About application
Application is simple greeting service, application is splitted into 3 components
each of them is javaee deployment unit.
1. Api component is glue between application deployment unit and other components
   that are loaded dynamically. Api component is packgaged as ejb.jar and
   contains all shared objectes that are used by application itself and from
   other deployment units.
2. Application itself is simple war package jax-rx + ejb application it has the
   only one dependency api component dependcy described in pom.xml. Application
   can't be deployed without api components so api components must be deployed
   with application at same time or before application is deployed.
3. Dynamic greeting this component contains ejb that greets customer in
   russian language. This component can be used to extend appication. Application
   and this component know nothing about each other the only one thing they know
   is api component. Interaction between application and dynamic component 
   happens with EJB + JNDI Technology.

## Build
This example don't uses maven multiproject model for better understanding but 
you have to pay attention to build and install application-api project first 
before you build other project.
1. Open you shell go into `application-api` build project with 
   `mvn clean install` install option is important here because other projects
   using application-api dependecy so they have to be installed into you local
   maven repository.
2. Got to `application` build project with `mvn clean package`
3. Repeat step 2 for the `dynamic` project


## Runnging
1. Start wildfly application server in standalone mode look into Wildfly topic if
   you unsure what to do
2. Copy artifact `application-api\target\application-api.jar` to wildlfy
   `standalone\deployments` folder after a couple of seconds youw will see 
   `application-api.jar.deployed` in `standalone\deployments` folder
3. Repeat step 2 for `application\target\application.war` artifact
4. Now open `http://localhost:8080/application/?name=Thomas` for example 
   in you favorite browser you will see json response object.
5. Now open `http://localhost:8080/application/?name=Thomas&module=dynamic/RussianGreetings` 
   and you will see the same greetings message with some warning messages because
   dynamic module is not loaded yet.
6. Keep wildfly running and repeat step 2 for `dynamc\target\dynamic.jar`.
7. After you see file `dynamic.jar.deployed` in `standalone\deployments` folder
   repeat step 5 you will see the russian greeting and warning messages will be gone.
8. Rename file `standalone\deployments\dynamic.jar.deployed` to
   `standalone\deployments\dynamic.jar.doundeploy` to undeploy dynamic component
9. After you see file `dynamic.jar.undeployed` in `standalone\deployments` folder.
   Repeat step 5 you will see fallback greetings and warnings again.
10. You can rename `dynamic.jar.doundeploy` to  `dynamic.jar.dodeploy` to deploy
   dynamic module one more time again.

## Facit
For my opinion javaee is great technology but sometime realy hard to understand
this example demostrate capabilities for modular and extensible webapplication 
that can be extended or reconfigured without application downtime.
