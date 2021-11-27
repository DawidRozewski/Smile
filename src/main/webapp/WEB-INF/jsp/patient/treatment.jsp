<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="temps/header.jsp" %>
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
        <th>Data</th>
        <th>Cena</th>
        <th>Zarezerwuj wizytę</th>
    </tr>

    <c:forEach var="t" items="${treatmentPlan}">
        <tr>
            <td>${t.visitNumber}</td>
            <td>${t.description}</td>
            <td>${t.visitDate}</td>
            <td>${t.price}</td>
            <td><a href="/app/patient/appointment-by-plan?planID=${t.id}">Zaplanuj wizyte</a></td>
        </tr>
    </c:forEach>
</table>

</body>