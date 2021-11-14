<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<h2>Zarejestruj</h2>
<head>
    <style>
        .errors {
            color: red;
        }
    </style>
</head>
<form:form modelAttribute="patient">
    <form:hidden path="id"/>

Imie: <form:input path="firstName"/><br/>
     <form:errors path="firstName" cssClass="errors"/><br/>

Nazwisko: <form:input path="lastName"/><br/>
<form:errors path="lastName" cssClass="errors"/><br/>

Hasło: <form:password path="password" /><br/>
<form:errors path="password" cssClass="errors"/><br/>

Email: <form:input path="email"/><br/>
<form:errors path="email" cssClass="errors"/><br/>

Pesel: <form:input path="pesel"/><br/>
<form:errors path="pesel" cssClass="errors"/><br/>

Nr. kontaktowy: <form:input path="phoneNumber"/><br/>
<form:errors path="phoneNumber" cssClass="errors"/><br/>

Wybierz doktora: <form:select path="doctor.id" items="${doctors}" itemLabel="fullName" itemValue="id"/><br/>
     <form:errors path="doctor" cssClass="errors"/><br/>

 <form:checkbox path="processingOfPD" id="confirm"/><br/>
     <label for="confirm">Zgoda na przetwarzanie danych osobowych.</label><br/>
<form:errors path="processingOfPD" cssClass="errors"/><br/>

<input type="submit" value="Zarejestruj">

</form:form>



