<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1>スケジュール登録</h1>
<spring:url var="mainFormUrl" value="/scheduler/BA01S010" />
<form:form action="${mainFormUrl}" modelAttribute="BA01S010Form">
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
		<td class="right">スケジュール日付</td>
		<td>
			<form:input path="scheduledOn" type="date" />
			<form:errors path="scheduledOn" element="div" cssClass="message-area error" />
		</td>
	</tr>
	<tr>
		<td class="right">スケジュール説明</td>
		<td>
			<form:input path="description" />
			<form:errors path="description" element="div" cssClass="message-area error" />
		</td>
	</tr>
</table>
</div>
<p>
	<input type="submit" name="create" value="登録" />
</p>
</form:form>
