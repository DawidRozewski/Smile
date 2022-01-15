<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<head>
    <style>
        div#menu {
            display: flex;
            justify-content:space-between;
        }

    </style>
</head>

<div id="menu"><strong><a href="/app" class="test">Home</a></strong> &nbsp;&nbsp;&nbsp;&nbsp;
    <strong><a href="/app/patient/dashboard" class="test">Dashboard</a></strong> &nbsp;&nbsp;&nbsp;&nbsp;
    <strong><a href="/app/patient/my-treatment"class="test">My treatment plan</a></strong> &nbsp;&nbsp;&nbsp;&nbsp;
    <strong><a href="/app/patient/history"class="test">History of my visits</a></strong> &nbsp;&nbsp;&nbsp;&nbsp;
    <strong><a href="/app/patient/services" class="test">Services</a></strong> &nbsp;&nbsp;&nbsp;&nbsp;
    <strong><a href="/app/patient/edit"class="test">Edit profile</a></strong> &nbsp;&nbsp;&nbsp;&nbsp;
    <strong><a href="/app/contact"class="test">Contact</a></strong> &nbsp;&nbsp;&nbsp;&nbsp;
    <strong><a href="/logout"class="test">Logout</a></strong> &nbsp;&nbsp;&nbsp;&nbsp;
</div>
<hr/>