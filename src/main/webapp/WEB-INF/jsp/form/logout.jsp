<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAuthenticated()">
<form action="<c:url value="/logout"/>" method="post">
    <input class="fa fa-id-badge" type="submit" value="Wyloguj">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</sec:authorize>