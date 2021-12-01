<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="temps/header.jsp" %>
<!-- koontynuacja zmian żeby plik js był dostępny i możliwy do uruchomienia --->
<script src="<c:url value="/app.js"/>" type="text/javascript"></script>
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

    Wybierz datę: <form:input id="pick_date"   path="date" type="date" value="${today}"/>
                  <form:errors path="date" cssClass="error"/><br/>

    Wybierz godzinę: <form:select id="pick_hour" path="time" items="${hoursDay}" /><br/>

    <input type="submit" value="Zarezerwuj">
    
</form:form>
