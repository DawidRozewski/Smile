<%@ page contentType="text/html; charset=UTF-8" %>
<h2>Are you sure you want to delete the visit No. ${treatment.visitNumber} ?</h2>

<form method="post">
    <input type="hidden" name="id" value="${treatment.id}">
    <button type="submit" value="yes" name="confirmed">Yes</button>
    <button type="submit" value="no" name="confirmed">No</button>
</form>