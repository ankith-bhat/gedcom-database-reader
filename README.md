# gedcom-database-reader
A free-to-use and free-to-modify project that allows users to read from a GEDCOM formatted file and insert the data into a SQL database

Created by Ankith Bhat and Amith Bhat

Assumptions:
- The GEDCOM file follows a linked-lineage structure
- Custom attributes (e.g. FLGS) are ignored

The following things need to be installed and setup:
-

For Windows:
-
- Java with JRE 11 or more recent version:
- MySQL database: https://dev.mysql.com/doc/mysql-getting-started/en/

For Linux/MacOS:
-
- Java with JRE 11 or more recent version:
- MySQL JDBC Driver: https://dev.mysql.com/downloads/connector/j/
- MySQL database: https://dev.mysql.com/doc/mysql-getting-started/en/


If on Ubuntu Linux, do this extra step:
https://marksman.wordpress.com/2009/03/01/setting-up-mysqljdbc-driver-on-ubuntu/

// CLASSPATH JDBC DRiver when running todo
C:\Program Files (x86)\MySQL\Connector J 8.0\mysql-connector-java-8.0.19.jar


How to run programs (todo):
-
- "create"
- "drop"
- "parse [file]"
