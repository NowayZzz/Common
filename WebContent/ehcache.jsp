<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<script type="text/javascript" src="${ctx}/static/jquery/jquery-1.8.2.min.js"></script>
<head>
<script type="text/javascript">
	function insert() {
		$.ajax({
			url:'${ctx}/test/cache/insert',
		});
	}
	
	function get() {
		$.ajax({
			url:'${ctx}/test/cache/get',
			data:{cacheId:'noway'}
		});
	}
	
	function remove() {
		$.ajax({
			url:'${ctx}/test/cache/remove',
			data:{cacheId:'noway'}
		});
	}
</script>
</head>
<body>
	<input type="button" value="放入缓存" onclick="insert()">
	<input type="button" value="获取缓存" onclick="get()">
	<input type="button" value="删除" onclick="remove()">
</body>
</html>