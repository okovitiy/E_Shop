<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>
	<fmt:message key="list_products_jsp.title" var="title" />
	<%@ include file="/WEB-INF/jspf/head.jspf"%>

	<body>
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		
		<div class="container">
			<div class="row">	
				<div class="col-md-3">
					<form id="sortProducts" action="controller" role="form">
			
						<div class="form-group">
							<input type="hidden" name="command" value="sortProducts" /> 
						</div>				
				
						<div class="form-group">	
							 <b><fmt:message key="list_products_jsp.color" />:</b> 
					 		<select name="color" class="form-control input-sm">
						<option value="all">
							<fmt:message key="list_products_jsp.color.all" />
						</option>
						<option value="white">
							<fmt:message key="list_products_jsp.color.white" />
						</option>
						<option value="orange">
							<fmt:message key="list_products_jsp.color.orange" />
						</option>
						<option value="red">
							<fmt:message key="list_products_jsp.color.red" />
						</option>
						<option value="green">
							<fmt:message key="list_products_jsp.color.green" />
						</option>
						<option value="blue">
							<fmt:message key="list_products_jsp.color.blue" />
						</option>
						<option value="black">
							<fmt:message key="list_products_jsp.color.black" />
						</option>
					</select> 
					
					</div>
					
					<div class="form-group">	
					<b><fmt:message key="list_products_jsp.category" />:</b> <select
						name="category" class="form-control input-sm">
						<option value="all">
							<fmt:message key="list_products_jsp.category.all" />
						</option>
						<option value="TVs">
							<fmt:message key="list_products_jsp.category.tvs" />
						</option>
						<option value="Laptops">
							<fmt:message key="list_products_jsp.category.laptops" />
						</option>
						<option value="Phones">
							<fmt:message key="list_products_jsp.category.mobile_phones" />
						</option>
					</select> 
					</div>
					
					
					<div class="form-group">	
						<b><fmt:message key="list_products_jsp.price" />:</b>
						<input class="form-control input-sm" type="text" placeholder="<fmt:message key="list_products_jsp.price_min" />:"
						name="minPrice">						
					</div>
					
					
					<div class="form-group">	
						 <input class="form-control input-sm" type="text" placeholder="<fmt:message
							key="list_products_jsp.price_max" />:" name="maxPrice">
					</div>
					
					<div class="form-group">	
						<select name="sort" class="form-control input-sm">
							<option value="byId">
								<fmt:message key="list_products_jsp.sort.by_id" />
							</option>
							<option value="byNameAz">
								<fmt:message key="list_products_jsp.sort.by_name_az" />
							</option>
							<option value="byNameZa">
								<fmt:message key="list_products_jsp.sort.by_name_za" />
							</option>
							<option value="byPriceUp">
								<fmt:message key="list_products_jsp.sort.by_price_up" />
							</option>
							<option value="byPriceDown">
								<fmt:message key="list_products_jsp.sort.by_price_down" />
							</option>
							<option value="byNovelty">
								<fmt:message key="list_products_jsp.sort.by_novelty" />
							</option>
						</select>
					</div>
						
						
					<div class="form-group">	
							<input type="submit" class="btn btn-sm btn-warning" value=
						"<fmt:message key="list_products_jsp.button.sort"/>">
						
						</div>	

					</form>
					</div>
				
					<div class="col-md-9">

					<form id="products" action="controller">
						<input type="hidden" name="command" value="removeProduct" />
						<table id="list_products_table" class="table table-hover">
							<thead>
								<tr>
									<td>№</td>
									<td><fmt:message key="list_products_jsp.table.category" /></td>
									<td><fmt:message key="list_products_jsp.table.name" /></td>
									<td><fmt:message key="list_products_jsp.table.price" /></td>
									<td><fmt:message key="list_products_jsp.table.color" /></td>
									<td><fmt:message key="list_products_jsp.table.size" /></td>
									<td><fmt:message
										key="list_products_jsp.table.incoming_date" /></td>
									<td><fmt:message key="list_products_jsp.table.quantity" /></td>
									<td><input type="submit" class="btn btn-sm btn-warning"
									value="<fmt:message key="list_products_jsp.table.remove_products" />"></td>
								</tr>
							</thead>

							<c:set var="k" value="0" />
							<c:forEach var="item" items="${productBeans}">
								<c:set var="k" value="${k+1}" />
								<tr>
								<td><c:out value="${k}" /></td>
								<td>${item.category}</td>
								<td>${item.name}</td>
								<td>${item.price}</td>
								<td>${item.color}</td>
								<td>${item.size}</td>
								<td>${item.novelty}</td>
								<td>${item.quantity}</td>
								<td><input type="checkbox" name="itemId" value="${item.id}" /></td>
								</tr>
							</c:forEach>
						</table>
					</form>
					
					<form id="addProduct" action="controller" method=post>
						<input type="hidden" name="command" value="addProduct" />
						<table id="add_product_table" class="table table-hover">
							<tr>
								<td>
									<input class="form-control" name="id"
									placeholder="№">								
								</td>
								<td>	
									<select	name="category" class="form-control input-sm">
										<option value="TVs">
											<fmt:message key="list_products_jsp.category.tvs" />
										</option>
										<option value="Laptops">
											<fmt:message key="list_products_jsp.category.laptops" />
										</option>
										<option value="Phones">
											<fmt:message key="list_products_jsp.category.mobile_phones" />
										</option>
									</select> 

								</td>					
								<td><input class="form-control" name="name"
									placeholder="<fmt:message key="list_products_jsp.table.name" />" required>
								</td>
								<td><input class="form-control" name="price"
									placeholder="<fmt:message key="list_products_jsp.table.price" />" required>
								</td>
								<td>
								<select name="color" class="form-control input-sm">
									<option value="white">
										<fmt:message key="list_products_jsp.color.white" />
									</option>
									<option value="orange">
										<fmt:message key="list_products_jsp.color.orange" />
									</option>
									<option value="red">
										<fmt:message key="list_products_jsp.color.red" />
									</option>
									<option value="green">
										<fmt:message key="list_products_jsp.color.green" />
									</option>
									<option value="blue">
										<fmt:message key="list_products_jsp.color.blue" />
									</option>
									<option value="black">
										<fmt:message key="list_products_jsp.color.black" />
									</option>
								</select>								
								</td>
								<td><input class="form-control" name="size"
									placeholder="<fmt:message key="list_products_jsp.table.size" />" required>
								</td>
								<td>
									<input type="submit" class="btn btn-sm btn-warning"
									value="<fmt:message key="list_products_jsp.table.add_update_product" />">
								</td>								
							</tr>
						</table>
					</form>
				</div>
			</div>
		
		</div>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</body>
	
	
	
</html>