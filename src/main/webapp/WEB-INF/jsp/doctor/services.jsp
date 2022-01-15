<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="temps/header.jsp" %>
<head>
    <title>Services</title>
    <style>
        table, th, td {
            border-collapse: collapse;
            border: 1px solid black;
            padding: 5px;
        }

        th {
            background-color: #ccc;
        }

        .error {
            color: red;
        }
    </style>
</head>

<table>
    <tr>
        <th>ID</th>
        <th>Description</th>
        <th>RTG</th>
        <th>Price</th>
        <th>Edit service</th>
        <th>Delete service</th>
    </tr>

    <c:forEach var="s" items="${services}">
        <tr>
            <td>${s.id}</td>
            <td>${s.description}</td>
            <td>${s.RTG}</td>
            <td>${s.amount}</td>
            <td><a href="/app/doctor/edit-service/${s.id}">Edit</a></td>
            <td><a href=/app/doctor/remove-service/${s.id}>Delete</a></td>
        </tr>

    </c:forEach>
</table>
<br/>
<h2>Add a service</h2> <br/>

<form:form modelAttribute="service">
    <form:hidden path="id"/>

    Description: <form:textarea path="description" cols="50" rows="10"/><br>
    <form:errors path="description" cssClass="error"/> <br>
    RTG: <form:radiobutton path="RTG" value="Yes"/>Yes
    <form:radiobutton path="RTG" value="No"/>No<br>
    <form:errors path="RTG" cssClass="error"/> <br>

    Price: <form:input path="amount" type="number"/><br/>
    <form:errors path="amount" cssClass="error"/><br/>

    <input type="submit" value="Add">
</form:form>

</body>