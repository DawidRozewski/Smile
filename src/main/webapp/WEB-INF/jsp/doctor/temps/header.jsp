<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<head>
    <style>
        div#menu {
            display: flex;
            justify-content:space-between;
        }
    </style>
</head>

<div id="menu">
    <strong><a href="/app">Home</a></strong>
    <strong><a href="/app/doctor/dashboard">Dashboard</a></strong>
    <strong><a href="/app/doctor/schedule">Schedule</a></strong>
    <strong><a href="/app/doctor/services">Services</a></strong>
    <strong><a href="/app/doctor/edit">Edit profile</a></strong>
    <strong><a href="/logout">Logout</a></strong>
</div>
<hr>