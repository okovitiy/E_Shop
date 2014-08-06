<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>

<fmt:message key="list_orders_jsp.title" var="title" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>


		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		
		<div class="container">

				<%-- <form id="listOrders" action="controller" role="form">
					<input type="hidden" name="command" value="sortOrders" />	
					
					<div class="form-group">
						<input type="submit" class="btn btn-sm btn-warning" value="<fmt:message key="list_orders_jsp.button.sort"/>">		
					</div>
					
					<select name="status" class="form-control input-sm" id="status">
										<option value="all">
											<fmt:message key="list_products_jsp.status.all" />
										</option>
										<option value="opened">
											<fmt:message key="list_products_jsp.status.opened" />
										</option>
										<option value="registered">
											<fmt:message key="list_products_jsp.status.registered" />
										</option>
										<option value="paid">
											<fmt:message key="list_products_jsp.status.paid" />
										</option>
										<option value="closed">
											<fmt:message key="list_products_jsp.status.closed" />
										</option>
										<option value="canceled">
											<fmt:message key="list_products_jsp.status.canceled" />
										</option>
								</select>
				</form> --%>
				
					
				<form id="listOrders" action="controller" role="form">
					<input type="hidden" name="command" value="changeStatus" />	
						<table id="list_order_table" class="table table-hover">
						<thead>
							<tr>
								<td>№</td>
								<td><fmt:message key="list_orders_jsp.table.user_login" /></td>
								<td><fmt:message key="list_orders_jsp.table.user_name" /></td>
								<td><fmt:message key="list_orders_jsp.table.price" /></td>
								<td><fmt:message key="list_orders_jsp.table.status" /></td>
								<td>
									<input type="submit" class="btn btn-sm btn-warning"
									value="<fmt:message key="list_orders_jsp.button.change_status"/>">
								</td>
							</tr>
						</thead>

						<c:forEach var="bean" items="${userOrderBeanList}">
							<tr>
								<td>${bean.orderId}</td>
								<td>${bean.userLogin}</td>
								<td>${bean.userName}</td>
								<td>${bean.cartPrice}</td>
								<td>${bean.statusName}</td>
								<td><select name="changeStatus" class="form-control input-sm">
										<option value="${bean.statusName}">
											${bean.statusName}
										</option>
										<option value="paid">
											<fmt:message key="list_products_jsp.status.paid" />
										</option>
										<option value="closed">
											<fmt:message key="list_products_jsp.status.closed" />
										</option>
										<option value="canceled">
											<fmt:message key="list_products_jsp.status.canceled" />
										</option>
								</select></td>
							</tr>
						</c:forEach>
					</table>
				</form> 
				

			</div>

		<%@ include file="/WEB-INF/jspf/footer.jspf"%>

</body>
</html>