<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="../patient/dashboard.jsp" %>
<h2>Edytuj dane</h2>
<head>
    <style>
        .error {
            color: red;
        }
    </style>
</head>

<form:form modelAttribute="patient">
    <form:hidden path="id"/>
    <form:errors path="phoneNumber" cssClass="error"/><br/>
    <form:hidden path="pesel"/><br/>
    <form:hidden path="password" /><br/>
    <form:hidden path="doctor.id" items="${doctors}" itemLabel="fullName" itemValue="id"/><br/>
    <form:hidden path="processingOfPersonalData" id="processingOfPersonalData"/><br/>

    Imie: <form:input path="firstName"/><br/>
    <form:errors path="firstName" cssClass="error"/><br/>

    Nazwisko: <form:input path="lastName"/><br/>
    <form:errors path="lastName" cssClass="error"/><br/>

    Email: <form:input path="email"/><br/>
    <form:errors path="email" cssClass="error"/><br/>

    Nr. kontaktowy: <form:input path="phoneNumber"/><br/>

    <input type="submit" value="Zapisz dane">

</form:form>

<br/>
