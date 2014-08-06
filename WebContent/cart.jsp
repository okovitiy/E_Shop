<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>

<fmt:message key="cart_jsp.title" var="title" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
		<%@ include file="/WEB-INF/jspf/header.jspf"%>

			<div class="container">
				<form id="cartList" action="controller" role="form">
					<input type="hidden" name="command" value="cleanProducts" /> 					
					<table id="list_products_table" class="table table-hover">
						<thead>
							<tr>
								<td>№</td>
								<td><fmt:message key="cart_jsp.table.product" /></td>
								<td><fmt:message key="cart_jsp.table.price" /></td>
								<%-- <td><fmt:message key="cart_jsp.table.quantity" /></td> --%>
								<td><input type="submit" class="btn btn-sm btn-warning"
									value="<fmt:message key="cart_jsp.table.button.clean_products" />"></td>
							</tr>
						</thead>

						<c:set var="k" value="0" />
						<c:forEach var="item" items="${cartList}">
							<c:set var="k" value="${k+1}" />
							<tr>
								<td><c:out value="${k}" /></td>
								<td>${item.productName}</td>
								<td>${item.price}</td>
								<td>${item.quantity}</td>
								<td><input type="checkbox" name="itemId" value="${item.id}" /></td>
							</tr>
						</c:forEach>
					</table>
					
				</form>
				<form id="cartList" action="controller" role="form">
					<input type="hidden" name="command" value="makeOrder" />
					<div class="form-group">
						<c:choose>
							<c:when test="${userRole.name == 'client' }">
								<input type="submit" class="btn btn-sm btn-warning" value=
								"<fmt:message key="cart_jsp.button.make_an_order"/>">
							</c:when>
							<c:when test="${empty userRole.name}">
								<a class="navbar-brand" href="controller?command=viewRegistration">
									<fmt:message key="cart_jsp.hiden_submit" />
								</a>
							</c:when>
						</c:choose>
					</div>
				</form>
				
			</div>

		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>