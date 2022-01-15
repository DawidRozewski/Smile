<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="../doctor/temps/header.jsp" %>
<head >
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<h2>Edit personal data</h2><br/>

<form:form modelAttribute="doctor">
    <form:hidden path="id"/>
    <form:hidden path="password" />
    <form:hidden path="email" />

    First name: <form:input path="firstName"/><br/>
    <form:errors path="firstName" cssClass="error"/><br/>

    Last name: <form:input path="lastName"/><br/>
    <form:errors path="lastName" cssClass="error"/><br/>

    Phone number: <form:input path="phoneNumber"/><br/><br/>
    <form:errors path="phoneNumber" cssClass="error"/><br/>

    <input type="submit" value="Submit">

</form:form>

<br/>
