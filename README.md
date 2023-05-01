# Spring-Library-API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)

## Table of Contents

- [Description](#description)
- [Approach](#approach)
- [ToolsAndTech](#toolsAndtech)
- [User-Story](#user-Story)
- [Roles](#roles)
    - [Backend](#backEnd)
- [Deployment](#deployment)
    - [Demo](#demo)
    - [Clone](#clone)
- [Features](#features)
    - [HurdlesAndUnsolved](#hurdlesAndunsolved)
- [Credits](#credits)

## Description
Resister if you are new or login if you are coming back! Create, update, list all(or by id) and delete books to help maintain your own personal library.
## Approach
I started with heavy planing for the first couple hours. I made the ERD diagram, user stories, made a board of steps that I could check off, and reviewed my plan/design with a partner.
Then I focused on the model/tables and the relationship between them following the ERD diagram that I made. Planning was key for a project like this because of all the moving parts. Next was 
making sure the API end points had full CRUD(create, read,update, and delete) functionality. When that was completed I worked on security making some end point private and some public depending on the needs.

Pushing often, keeping to DRY and KISS approaches to code were my main priorities. Taking advantage of feature branches and making docstrings to keep track of code really helped.
![Api.](/src/main/java/com/bookkeeper/library/img/book-library-api.JPG)
(https://github.com/users/Dommy99/projects/3/views/1)
## ToolsAndTech
- Springboot
- JPA repository
- Json Web Tokens
- Postgresql
## User-Story
- As a user, I should be able to login or register.
- As a user, I should be able to create, update, list all(or by id) and delete books.
- As a user, I should not be able to create, update, list all(or by id) and delete books if I'm not logged.

![ERD.](/src/main/java/com/bookkeeper/library/img/library%20erd.png)


## Roles

### BackEnd
- Dominique Akers
    - Java

### Clone
1. Head to the [GitHub Repo](https://github.com/Dommy99/spring-library)
2. Click the green code.
3. Click the copy button.
4. Go to Terminal apply the following command.
```bash
git clone "add clone text here"
```
### Installation

-[Spring Jpa](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa/3.0.6)  
-[Spring Security](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security)
-[Spring Boot Starter](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter/3.0.6)
-[Spring Boot Starter Web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web/3.0.5)  
-[PostGres Driver](https://mvnrepository.com/artifact/org.postgresql/postgresql/42.6.0)

## End Points

| Request Type | URL                         | Functionality                              | Access  |
|--------------|-----------------------------|--------------------------------------------|---------|
| POST         | /auth/author/login/         | Author login                               | Public  |
| POST         | /auth/author/register/      | Author registration                        | Public  |
| GET          | /api/books                  | retrieving all books                       | Private |
| GET          | /api/books/{bookId}         | retrieving a single book by ID             | Private |
| POST         | /api/books                  | creating a new book                        | Private |
| PUT          | /api/books/{bookId}         | updating a single book by ID               | Private |
| DELETE       | /api/books/{bookId}         | deleting a single book by ID               | Private |
| GET          | /api/genres/{genreId}/books | retrieving all books with a given genre ID | Private |
| POST         | /api/books/{bookId}/genre   | creating a new genre for a book            | Private |
| POST         | /api/genres/{genreId}/books | creating a new book                        | Private |
| DELETE       | /api/genres/{genreId}       | deleting a genre by ID                     | Private |

## Features
- Full CRUD functionality
- User login
- User register
- Keep track of multiple game rounds with a win counter.
### Future Features
- More tables including book length

## HurdlesAndUnsolved
- Adding security to the api end points
- Getting full CRUD functionality
- Adding user authentication to genre
- Displaying the list of books attached to the genre
- Adding genre name to newly created book

## Credits
- Shout out to [Kevin-Barrios](https://github.com/dayjyun),[Jeff-Ou](https://github.com/pophero110),[Jay Padilla](https://github.com/Jaypad07),[Obinna Umerah](https://github.com/ObinnaUmerah) & [Maksym-Zinchenko](https://github.com/maklaut007) for help with bug fixes and code reviews.
- [JAVA API](https://docs.oracle.com/en/java/javase/17/docs/api/index.html)
- [Spring Framework Documentation](https://docs.spring.io/spring-framework/docs/current/reference/html/)