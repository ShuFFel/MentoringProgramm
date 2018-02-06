<%--
  Created by IntelliJ IDEA.
  User: egid73
  Date: 5.2.18
  Time: 16.15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:useBean id="bookId" scope="request" type="java.lang.String"/>
<div class="tab-header-area bg-info">Book "id" is: ${bookId}</div>

<%--@elvariable id="bookDTO" type="com.instinctools.egor.mentoring.spring.web.dto.BookDTO"--%>
<form:form method="POST" action="/books/update"
           modelAttribute="bookDTO">
    <form:label path="name">New name</form:label>
    <form:input path="name" class="form-control" id="name"/>

    <form:label path="author">New author</form:label>
    <form:input path="author" class="form-control" id="author"/>
    <label>
        <input hidden name="bookId" value="${bookId}">
    </label>
    <input type="submit" value="Submit"/>
</form:form>
