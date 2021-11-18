<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<h2>Zaloguj</h2>

<form method="post">
    E-mail: <input type="text" name="email" placeholder="Podaj email"> <br/>
    Hasło: <input type="password" name="password" placeholder="Podaj hasło"> <br/>
    <input type="submit" value="Zaloguj"> <br/>
<%--    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
</form>