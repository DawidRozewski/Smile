<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<head>
<strong><a href="/app" class="test">Strona główna</a></strong>
<strong><a href="/app/patient/dashboard" class="test">Dashboard</a></strong>
<strong><a href="/app/patient/my-treatment"class="test">Plan leczenia</a></strong>
<strong><a href="/app/patient/history"class="test">Historia wizyt</a></strong>
<strong><a href="/app/patient/services" class="test">Cennik zabiegów</a></strong>
<strong><a href="/app/patient/appointment"class="test">Zaplanuj wizytę</a></strong>
<strong><a href="/app/patient/edit"class="test">Edytuj dane</a></strong>
<strong><a href="/app/patient/login"class="test">Kontakt</a></strong>
<strong><a href="/logout"class="test">Wyloguj</a></strong>
</head>
<body class="back">

<sec:authorize access="isAuthenticated()">
    <p>Zalogowany jako: <sec:authentication property="principal.username"/></p>
    <p>Posiada role: <sec:authentication property="authorities"/></p>
</sec:authorize>

</body>