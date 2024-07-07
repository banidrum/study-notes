Exception - You decide if you throw it or not (but of course, you should treat errors)

JDBC - Depends on the database implementation

JPA - Independent of database implementation, your scripts will run the same way, it doesn't matters if you are using Postgres or MySQL.

.equals() tests for value equality, == tests if it is the same object (hashCode could be used to do tha as well maybe?)

Calling equals on a null object throws an exception