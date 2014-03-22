<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
ログインユーザ：<security:authentication property="principal.userId" /><br/>
ニックネーム：<security:authentication property="principal.nickname" /><br/>
<a href="<spring:url value='/j_spring_security_logout' />">ログアウト</a>
<hr/>
