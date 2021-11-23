<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<div id="menu">
    <strong><a href="/app">Strona główna</a></strong>
    <strong><a href="/app/doctor/dashboard">Dashboard</a></strong>
    <strong><a href="/app/doctor/schedule">Grafik</a></strong>
    <strong><a href="/app/doctor/patients">Lista pacjentów</a></strong>
    <strong><a href="/app/doctor/treatment-plan">Plan leczenia</a></strong>
    <strong><a href="/app/doctor/services">Cennik zabiegów</a></strong>
    <strong><a href="/app/doctor/edit">Edytuj dane</a></strong>
    <strong><a href="/app/doctor/dashboard">Kontakt</a></strong>
    <strong><a href="/logout">Wyloguj</a></strong>
</div>
<hr>
<head>
    <title>Cennik zabiegów</title>
    <style>
        table, th, td {
            border-collapse: collapse;
            border: 1px solid black;
            padding: 5px;
        }
        th {
            background-color: #ccc;
        }

        .error {
            color: red;
        }
    </style>
</head>

<table>
    <tr>
        <th>Id</th>
        <th>Opis zabiegu</th>
        <th>RTG</th>
        <th>Czas</th>
        <th>Cena</th>
        <th>Edytuj</th>
        <th>Usuń</th>
    </tr>

        <c:forEach var="s" items="${services}">
    <tr>
        <td>${s.id}</td>
        <td>${s.description}</td>
        <td>${s.RTG}</td>
        <td>${s.time}</td>
        <td>${s.amount}</td>
        <td><a href="">Edytuj</a> </td>
        <td><a href="">Usuń</a> </td>
    </tr>
        </c:forEach>
</table>
<br/>
<h2>Dodaj zabieg</h2> <br/>

<form:form modelAttribute="service">
    <form:hidden path="id"/>

    Opis: <form:textarea path="description" cols="50" rows="10"/><br>
    <form:errors path="description" cssClass="error"/> <br>
    RTG: <form:radiobutton path="RTG" value="Tak"/>Tak
    <form:radiobutton path="RTG" value="Nie"/>Nie<br>
    <form:errors path="RTG" cssClass="error"/> <br>

    Czas: <form:input type="number" path="time"/><br/>
    <form:errors path="time" cssClass="error"/><br/>

    Cena: <form:input path="amount" type="number"/><br/>
    <form:errors path="amount" cssClass="error"/><br/>

    <input type="submit" value="Dodaj">
</form:form>

</body>