Library Management System 

APIs
POST  http://localhost:8090/api/books  (add new book)
GET  http://localhost:8090/api/books  (get all books)
GET http://localhost:8090/api/books/{bookId}  (get specific book)
PUT http://localhost:8090/api/books/{bookId}  (update specifc book)
Delete http://localhost:8090/api/books/{bookId} (remove specific book)

POST http://localhost:8090/api/patrons  (add new Patron)
GET  http://localhost:8090/api/patrons  (get all Patron)
GET http://localhost:8090/api/patrons/{patronId}  (get specific Patron)
PUT http://localhost:8090/api/patrons/{patronId}  (update specifc Patron)
Delete http://localhost:8090/api/patrons/{patronId} (remove specific Patron)


POST http://localhost:8090/api/borrow/{bookId}/patron/{patronId}  (borrow a book)
PUT http://localhost:8090/api/borrow/{bookId}/patron/{patronId}  (return a book)

-----------------------------------------------------
H2 database 
not perfect handling exception , AOP ... etc (due to busy time )
