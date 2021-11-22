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
    <style>
        table, th, td {
            border-collapse: collapse;
            border: 1px solid black;
            padding: 5px;
        }
        th {
            background-color: #ccc;
        }

        .errors {
            color: red;
        }
    </style>
</head>
<h2>Dane pacjenta</h2>
<table>
    <tr>
        <th>Id</th>
        <th>Imie i nazwisko</th>
        <th>Pesel</th>
        <th>Email</th>
        <th>Nr. kontaktowy</th>
        <th>Historia wizyt</th>
    </tr>
    <tr>
        <td>${patient.id}</td>
        <td>${patient.fullName}</td>
        <td>${patient.pesel}</td>
        <td>${patient.email}</td>
        <td>${patient.phoneNumber}</td>
        <td><a href="/app/doctor/history/${patient.id}">Pokaż</a></td>
    </tr>
</table>
<hr/>

<h2>Plan leczenia</h2>
<table>
    <tr>
        <th>Kolejność wizyt</th>
        <th>Opis</th>
        <th>Czas</th>
        <th>Data wizyty</th>
    </tr>
    <c:forEach var="t" items="${treatmentList}">
        <tr>
            <td>${t.id}</td>
            <td>${t.description}</td>
            <td>${t.time}</td>
            <td>${t.visitDate}</td>
        </tr>
    </c:forEach>
</table>
<h2>Dodaj</h2>
<form:form modelAttribute="treatment">
    <form:hidden path="id"/>

    Opis: <form:textarea path="description" cols="80" rows="10"/><br>
    <form:errors path="description" cssClass="error"/> <br>

    Czas: <form:input path="time"/><br/>
    <form:errors path="time" cssClass="errors"/><br/>

    Wizyta: <form:input path="visitDate" type="date"/><br/>
    <form:errors path="visitDate" cssClass="errors"/><br/>

    <form:hidden path="patient" value="${patient.id}" name="patient"/> <br/>

    <form:hidden path="doctor" value="${doctor.id}"  name="doctor"/><br/>

    <input type="submit" value="Dodaj">
</form:form>

</body>