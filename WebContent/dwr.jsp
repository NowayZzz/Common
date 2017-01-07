<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<script type="text/javascript" src="${ctx}/static/jquery/jquery-1.8.2.min.js"></script>
<script type='text/javascript' src='${ctx}/dwr/engine.js'></script>
<script type='text/javascript' src='${ctx}/dwr/util.js'></script>
<script type="text/javascript">
	//dwr错误信息处理，覆盖原方法
	dwr.engine._errorHandler = function(message, ex) {
		dwr.engine._debug("Error: " + ex.name + ", " + ex.message, true);
	};
</script>
<script type="text/javascript">
	function onload() {
		try {
			dwr.engine.setActiveReverseAjax(true);
		} catch (error) {
			//alert(error.toString());
		}
	}
	
	function dwrMsg(){
		$.ajax({
			url:'${ctx}/test/dwr/pushmsg',
			methodType:'POST',
		});
	}
	
	function pushMessage(data){
		console.log(data);
	}
</script>
</head>
<body onload="onload()">
	<input type="button" value="启动定时发送" onclick="dwrMsg()">
</body>
</html>