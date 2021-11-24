<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="temps/header.jsp" %>

<h2>Zaplanuj wizytę</h2>

<form:form modelAttribute="appointment" method="post">
    <form:hidden path="id"/>

    Wybierz date: <form:input path="start" type="date"/>
    <form:errors path="start" cssClass="error"/><br/>

    Opis: <form:input path="treatmentDescription"/>
    <form:errors path="treatmentDescription" cssClass="error"/><br/>

    Cena: <form:input path="price"/>
    <form:errors path="price" cssClass="error"/><br/>

    <input type="button" value="Zarezerwuj">
    
</form:form>
