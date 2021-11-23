<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="dashboard.jsp"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<hr>
<head>
    <title>Plan leczenia</title>
    <style>
        table, th, td {
            border-collapse: collapse;
            border: 1px solid black;
            padding: 5px;
        }
        th {
            background-color: #ccc;
        }
    </style>
</head>

<table>
    <tr>
        <th>Kolejność wizyt</th>
        <th>Opis zabiegu</th>
        <th>Czas</th>
        <th>Data</th>
        <th>Zarezerwuj wizytę</th>
    </tr>

    <c:forEach var="t" items="${treatmentPlan}">
        <tr>
            <td>${t.id}</td>
            <td>${t.description}</td>
            <td>${t.time}</td>
            <td>${t.visitDate}</td>
            <td><a href="/}">Rezerwuj termin</a></td>
        </tr>
    </c:forEach>
</table>

</body>