<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: egid73
  Date: 6.2.18
  Time: 13.25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1>Users</h1>
<table class="table">
    <td>
        <thead>
        <tr class="tab-header-area bg-info">
            <td>id</td>
            <td>userName</td>
            <td>dateOfBirth</td>
            <td></td>
        </tr>
        </thead>
        <tbody>
        <jsp:useBean id="userList" scope="request" type="java.util.List"/>
        <jsp:useBean id="bookId" scope="request" type="java.lang.String"/>
        <c:forEach var="listValue" items="${userList}">
            <tr>
                <td>${listValue.id}</td>
                <td>${listValue.userName}</td>
                <td>${listValue.dateOfBirth}</td>
                <td>
                    <form:form action="/books/assign" method="post">
                        <label>
                            <input hidden name="userId" value="${listValue.id}">
                            <input hidden name="bookId" value="${bookId}">
                        </label>
                        <button type="submit" class="btn btn-secondary">Assign</button>
                    </form:form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </td>
</table>