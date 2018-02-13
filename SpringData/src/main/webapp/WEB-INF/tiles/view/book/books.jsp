<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: egid73
  Date: 2.2.18
  Time: 15.30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:useBean id="bookList" scope="request" type="java.util.List"/>
<c:if test="${not empty bookList}">
    <h1>Books</h1>
    <table class="table">
        <thead>
        <tr class="tab-header-area bg-info">
            <td>id</td>
            <td>name</td>
            <td>author</td>
            <td>owner</td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="listValue" items="${bookList}">
            <tr>
                <td>${listValue.id}</td>
                <td>${listValue.name}</td>
                <td>${listValue.author}</td>
                <td>
                    <c:if test="${listValue.owner != null}">
                        ${listValue.owner.toString()}
                    </c:if>
                    <c:if test="${listValue.owner == null}">
                        Free
                    </c:if>

                </td>
                <td>
                    <form:form action="/books/delete" method="post">
                        <label>
                            <input hidden name="id" value="${listValue.id}">
                        </label>
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </form:form>
                </td>
                <td>
                    <form:form action="/books/update/form" method="get">
                        <label>
                            <input hidden name="bookId" value="${listValue.id}">
                        </label>
                        <button type="submit" class="btn btn-info">Edit</button>
                    </form:form>
                </td>
                <td>
                    <c:if test="${listValue.owner == null}">
                        <form:form action="/books/assign/form" method="get">
                            <label>
                                <input hidden name="bookId" value="${listValue.id}">
                                <button type="submit" class="btn btn-success">book it</button>
                            </label>
                        </form:form>
                    </c:if>
                    <c:if test="${listValue.owner != null}">
                        <form:form action="/books/return" method="post">
                            <label>
                                <input hidden name="bookId" value="${listValue.id}">
                                <button type="submit" class="btn btn-danger">return it</button>
                            </label>
                        </form:form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<div class="btn-group mr-2" role="group" aria-label="Second group">
    <form:form action="/books/create/form" method="get">
        <button type="submit" class="btn btn-secondary">Add book</button>
    </form:form>
</div>

