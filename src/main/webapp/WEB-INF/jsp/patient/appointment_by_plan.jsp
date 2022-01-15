<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="temps/header.jsp" %>
<script src="<c:url value="/app.js"/>" type="text/javascript"></script>

<h2>Book an appointment</h2>
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
    <form:hidden path="serviceDescription" value="${treatment.description}"/>
    <form:hidden path="price" value="${treatment.price}"/>

    Pick a date: <form:input id="pick_date" path="date" type="date" value="${treatment.visitDate}"/>
                  <form:errors path="date" cssClass="error"/><br/>

    Pick an hour: <form:select id="pick_hour" path="time" items="${hoursDay}" /><br/>

    <input type="submit" value="Book now">

</form:form>
