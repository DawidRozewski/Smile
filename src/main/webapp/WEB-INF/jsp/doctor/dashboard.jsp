<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="temps/header.jsp"%>
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
    <script type="text/javascript">
        function clearSearch() {
            window.location = "http://localhost:8080/app/doctor/dashboard";
        }
    </script>
</head>

<h3>Znajdź pacjenta: </h3>
<form method="get" action="/app/doctor/dashboard">
    Pesel: <input type="text" name="pesel"  size="15" value="${pesel}"/>
    <input type="submit" value="Szukaj" />
    <input type="button" value="Wyczyść"  onclick="clearSearch()" />
</form>

<table>
    <tr>
        <th>ID</th>
        <th>Imię i nazwisko</th>
        <th>Pesel</th>
        <th>Pokaż dane</th>

    </tr>

    <c:forEach var="p" items="${patients}">
        <tr>
            <td>${p.id}</td>
            <td>${p.fullName}</td>
            <td>${p.pesel}</td>
            <td><a href="/app/doctor/patient/${p.id}">Pokaż</a></td>
        </tr>
    </c:forEach>

</table>

</body>