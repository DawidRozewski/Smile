<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="dashboard.jsp"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<hr>
<h2>Zaplanuj wizytę</h2>

<form:form modelAttribute="appointment">

    Wybierz zagieg: <form:select path="services.id" items="${services}" itemLabel="description" itemValue="id"/><br/>
    <form:errors path="description" cssClass="error"/><br/>

    Wybierz date: <form:input path="start" type="date"/>
    <form:errors path="start" cssClass="error"/><br/>

</form:form>



