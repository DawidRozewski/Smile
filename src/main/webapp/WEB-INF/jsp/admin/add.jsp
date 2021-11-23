<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<h2>Dodaj doktora</h2>
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

    Imie: <form:input path="firstName"/><br/>
    <form:errors path="firstName" cssClass="error"/><br/>

    Nazwisko: <form:input path="lastName"/><br/>
    <form:errors path="lastName" cssClass="error"/><br/>

    Hasło: <form:password path="password" /><br/>
    <form:errors path="password" cssClass="error"/><br/>

    Powtórz hasło: <form:password path="repassword" /><br/>
    <form:errors path="repassword" cssClass="error"/><br/>

    Email: <form:input path="email"/><br/>
    <form:errors path="email" cssClass="error"/><br/>

    Nr. telefonu: <form:input path="phoneNumber"/><br/>
    <form:errors path="phoneNumber" cssClass="error"/><br/>

    <input type="submit" value="Dodaj doktora">

</form:form>
</body>