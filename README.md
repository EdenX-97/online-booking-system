# Online booking system for COVID-19 nucleic acid testing (OBS-C19T)
![image](https://img.shields.io/badge/Type-Application-important)
![image](https://img.shields.io/badge/Language-Java-red)
![image](https://img.shields.io/badge/Language-Jsp-blue)
![image](https://img.shields.io/badge/Version-0.0.1-green)


## Table of Contents
- [Online booking system for COVID-19 nucleic acid testing (OBS-C19T)](#online-booking-system-for-covid-19-nucleic-acid-testing-obs-c19t)
  - [Table of Contents](#table-of-contents)
  - [Background](#background)
  - [Install](#install)
  - [Run](#run)
  - [Dependencies and version](#dependencies-and-version)
  - [Functions](#functions)
  - [License](#license)

## Background
The COVID-19 is currently one of the most important issues facing every country in the world. The data from wikipedia shows there are 59,951 cases in Australia. Therefore booking a test is also crucial for people who are not sure whether they have contracted the new coronavirus.

For people who want to be vaccinated or tested, sometimes it is not easy to find relevant channels. Generally speaking, local government or medical department websites will display some information, but the main business of these websites is not testing after all. At the same time, there will be much misinformation on the Internet to influence people's actions.

Therefore, people often need to click to jump to several web pages, or even fill in e-mail inquiries, etc., to find the right channel. In addition, most users will need a way to store their test records, so as not to find them in countless emails and text messages when needed.

> This project is the assignment for elec5619 in USYD in 2021 Semeter 2

## Install
1. Java install
   - Please refer to [java website](https://www.java.com/)
   - Note: Please use java11 for this project
2. IDEA install
   - Suggest to use [IntelliJ IDEA](https://www.jetbrains.com/idea/) to run this project.

## Run
1. Download source code
2. User IDEA to open the project from source code
3. Set configuration of java version to java11
4. Run `OBSApplication.java` in path: `/src/main/java/net/obs`

## Dependencies and version
- Java: 11
- Maven: 4.0.0
- spring-boot-starter-parent: 2.0.4.RELEASE
- jaxb-api: 2.3.0
- javassist: 3.23.1-GA
- spring-boot-starter-web: 2.0.4.RELEASE
- spring-boot-starter-data-jpa: 2.0.4.RELEASE
- spring-boot-devtools: 2.0.4.RELEASE
- mysql-connector-java: 5.1.46
- spring-boot-starter-test: 2.0.4.RELEASE
- jstl: 1.2
- tomcat-embed-jasper: 8.5.32
- spring-boot-starter-tomcat: 2.0.4.RELEASE
- bootstrap: 5.1.0
- spring-boot-starter-security: 2.0.4.RELEASE
- spring-security-test: 5.0.7.RELEASE
- spring-boot-starter-mail: 2.0.4.RELEASE
- spring-integration-mail: 5.5.5

## Functions
1. First enter the homepage of the website. On this page, the user can click “User” in the navigation bar at the top of the page to log in or register, or continue to browse as a visitor. Two categories of hospital and clinic are displayed on the homepage, and the user selects one of the categories and enters the search page.
![home](https://github.sydney.edu.au/lwan3349/ELEC5619-Group19/blob/master/IMG/home.png?raw=true)

2. The user can enter the account (phone/mail) and password on this page to log in.
![login](https://github.sydney.edu.au/lwan3349/ELEC5619-Group19/blob/master/IMG/login.png?raw=true)

3. If the user does not already have an existing account, you can enter personal information on this page and complete the registration operation after the SMS verification code/email confirmation.
![register](https://github.sydney.edu.au/lwan3349/ELEC5619-Group19/blob/master/IMG/register.png?raw=true)

4. After selecting the category, the user can enter the name or geographic location of the agency he wants to search in the search box, and after clicking confirm, the page will display a list of all related agencies and brief information. According to the user's positioning, the list can be sorted according to the distance between the agency and the user, so that the user can view the agencies near him. After finding the agency they want, the user can click to view the detailed information of the agency.

Clinic:
![show-clinic](https://github.sydney.edu.au/lwan3349/ELEC5619-Group19/blob/master/IMG/show-clinic.png?raw=true)

Hospital:
![show-hospital](https://github.sydney.edu.au/lwan3349/ELEC5619-Group19/blob/master/IMG/show-hospital.png?raw=true)

5. Users can view all the details of the agency on this page, including Name, Address, Appointment Required, Drive Through Testing, Patient Restriction, Phone, Prescription Required, Website, and location display on Google map. After selecting the date, the user can click “Reserve” to make an appointment and receive a reminder that the appointment is successful. If the user is not logged in, he will receive a prompt to log in.
![make-appointment](https://github.sydney.edu.au/lwan3349/ELEC5619-Group19/blob/master/IMG/make-appointment.png?raw=true)

6. After logging in, the user can click “User” in the navigation bar at the top of the page to view profile and log out.
![user-profile-choose](https://github.sydney.edu.au/lwan3349/ELEC5619-Group19/blob/master/IMG/user-profile-choose.jpeg?raw=true)

7. After clicking to enter the profile page, the user can view all the personal information saved during the previous registration, or directly edit and modify this page, and click “Submit” to save. Users can also click on the left column to reset the password and view the appointment record.
![change-profile](https://github.sydney.edu.au/lwan3349/ELEC5619-Group19/blob/master/IMG/change-profile.png?raw=true)

8. On the reset password page, the user directly sets a new password and clicks submit to save it.
![change-password](https://github.sydney.edu.au/lwan3349/ELEC5619-Group19/blob/master/IMG/change-password.png?raw=true)

9.  On the appointment record page, the user can view all the hospitals or clinics that have been booked, as well as the appointment date and state, or click “Delete” to cancel the appointment.
![delete-appointment](https://github.sydney.edu.au/lwan3349/ELEC5619-Group19/blob/master/IMG/delete-appointment.png?raw=true)
   
10. Users can click "About us" in the navigation bar at the top of the page to view relevant information about the developer.
![about-us](https://github.sydney.edu.au/lwan3349/ELEC5619-Group19/blob/master/IMG/about-us.png?raw=true)

## License
[MIT](LICENSE)
