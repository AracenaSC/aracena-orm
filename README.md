# Aracena ORM

## Project Description
A java based ORM for simplifying the process of connecting to and from an SQL database without the need for SQL or connection management.

## Technologies Used
* PostgreSQL - version 42.2.12  
* Java - version 8.0  
* Apache commons - version 2.1  
* JUnit
* Log4J

## Current Features  
* Easy to use and straightforward user API.
* No need for SQL, HQL, or any databse specific language.
* Straightforward and simple Annotation based for ease of use.
* Ability to build tables based on Annotations in Entities.
* Ability to drop tables.

## Upcoming Features
* Implementation of DML operations.
* Implementation of TCL operations.
* Implementation of DCL operations.
* Mapping of join columns inside of entities. 

## Getting Started
Currently project must be included as local dependency. To do so open your terminal of choice and run the following commands:
```shell
  git clone https://github.com/AracenaSC/aracena-orm.git
  cd aracena-orm
  mvn install
```
Next, place the following inside your project's pom.xml file:
```XML
<dependency>
	<groupId>com.revature</group>
	<artifactId>aracenaORM</artifactId>
	<version>0.0.1-SNAPSHOT</version>
</dependency>
```

Finally, you must create a Configuration object and set its connection in a driver class
```java
Configuration config = new Configuration();
config.setConnection("your database url", "your database username", "your database password");
```
## Usage  
  ### Annotating classes  
  All classes which represent objects in your database must be annotated.
   - #### @Entity(name = "table_name)
      - Indicates that this class is associated with table 'table_name'  
   - #### @Column(name = "column_name, dataType ="datatype")  
      - Indicates that the Annotated field is a column in the table with the name 'column_name' and a specified data type
      - E.g. @Column(name = "a_word", dataType ="varchar")

### User API  
  
  - #### `public static Something getInstance()`  
     - returns the singleton instance of the class. It is the starting point to calling any of the below methods.  
  
## License

This project uses the following license: [GNU Public License 3.0](https://www.gnu.org/licenses/gpl-3.0.en.html).
