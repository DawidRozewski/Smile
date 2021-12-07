<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="../patient/temps/header.jsp" %>
<h2>Edytuj dane</h2><br/>

<form:form modelAttribute="patient">
    <form:hidden path="id"/>
    <form:hidden path="password" />
    <form:hidden path="doctor.id" items="${doctors}" itemLabel="fullName" itemValue="id"/>
    <form:hidden path="processingOfPersonalData" />
    <form:hidden path="email" />

    Imie: <form:input path="firstName"/><br/>
    <form:errors path="firstName" cssClass="error"/><br/>

    Nazwisko: <form:input path="lastName"/><br/>
    <form:errors path="lastName" cssClass="error"/><br/>

    Pesel: <form:input path="pesel"/><br/>
    <form:errors path="pesel" cssClass="error"/><br/>

    Nr. kontaktowy: <form:input path="phoneNumber"/><br/><br/>
    <form:errors path="phoneNumber" cssClass="error"/><br/>

    <input type="submit" value="Zapisz dane">

</form:form>

<br/>
