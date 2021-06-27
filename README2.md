In our second release we have implemented 2 main functionalities,

Functional Requirement 5 

Which lets the users to get updates whenever there is a change in one of their reports that the user submitted. This is shown whenever the user logs into the system. The user will have notifications pop up which will tell them the new version of the report.

Functional requirement 7

Func req 7 lets the users to communicate with each other, the users select another user and they can send message to the user by selecting their IDs. Whenever the other user that recieved message logs in the user will see a notification about the new messages and will see the new messages.


Also we have implemented the junit tests that checks the functions inside CrimeReport class with assertequals and assertnotsame. The tests return all green notification back and the program works correctly. 

By making the test I have found out that the ID of users were assigned wrongly. Which created a huge logic problem in the project. I could not find the root cause before the testing but right now thanks to junit all of the programs components works just as intended.


We only excluded the functional requirements of the admins. We could not implement relational databases with JDBC in our project because of the lack of time. In the last release all of the information will be carried from memory into a relational database that we will ship with our application.

The admin functional requirements will not take that much time as our project is coded with Object Oriented Manner,we just need to extend and manipulate some classes. After manipulating and extending them we will have a complete project.


The version 3 will be shipped with sqlite preferrably, all of the functions that take part in creation of objects will go through the filter of the database functions and they will be kept private. The functions will also use Prepared Statement to negate SQL injection attacks.
