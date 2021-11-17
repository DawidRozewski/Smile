<h2>Czy chcesz usunac autora ${patient.fullName} ?</h2>

<form method="post">
    <input type="hidden" name="id" value="${patient.id}">
    <button type="submit" value="yes" name="confirmed">YES</button>
    <button type="submit" value="no" name="confirmed">NO</button>
</form>