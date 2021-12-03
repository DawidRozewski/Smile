
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>



<head>
    <title>List of Documents</title>
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

<form method="post" action="/app/doctor/uploadFiles" enctype="multipart/form-data">
    <input type="file" name="files" multiple required>
    <button type="submit">Submit</button>
</form>

 <div>
     <table>
         <tr>
             <th>Id</th>
             <th>Name</th>
             <th>Download Link</th>
         </tr>

         <c:forEach var="d" items="${docs}">
             <tr>
                 <td>${d.id}</td>
                 <td>${d.name}</td>
                 <td><a href="/app/doctor/downloadFile/${d.id}">Download</a></td>
             </tr>

         </c:forEach>
     </table>
 </div>



/////// Naprawić ściąganie :) 