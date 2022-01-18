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
### Usage  
  ### Annotating classes  
  All classes which represent objects in your database must be annotated.
   - #### @Entity(tableName = "`table name`")
      - ***All objects must have this Annotation***
      - Indicates the table this object is associated with 
   - #### @Id(columnName = "`column name`")
      - Indicates that the Annotated field is the primary key field 
   - #### @Column(columnName = "`column name`", dataType ="`specified data type`", unique ="`false`", nullable="`true`")  
      - **columnName**: Indicates the name of the Annotated field
      - **dataType**: Indicates the datatype of the Annotated field `(varchar, numeric, boolean, etc)`
      - **unique**: (*optional*) Indicates that the Annotated field's column name cannot already exist in the table. Set to `false` by default.
      - **nullable**: (*optional*) Indicates that the Annotated field can be null. Set to `true` by default.
   - #### @JoinColumn(columnName = "`column name`", joinedColumn = "`refrenced column`",joinedTable = "`referenced table`")
      - **columnName**: Indicates the name of the Annotated field
      - **joinedColumn**: Indicates the foreign column that this column is referencing
      - **joinedTable**: Indicates the table that the referenced column comes from
      

### User API  

  - #### `public static Something getInstance()`  
     - returns the singleton instance of the class. It is the starting point to calling any of the below methods.  
  
## License

This project uses the following license: [GNU Public License 3.0](https://www.gnu.org/licenses/gpl-3.0.en.html).
