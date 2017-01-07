<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<script type="text/javascript" src="${ctx}/static/jquery/jquery-1.8.2.min.js"></script>
<head>
<script type="text/javascript">
	function start() {
		$.ajax({
			url:'${ctx}/test/socket/start',
		});
	}
	
</script>
</head>
<body>
	<input type="button" value="启动socket" onclick="start()">
</body>
</html>