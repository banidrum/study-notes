[[resources-on-distributed-systems]]

CAP Theorem says that in distributed distributed systems, you can have only 2 out of 3 guarantees.

Those guarantees are:

Consistency
	All clients see the same data at the same time

Availability
	The ability of a system to respond to requests of user at all times

Partition tolerance
	Continue operating when a network partition happens

Since we cannot have all three guarantees, we must do trade offs depending on the use case of the application

