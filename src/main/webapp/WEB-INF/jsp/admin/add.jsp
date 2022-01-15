<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<h2>Add a doctor</h2>
<hr>
<head>
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
<form:form modelAttribute="doctor">
    <form:hidden path="id"/>

    First name: <form:input path="firstName"/><br/>
    <form:errors path="firstName" cssClass="error"/><br/>

    Last name: <form:input path="lastName"/><br/>
    <form:errors path="lastName" cssClass="error"/><br/>

    Password: <form:password path="password" /><br/>
    <form:errors path="password" cssClass="error"/><br/>

    Repassword: <form:password path="repassword" /><br/>
    <form:errors path="repassword" cssClass="error"/><br/>

    Email: <form:input path="email"/><br/>
    <form:errors path="email" cssClass="error"/><br/>

    Phone number: <form:input path="phoneNumber"/><br/>
    <form:errors path="phoneNumber" cssClass="error"/><br/>

    <input type="submit" value="Submit">

</form:form>
</body>