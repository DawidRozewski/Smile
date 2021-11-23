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
<strong><a href="" class="home">Strona główna</a></strong>
<strong><a href="/app/services" class="home">Cennik zabiegów</a></strong>
<strong><a href="/patient/appointment" class="home">Zaplanuj wizytę</a></strong>
<strong><a href="/login" class="home">Zaloguj</a></strong>
<strong><a href="/app/register" class="home">Zarejestruj</a></strong>
<strong><a href="/app/about" class="home">O mnie</a></strong>
<strong><a href="/app/contact" class="home">Kontakt</a></strong>
</div>
<br/>
<br/>
<br/>
<br/>
<br/>

<h3><a href="http://localhost:8080/app/patient/dashboard">Pacjent</a></h3><br/><br/>
<h3><a href="http://localhost:8080/app/doctor/dashboard">Doktor</a></h3><br/><br/>
<h3><a href="http://localhost:8080/app/admin/patients">Admin</a></h3>