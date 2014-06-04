<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<spring:url value="/" var="contextPath"/>
<html>
<head>
    <title>Process list</title>
</head>
<body>
Process list
    <form>
        <table border="1">
            <tr>
                <th>Key</th>
                <th>Name</th>
                <th>Version</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${processList}" var="process">
                <tr>
                    <td>${process.key}</td>
                    <td>${process.name}</td>
                    <td>${process.version}</td>
                    <td><a href="${contextPath}admin/start-process/${process.key}">Start</a></td>
                </tr>
            </c:forEach>
        </table>
    </form>
</body>
</html>
