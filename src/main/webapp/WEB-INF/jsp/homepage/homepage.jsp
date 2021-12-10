<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
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
<strong><a href="/app" class="home">Strona główna</a></strong>
<strong><a href="/app/services" class="home">Cennik zabiegów</a></strong>
<strong><a href="/login" class="home">Zaloguj</a></strong>
<strong><a href="/app/register" class="home">Zarejestruj</a></strong>
<strong><a href="/app/about" class="home">O nas</a></strong>
<strong><a href="/app/contact" class="home">Kontakt</a></strong>
<strong><a href="/logout" class="home">Wyloguj</a></strong>
</div>
<hr/>
<br/>
<div id="users">
<h3><a href="http://localhost:8080/app/patient/dashboard">Pacjent</a></h3><br/><br/>
<h3><a href="http://localhost:8080/app/doctor/dashboard">Doktor</a></h3><br/><br/>
<h3><a href="http://localhost:8080/app/admin/patients">Admin</a></h3>
</div>
<body>

</body>