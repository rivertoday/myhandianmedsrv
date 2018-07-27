<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script>
	window.onload = function() {
		if(<%=session.getAttribute("message")!=null%>){
				alert("<%=session.getAttribute("message")%>");
		}
		<%session.setAttribute("message", null);%>
		if(<%=request.getAttribute("message")!=null%>){
				alert("<%=request.getAttribute("message")%>");
		}
	}
</script>
