<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>

<fmt:message key="persanal_account_jsp.title" var="title" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	

		<%@ include file="/WEB-INF/jspf/header.jspf"%>

			<div class="container">
			
				<form id="persanalAccount" action="controller" role="form">
				<table id="orders_table" class="table table-hover">
						<thead>
							<tr>
								<td>№</td>
								<td><fmt:message key="persanalAccount_jsp.table.status" /></td>
							</tr>
						</thead>

						<c:set var="k" value="0" />
						<c:forEach var="item" items="${orderBeans}">
							<c:set var="k" value="${k+1}" />
							<tr>
								<td><c:out value="${k}" /></td>
								<td>${item.statusOrder}</td>
							</tr>
						</c:forEach>
					</table>
					
					
				</form>
			</div>



		<%@ include file="/WEB-INF/jspf/footer.jspf"%>


</body>