# Smile


## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Inspiration](#inspiration)

## General info
It is a web application for booking and managing dental appointments.
An SMS reminder is also sent 3 days before the visit.

## As a patient:
- you can book an appointment
- view the entire history of visits
- download files for each visit
- review (if any) the treatment plan and book another appointment
- edit personal data

## As a doctor:
- view all your patients and search by pesel
- view your visit timesheet and delete an appointment
- you can add / edit / delete the treatment available in the dentist's office
- you can see the history of the patient's visits
- add / edit / delete treatment plan, if required
- see the patient's upcoming visits
- you can add files to each visit
- you can end the patient's visit
- edit personal data

## As admin:
- you can view all patients and add a doctor



## Technologies: 
- spring-boot
- spring-security
- hibernate
- mySql
- lombok
- twilio API

## Setup

[Enter here](https://just-smile.herokuapp.com/app)

If you do not want to create a new account, you can use your existing account to log in:
- **Patient**:
- username: pacjent@gmail.com
- password: Patient123!

- **Doctor**: 
- username: doktor@gmail.com
- password: Doktor123!

The SMS sending method starts every day at **08:00**, so when you book an appointment for 
- **3 days from today, do not forget** to enter your correct phone number :)

## Inspiration

The inspiration to write this application was my girlfriend because she works as a dental hygienist and it happens that patients booked an appointment and did not come.
So I decided to make an application that allows you to manage visits and set an SMS reminder.
