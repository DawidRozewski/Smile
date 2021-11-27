<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="temps/header.jsp" %>

<h2>Zaplanuj wizytę</h2>

<head >
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<form:form modelAttribute="appointment" method="post">
    <form:hidden path="id"/>
    <form:hidden path="doctor" value="${doctor.id}"/><br/>
    <form:hidden path="patient" value="${patient.id}"/><br/>
    <form:hidden path="serviceDescription" value="${service.description}"/>
    <form:hidden path="price" value="${service.amount}"/>

    Wybierz date: <form:input path="date" type="date"/>
                  <form:errors path="date" cssClass="error"/><br/>

    Wybierz godzinę: <form:select path="time" items="${hours}" /><br/>

    <input type="submit" value="Zarezerwuj">
    
</form:form>
