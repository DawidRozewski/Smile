<%@ page contentType="text/html; charset=UTF-8" %>
<h2>Czy chcesz usunac usługę nr.${dentalService.id} ?</h2>

<form method="post">
    <input type="hidden" name="id" value="${dentalService.id}">
    <button type="submit" value="yes" name="confirmed">Tak</button>
    <button type="submit" value="no" name="confirmed">Nie</button>
</form>