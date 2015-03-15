# bits-please
I believe the .java file names are named fairly well to explain what they are meant to do.

The main class is located in BitsPlease.java

you can try and compile to see what happens. If you have a problem, I assume your problem might be with the file BitsPleaseDB.java

Java 7 has an SQL database.  If not everything compiled you will probably need to create paths with your IDE to derby.jar, derbyclient.jar, derbynet.jar, and derbytool.jar
----they are located wherever you JDK files are:
-----typically at  C:\Program Files\Java\jdk1.7.x.x.x+\db\lib


Once you have been able to compile everything. You will need to run BitspleaseDB (the main might have been commented out, or it can be created from BitsPlease.java, just look and figure it out) and that will create a database with one table, and it has a username and password.

You get that working, then you can  run BitsPlease, it will run and look like the login page on our prototype. If everything has worked right up til now, you should be at a login  page and be able to enter the following to access the rest of the pages:

username: admin
password: 123456

shall now see the main menu, all pages after that are not complete, the DB is not complete.

hopefully this all looks good to you all and we can move forward with this. If not lets figure it out and hop along Cassidy.
