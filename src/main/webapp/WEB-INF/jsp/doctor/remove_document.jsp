<%@ page contentType="text/html; charset=UTF-8" %>
<h2>Are you sure you want to delete the ${document.name}?</h2>

<form method="post">
    <input type="hidden" name="id" value="${document.id}">
    <button type="submit" value="yes" name="confirmed">Yes</button>
    <button type="submit" value="no" name="confirmed">No</button>
</form>