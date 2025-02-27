# socialMedia
Project Overview:

The project is a back-end service of a social media platform that supports user registration, login, post management, likes, comments and other functions.

I wrote a simplified version of the social media platform, which is divided into three modules: user management module, dynamic management module and social interaction module. The following is the mind map I drew during the construction of the framework.
![输入图片说明](https://foruda.gitee.com/images/1739136614901016062/12aa03a8_10747971.jpeg "socialMedia-SocialMedia.jpeg")

Each module

In the system module, the login and registration function of the user is mainly realized, and the two roles of user and admin are added. Unfortunately, the writing time of this project is not enough, and many functions that should be available are not realized. For example, although the role entity is written, the permission is not realized. For example, admin has the right to log in to the background page to see the list of all users, and user can only see their own personal information after login.

In addition, I use jwt to implement single sign-on. After the user logs in, the token is generated and placed in the request header. All subsequent requests will be intercepted to verify the login information.

In the dynamic and interaction module, the functions of user Posting dynamic, like dynamic, comment dynamic and so on are realized.

Tech Stack

This project adopts Spring Boot + MyBatis Plus + MySQL + Liquibase for development. The main technology stack includes:

1. Spring MVC structure: The classic MVC design pattern is adopted to clearly separate the Controller, Service and DAO layers.
2. Spring Boot: Build an efficient Java Web backend framework
3. MyBatis-Plus: Enhanced ORM framework based on MyBatis with less SQL code
4. MySQL: A relational database for storing data from social media platforms
5. Liquibase: Database version management tool for easy database structure changes and version control
6. JWT (jjwt) : User authentication and authorization

# Instructions for use
This project includes not only back-end API, but also a Vue front-end, where users can complete registration, login, post dynamic, like, comment and other functions, so I'm sorry that unit testing is not implemented in the back-end. We hope that front-end and back-end can interact through RESTful apis to make the project more complete.
In addition, the front-end project was not my result, the pages was written by my boyfriend who was used to a front-end engineer.
Overall, this program still contain loads of bugs during short coding period, I will keep updating though.

There is a serious bug which i didn't fix is that when user comment a post, he only can enter number instead of a text.


back-end:
1. Change the mysql username and password in application.yml (launcher/src/main/resources/application.yml)
   
url: jdbc:mysql://localhost:3306/socialMedia?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true

username: #{yourUserName}

password: #{yourPassword}

please don't forget create a database named socialMedia in mysql. (I forgot to write in liquibase, sorry.)

2. LauncherApplication is in the launcher module

front-end:
1. open terminal
2. npm i
3. npm start serve

# please login with following account:
account: user3@163.com
password: securePassword123

# link in github
back-end: https://github.com/jiaqi-yu830625/socialMedia
front-end: https://github.com/jiaqi-yu830625/socialMedia-vue

