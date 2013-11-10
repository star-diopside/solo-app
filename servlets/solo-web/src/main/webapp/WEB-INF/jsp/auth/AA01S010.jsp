<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/head.jsp" />
<title>ユーザログイン</title>
</head>
<body>
<h1>ユーザログイン</h1>
<spring:url var="mainFormUrl" value="/auth/AA01S010" />
<form:form action="${mainFormUrl}" modelAttribute="AA01S010Form">
<form:errors element="div" cssClass="message-area error" />
<div class="table-none">
<table>
	<tr>
		<td class="right">ユーザ名</td>
		<td>
			<form:input path="username" />
			<form:errors path="username" element="div" cssClass="message-area error" />
		</td>
	</tr>
	<tr>
		<td class="right">パスワード</td>
		<td>
			<form:password path="password" autocomplete="off" />
			<form:errors path="password" element="div" cssClass="message-area error" />
		</td>
	</tr>
</table>
</div>
<p>
	<input type="submit" name="login" value="ログイン" />
</p>
<p>
	<a href="<spring:url value='/auth/AA02S010' />">ユーザを新規登録する場合はこちら</a>
</p>
</form:form>
</body>
</html>
