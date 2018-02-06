<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: egid73
  Date: 5.2.18
  Time: 12.09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="userDTO" type="com.instinctools.egor.mentoring.spring.web.dto.UserDTO"--%>
<form:form action="/users/create" modelAttribute="userDTO" method="post">
    <div class="form-group">
        <form:label path="name">name</form:label>
        <form:input path="name" class="form-control" id="name"/>
        <form:label path="birthDate">Date of birth</form:label>
        <div class="controls">
            <form:input path="birthDate" type="date" class="date"/>
        </div>
    </div>
    <button type="submit" class="btn btn-outline-info">Create User</button>
</form:form>