<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<head>
    <style>
        .error {
            color: red;
        }
    </style>
</head>

<h2>Dodaj plan</h2>

<form:form modelAttribute="treatment">
    <form:hidden path="id"/>

    Numer wizyty: <form:input type="number" path="visitNumber"/> <br/>
    <form:errors path="visitNumber" cssClass="error"/><br/>

    Opis: <form:textarea path="description" cols="80" rows="10"/><br>
    <form:errors path="description" cssClass="error"/> <br>

    Czas: <form:input type="number" path="time"/><br/>
    <form:errors path="time" cssClass="error"/><br/>

    Wizyta: <form:input path="visitDate" type="date"/><br/>
    <form:errors path="visitDate" cssClass="error"/><br/>

    <form:hidden path="patient" value="${patient.id}" name="patient"/> <br/>

    <form:hidden path="doctor" value="${doctor.id}"  name="doctor"/><br/>

    <input type="submit" value="Dodaj">
</form:form>
