<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/head.jsp" />
<title>ユーザ新規登録</title>
</head>
<body>
<h1>ユーザ新規登録</h1>
<spring:url var="mainFormUrl" value="/auth/AA02S010" />
<form:form action="${mainFormUrl}" modelAttribute="AA02S010Form">
<c:out value="${errors}" />
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
		<td class="right">ニックネーム</td>
		<td>
			<form:input path="nickname" />
			<form:errors path="nickname" element="div" cssClass="message-area error" />
		</td>
	</tr>
	<tr>
		<td class="right">パスワード</td>
		<td>
			<form:password path="password" autocomplete="off" />
			<form:errors path="password" element="div" cssClass="message-area error" />
		</td>
	</tr>
	<tr>
		<td class="right">パスワード（確認）</td>
		<td>
			<form:password path="passwordConfirm" autocomplete="off" />
			<form:errors path="passwordConfirm" element="div" cssClass="message-area error" />
		</td>
	</tr>
	<tr>
		<td class="right">画像認証</td>
		<td>
			<span>画像に表示されている文字を入力してください。</span>
			<div><img src="<spring:url value='/captcha.jpg' />" /></div>
			<form:input path="captcha" autocomplete="off" />
			<form:errors path="captcha" element="div" cssClass="message-area error" />
		</td>
	</tr>
</table>
</div>
<p>
	<input type="submit" name="back" value="戻る" />
	<input type="submit" name="register" value="新規登録" />
</p>
</form:form>
</body>
</html>
