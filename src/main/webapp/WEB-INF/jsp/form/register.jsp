<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="../homepage/homepage.jsp" %>
<h2>Zarejestruj</h2>
<head >
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<form:form modelAttribute="patient">
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

Pesel: <form:input path="pesel"/><br/>
<form:errors path="pesel" cssClass="error"/><br/>

Nr. kontaktowy: <form:input path="phoneNumber"/><br/>
<form:errors path="phoneNumber" cssClass="error"/><br/>

Wybierz doktora: <form:select path="doctor.id" items="${doctors}" itemLabel="fullName" itemValue="id"/><br/>
     <form:errors path="doctor" cssClass="error"/><br/>

 <form:checkbox path="processingOfPersonalData" id="processingOfPersonalData"/><br/>
     <label for="processingOfPersonalData">Zgoda na przetwarzanie danych osobowych.</label><br/>
<form:errors path="processingOfPersonalData" cssClass="error"/><br/>

<input type="submit" value="Zarejestruj">

</form:form>



