<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<form:form modelAttribute="test">

    <form:hidden path="id"/> <br/>

    <form:input path="email"/><br/>
    <form:errors path="email"/><br/>

    <input type="submit" value="Zaloguj"><br/>

</form:form>