<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<head>
    <style>
        div#menu {
            display: flex;
            justify-content:space-between;
        }
        div#users {
            position: absolute;
            bottom: 0;
            right: 0;
        }
    </style>
</head>
<div id="menu">
<strong><a href="/app" class="home">Home</a></strong>
<strong><a href="/app/services" class="home">Services</a></strong>
<strong><a href="/login" class="home">Login</a></strong>
<strong><a href="/app/register" class="home">Register</a></strong>
<strong><a href="/app/about" class="home">About us</a></strong>
<strong><a href="/app/contact" class="home">Contact</a></strong>
<strong><a href="/logout" class="home">Logout</a></strong>
</div>
<hr/>
<br/>
<div id="users">
<h3><a href="https://just-smile.herokuapp.com/app/patient/dashboard">Patient</a></h3><br/><br/>
<h3><a href="https://just-smile.herokuapp.com/app/doctor/dashboard">Doctor</a></h3><br/><br/>
<h3><a href="https://just-smile.herokuapp.com/app/admin/patients">Admin</a></h3>
</div>

<div id="background">

</body>
</div>