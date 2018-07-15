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
