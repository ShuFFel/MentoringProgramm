<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:useBean id="userId" scope="request" type="java.lang.String"/>
<div class="tab-header-area bg-info">User "id" is: ${userId}</div>

<%--@elvariable id="userDTO" type="com.instinctools.egor.mentoring.spring.web.dto.UserDTO"--%>
<form:form action="/users/update" modelAttribute="userDTO" method="post">
    <div class="form-group">
        <form:label path="name">New name</form:label>
        <form:input path="name" class="form-control" id="name"/>
        <form:label path="birthDate">New Date of birth</form:label>
        <div class="controls">
            <form:input path="birthDate" class="date" type="date"/>
        </div>
    </div>
    <label>
        <input hidden name="userId" value="${userId}">
    </label>
    <button type="submit" class="btn btn-outline-info">Update user</button>
</form:form>
