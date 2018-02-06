<%--
  Created by IntelliJ IDEA.
  User: egid73
  Date: 5.2.18
  Time: 12.05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="userList" scope="request" type="java.util.List"/>
<c:if test="${not empty userList}">
    <h1>Users</h1>
    <table class="table">
        <thead>
        <tr class="tab-header-area bg-info">
            <td>id</td>
            <td>userName</td>
            <td>dateOfBirth</td>
            <td></td>
            <td></td>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="listValue" items="${userList}">
            <tr>
                <td>${listValue.id}</td>
                <td>${listValue.userName}</td>
                <td>${listValue.dateOfBirth}</td>
                <td>
                    <form:form action="/users/delete" method="post">
                        <label>
                            <input hidden name="userId" value="${listValue.id}">
                        </label>
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </form:form>
                </td>
                <td>
                    <form:form action="/users/update/form" method="get">
                        <label>
                            <input hidden name="userId" value="${listValue.id}">
                        </label>
                        <button type="submit" class="btn btn-info">Edit</button>
                    </form:form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<div class="btn-group mr-2" role="group" aria-label="Second group">
    <form:form action="/users/create/form" method="get">
        <button type="submit" class="btn btn-secondary">Create user</button>
    </form:form>
</div>

