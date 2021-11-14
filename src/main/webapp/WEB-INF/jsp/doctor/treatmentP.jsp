<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="dashboard.jsp"%>
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
        <th>Kolejnosc wizyt</th>
        <th>Opis zabiegu</th>
        <th>Czas</th>
        <th>Cena</th>
    </tr>


    <%--    <c:forEach var="" items="${}">--%>
    <tr>
        <td>1</td>
        <td>Usuwanie kamienia</td>
        <td>60 minut</td>
        <td>200 zł</td>

    </tr>
    <tr>
        <td>2</td>
        <td>Piaskowanie</td>
        <td>60 minut</td>
        <td>250 zł</td>

    </tr>

    <%--    </c:forEach>--%>
</table>
<br/>
<a href="">Dodaj wizytę</a>

</body>