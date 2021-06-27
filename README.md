# CFMS2_FarukBurak-HaticeNur-Burak

The functional requirements done in this release

Functional Requirement 2

Users shall be able to add and edit complaints and crime reports.

This functional requirement was done by pair programming with the developers Faruk Burak Gürel and Hatice Nur Ruşen. We have created multiple classes with object oriented programming which gives information easier to the user. It has multiple design patterns including singleton,facade and factory. Everything was coded regarding object oriented process. The data for the users, reports and people is kept in the memory inside Hashmap at the moment within the userholder and personholder classes. In the future sqlite will be implemented with the program.

Functional Requirement 3 

This functional requirement was coded by Faruk Burak Gürel, users are able to get into the system via a login page that is given in the terminal. The passwords are kept in the database with sha256 and md5 hash combination. The user is derived from the user interface, the basic user is polymorphed from user interface to the NormalUser. The user is implemented in the BasicUser abstract class with the common functions like set and get and functions that will be used with every class. In the future when we need the admin account we will simply extend the basicuser.


Non Functional Requirement 3

The passwords are kept in the memory with hashed version. They are firstly hashed with sha256 and than the output is hashed with md5. The combination of those two is important and enough for the project.


Requirements that are not done

Functional requirement 1

This requirement was not finished because of miscommunication between teammates, time management and program schedulability.


Other Requirements that is not implemented

The other requirements were mostly high level and they will require much less effort in the next releases. They can be derived easily with oop. Other requirements were not implemented because of the functional requirement 1 not being implemented.
