<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<style>
    .test {
        padding: 22px;
        border: brown;
    }
    .back {
        background-image: url("/img/tooth1.jpg");

    }
</style>

<head>
<strong><a href="/login" class="test">Strona główna</a></strong>
<strong><a href="/patient/dashboard" class="test">Dashboard</a></strong>
<strong><a href="/patient/my-treatment"class="test">Plan leczenia</a></strong>
<strong><a href="/patient/history"class="test">Historia wizyt</a></strong>
<strong><a href="/patient/services" class="test">Cennik zabiegów</a></strong>
<strong><a href="/patient/appointment"class="test">Zaplanuj wizytę</a></strong>
<strong><a href="/patient/edit"class="test">Edytuj dane</a></strong>
<strong><a href="/patient/login"class="test">Kontakt</a></strong>
<strong><a href="/patient/logout"class="test">Wyloguj</a></strong>
</head>
<body class="back">

</body>