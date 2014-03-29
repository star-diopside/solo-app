<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<spring:url value='/static/css/normalize.css'/>" type="text/css" media="screen,print"/>
<link rel="stylesheet" href="<spring:url value='/static/css/layout.css'/>" type="text/css" media="screen,print"/>
<link rel="stylesheet" href="<spring:url value='/static/css/style.css'/>" type="text/css" media="screen,print"/>
<title><tiles:getAsString name="title"/></title>
</head>
<body>
<div id="wrapper">
<div id="wrapper-inside">
<div id="header"><tiles:insertAttribute name="header"/></div>
<div class="clear"></div>
<div id="body"><tiles:insertAttribute name="body"/></div>
<div class="clear"></div>
<div id="footer"><tiles:insertAttribute name="footer"/></div>
</div>
</div>
</body>
</html>
