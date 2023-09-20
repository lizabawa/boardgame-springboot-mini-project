# boardgame-springboot-mini-project

## Description
- This is a Springboot API that allows a user to create a profile and login to create categories and boardgames lists.

## Tools and Technologies Used
- Spring Boot DevTools
- Spring Web
- h2 database
- JPA translator
- Spring Security
- JSON Web Token
- [General Assembly Springboot README](https://git.generalassemb.ly/sureshmelvinsigera/Java-Spring-Boot-lecture/blob/spring-2-7-8/README.md#day-01
  )
- [General Assembly Springboot To Do Lab](https://git.generalassemb.ly/java-interapt-7-31/spring-boot-todo-lab)
- [My Class Code Along Food API](https://github.com/lizabawa/food)

## Approach
- I started by creating my user stories and a ERD diagram so that I could brainstorm the direction I wanted my project to take, as well as visualize the relationships between the tables.
- I began my code by adding my model classes and then Spring security along with the appropriate JSON web token dependencies and added methods and classes as needed from there to get my security working correctly. Lastly, I added the CRUD methods.

## Unsolved Problems and Major Hurdles
### Unsolved Problems
- Users cannot create the same category name as another user
- The deleteCategory method is not correctly functioning

### Hurdles
- Getting the security package functioning took the longest. There are so many moving parts and so many other classes and methods that needed to be functioning before I could implement the security measures.

## User Stories
- As a user, I want to login so that I can create categories and boardgame lists.
- As a user, I want to add, update, and delete categories and boardgames, so I can maintain my lists.
- As a user, I want to create my login information, so I can login using that information.
- As a user, I want to login using accurate credentials, so that my account can only be accessed with the accurate credentials.

## ERD Diagram
- I found it easier to create my ERD diagram by hand on a whiteboard than using an online tool. The photo below was not the final version of my ERD diagram but it gave me a good idea of how to organize my code.
![ERD Diagram](https://github.com/lizabawa/boardgame-springboot-mini-project/blob/fa79a37ee68e2c1a36b08e60ed467a0a2797b27e/src/image/ERD%20Diagram.jpeg)


## Planning
- In terms of planning, I utilized an ERD diagram and a [spreadsheet](https://docs.google.com/spreadsheets/d/1aeuLdyFBMQ7PxdJf1v-rc167PWryu8HmBvivUike19s/edit#gid=0)
  for my endpoints. I also utilized //TODO comments to create a to do list in order of priority and began coding by hitting each to-do item.
![Endpoints](https://github.com/lizabawa/boardgame-springboot-mini-project/blob/dc40f95aeda54ec94e1446b11fabbb3f182b2740/src/image/Endpoints.png)

## Dependencies
- Spring Web
- Spring Boot Devtools
- h2 database
- JPA translator
- Spring Security
- JSON Web Token