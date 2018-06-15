# springboot_tute
# Tutorial On SpringBoot
https://developer.okta.com/blog/2017/12/06/bootiful-development-with-spring-boot-and-react
Normally, to get used to a language or framework, I build a giflooper. It's my version of a 'Hello world' that lets me feel vaguely confident going forwards with a tech. Usually, I have the front end as 2 pages: a search term entry page, and a results page. However, we started using React at work and although I felt OK tweaking little parts of it, I wanted to get better at it and I thought the best way would be to hack together a giflooper frontend with it. Also, those 2 pages could very easily be one page, and React lets you do that quite easily.

Also I wanted to know how to build a UI and an API as separate apps. We do this at work, and it seems a cool way to make both parts reusable but I wasn't confident on exactly how to do this, and again this demo checked that box. I'm not really a beer guy, so I might replace it with something else, like Arsenal footballers or guitars.

Also, this demo taps into GIPHY, which is a huge plus, as that's the API I wanna use.

# Spring so far
All the Javaheads tell me about its robustness and configuration options, but being a relative code newbie these benefits aren't immediately obvious to me, and the range of configuration options are a bit baffling. From a python background, where you set up a local environment and clone requirements into it, the Java equivalent is immediately more complex it feels. Especially with the choice between Maven and Gradle. At work we use Maven, so it's good the demo does too.

# https://start.spring.io/

The demo advises starting at this spring.io site. Seems legit, with the spring logo and all, but already the fact that we use a UI to initialise this scares me a bit. I mean the UI itself simplifies it, but I've not had to do this before. We specify the following dependencies:
-H2: An in-memory database
-JPA: Standard ORM for Java
-Rest Repositories: Allows you to expose your JPA repositories as REST endpoints
-Web: Spring MVC with Jackson (for JSON), Hibernate Validator, and embedded Tomcat
From what I gather these are like python packages, and live in the pom.xml file. So again, these dependencies are equivalent to requirements, and are outlined in the pom.xml file, which is equivalent to the 'requirements.txt' of python files.
Looks like we get everything needed for a simple CRUD site. The H2 will let us persist the users actions on database objects, the JPA will allow us to perform Database Operations within the Java code, the Rest Repositories will allows use to expose our Database through REST, meaning that our eventual React frontEnd will be able to play with it.

I am not sure what the 'Artifact' and 'Group' configurations mean.
Seems like 'Artifact' is the project's name.

```
Error: Could not find or load main class org.apache.maven.wrapper.MavenWrapperMain
Caused by: java.lang.ClassNotFoundException: org.apache.maven.wrapper.MavenWrapperMain
```
I got this error for the longest time when trying to run the command '$./mvnw spring-boot:run'. The demo suggested you could run it from within the IDE, so I gave that a shot.
I had to get back into IntelliJ. I don't mind IntelliJ at all, but again it does so much for you that it really exposes your Java inexperience. I guess that's also good as you get a strong idea of what you don't know. I'm rambling.
One issue was setting the ting up with an SDK. I couldn't really figure out the difference between an SDK(Software Developer Kit) and a JDK(Java Developer Kit). I looked it up - An SDK is a generic term for a Developer Kit, whereas JDK applies exclusively to the Java domain.
For now it seems ok to continue running the app from the IDE. I still don't get why it's failing though :( .

Next step:
```
Create a com.example.demo.beer package and a Beer.java file in it. This class will be the entity that holds your data.
```
What's a package in Java? I found this cool definition "A package is a namespace that organizes a set of related classes and interfaces. Conceptually you can think of packages as being similar to different folders on your computer. You might keep HTML pages in one folder, images in another, and scripts or applications in yet another."

```
package com.example.demo.beer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Beer {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Beer() {}

    public Beer(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
```
Here's the first class they want in the beer package. There's a few things here I don't get.

What do the imports do?
The @Id annotation marks a field as a primary key field. When a primary key field is defined the primary key value is automatically injected into that field by ObjectDB.

The @GeneratedValue annotation specifies that the primary key is automatically allocated by ObjectDB. During a commit the AUTO strategy uses the global number generator to generate a primary key for every new entity object. These generated values are unique at the database level and are never recycled.

@Entity: An entity is a lightweight persistence domain object. Typically an entity represents a table in a relational database, and each entity instance corresponds to a row in that table. The primary programming artifact of an entity is the entity class, although entities can use helper classes.
@Entity annotation over a class defines that, it has a distinct separate existence. Thus we can run DB queries, without being dependent on any other class.Entities have an identity and can be queried for.
An entity class is an ordinary user defined Java class whose instances can be stored in the database.

So it looks like entity is used to map an object directly to a DB.


What does the constructor do / how does it work?
