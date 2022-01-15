<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="../patient/temps/header.jsp" %>
<h2>Edit personal data</h2><br/>

<form:form modelAttribute="patient">
    <form:hidden path="id"/>
    <form:hidden path="password" />
    <form:hidden path="doctor.id" items="${doctors}" itemLabel="fullName" itemValue="id"/>
    <form:hidden path="processingOfPersonalData" />
    <form:hidden path="email" />
    <form:hidden path="pesel" />

    First name: <form:input path="firstName"/><br/>
    <form:errors path="firstName" cssClass="error"/><br/>

    Last name: <form:input path="lastName"/><br/>
    <form:errors path="lastName" cssClass="error"/><br/>

    Phone number: <form:input path="phoneNumber"/><br/><br/>
    <form:errors path="phoneNumber" cssClass="error"/><br/>

    <input type="submit" value="Submit">

</form:form>

<br/>
