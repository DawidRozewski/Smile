<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<head>
    <title>Cennik zabiegów</title>
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
        <th>Id</th>
        <th>Opis zabiegu</th>
        <th>Czas</th>
        <th>Cena</th>
        <th>Zaplanuj wizyte</th>
    </tr>


    <%--    <c:forEach var="" items="${}">--%>
    <tr>
        <td>1</td>
        <td>Usuwanie kamienia</td>
        <td>60 minut</td>
        <td>200 zł</td>
        <td><a href="">Zaplanuj wizyte</a> </td>
    </tr>
    <%--    </c:forEach>--%>
<br/>
    <a href="">Dodaj zabieg</a>
</table>

</body>