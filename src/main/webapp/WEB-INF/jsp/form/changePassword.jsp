<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<form:form modelAttribute="patient">

    Hasło: <form:password path="password" /><br/>
    <form:errors path="password" cssClass="errors"/><br/>

    Powtórz hasło: <form:password path="repassword" /><br/>
    <form:errors path="repassword" cssClass="errors"/><br/>

    <input type="button" value="Zapisz">
</form:form>