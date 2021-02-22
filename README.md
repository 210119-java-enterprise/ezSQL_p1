# ezSQL
ezSQL is an ORM that allows a programmer to access simple SQL functionality by using annotations on their POJOs to transfer field data to columns on their tables.
The intention of this tool is to skip the middle man of establishing jdbc calls. Full functionality will include the ability to draft any kind of SQL Statement using the tool.

## Using ezSQL
To use ezSQL, using a Maven project, include the following within a dependency tag:

groupId: com.revature 

artifactId: ezSQL

version: 1.0-SNAPSHOT
        
Tags to use when utilizing ezSQL are as follows:
@Entity (tableName = "Example")
@Column (tableName = "Example")

Using these tags to mark the Class desired as a Table, and the Fields desired as Columns is critical to usage of the project.

An initial Session should be created to access functionality of the project. From this class you will be able to execute different calls upon your database.
Your declaration for a Session should be called with ConnectionFactory, as follows:

Session mySession = ConnectionFactory.startConnectoin(Path pathToApplications.properties);

An applications.properties file is also critical to using this project. It should include three marked fields:
url = jdbc:postgresql://[Endpoint]:5432/[DatabaseName]
admin-usr = admin username
admin-pw = admin password

Please insert your own Endpoint, DatabaseName, admin user and pass.

Once these are done, you will be able to access AddAnnotatedClass, Add, Remove, and Update from the Session class.

## Existing Features
All database calls are currently only accepting String values until further notice. Please accomodate this by using varchar for simple databases.
Session.addAnnotatedClass should receive a .class call of the annotated class within your project. This will allow the class to be recognizable on any calls made to the database.
Session.add will insert values into an existing database.
Session.remove will remove all cases of the given user from the existing database.
Session.update will update all cases of the given user with the new passed value.
