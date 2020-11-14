# BookAPI-Spring
It is a project implemented after passing the fifth module of the course in CodersLab. \
This module was focused on __Spring MVC__.
## What programme is that?
The program is used to catalog books using the REST method. \
It supports four __REST methods__: _GET_, _POST_, _PUT_, _DELETE_.

Two operations can be performed on the __/books__ resource:
- download a list of all books;
- send a book that will be added to the book list.

For the resource __/books/{id}__ you can: 
- download book data with a given id; 
- edit the book; 
- remove the book.
## How it works? 
I used __Spring MVC__, the __Jackson__ library, and additional annotations to create the API. \
Unfortunately, at this stage, it does not use data recording, for example to the database, so each time the application is turned off, the data is lost. \
Only books are available, each time added to the book list when creating an instance of the class managing the book list (injecting it into the BookController). \
I used __Postman__ to verify the correctness of the API.
## Additional information:
In the BookController class, I commented on the code that allows adding a book using a prepared form.
