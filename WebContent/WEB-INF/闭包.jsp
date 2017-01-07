<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<script type="text/javascript">
		var inp = function(){
			var i = 0;
			return function(){
					alert(++i);
				
			};
		};
		var ba = inp();
	</script>
  </head>
  
  <body>
   <input type="button" value="add" onclick="ba()">
  </body>
</html>
