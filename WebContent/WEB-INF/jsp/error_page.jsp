<%@ page isErrorPage="true" %>
<%@ page import="java.io.PrintWriter" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Error" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>
	
<body>
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		
			<div class="container">
				<h2 class="error">
					The following error occurred
				</h2>
			
				<%-- this way we get the error information (error 404)--%>
				<c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
				<c:set var="message" value="${requestScope['javax.servlet.error.message']}"/>
				
				<c:if test="${not empty code}">
					<h3>Error code: <c:out value="${code}"/></h3>
				</c:if>			
				
				<c:if test="${not empty message}">
					<h3><c:out value="${message}"/></h3>
				</c:if>
				
				<%-- if get this page using forward --%>
				<c:if test="${not empty errorMessage}">
					<p><c:out value="${errorMessage}"/></p>
				</c:if>	
				
				<%-- this way we print exception stack trace --%>
				<%
					if (exception != null)
						exception.printStackTrace(new PrintWriter(out));
				%>
				
			</div>


		<%@ include file="/WEB-INF/jspf/footer.jspf"%>

</body>
</html>