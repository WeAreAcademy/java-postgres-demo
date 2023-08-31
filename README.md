# java-postgres-demo
This is a simple java project demonstrating postgres connectivity with jdbc 
(It is an intellij idea project)




# installation (under Intellij IDEA)
You'll need to:

* Download a postgresql jar from https://jdbc.postgresql.org/download/
* save it into a new subdirectory of the project, `ext-jars`
* Either Add it as a module in module settings as follows:
  * Right click on your project name in the project view
  * Choose "Open module settings"
  * Press + to add a module,
  * choose "JARs or directories"
  * then select the downloaded jar file under ext-jars
* or alternatively edit `FirstDBKata.iml` to have the correct path to, and filename for, the downloaded jar.

In more advanced projects, you'll find a maven or gradle project configuration that takes care of downloading such dependencies for you.


