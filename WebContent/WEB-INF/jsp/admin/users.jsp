<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>
	<fmt:message key="list_users_jsp.title" var="title" />
	<%@ include file="/WEB-INF/jspf/head.jspf"%>

	<body>
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		
		<div class="container">
		<form id="users" action="controller">
						<input type="hidden" name="command" value="blockUsers" />
						<table id="list_users_table" class="table table-hover">
							<thead>
								<tr>
									<td>№</td>
									<td><fmt:message key="list_users_jsp.table.userId" /></td>
									<td><fmt:message key="list_users_jsp.table.login" /></td>
									<td><fmt:message key="list_users_jsp.table.name" /></td>
									<td><fmt:message key="list_users_jsp.table.email" /></td>
									<td><fmt:message key="list_users_jsp.table.roleId" /></td>
									<td><input type="submit" class="btn btn-sm btn-warning"
									value="<fmt:message key="list_users_jsp.table.button.execute" />"></td>
								</tr>
							</thead>

							<c:set var="k" value="0" />
							<c:forEach var="item" items="${userBeans}">
								<c:set var="k" value="${k+1}" />
								<tr>
								<td><c:out value="${k}" /></td>
								<td>${item.userId}</td>
								<td>${item.userLogin}</td>
								<td>${item.userName}</td>
								<td>${item.userEmail}</td>
								<td>${item.roleName}</td>
								<td><select name="roleName" class="form-control input-sm">
										<option value="${item.roleName}">
											${item.roleName}
										</option>
										<option value="blockedClient">
											<fmt:message key="list_users_jsp.table.block" />
										</option>
										<option value="client">
											<fmt:message key="list_users_jsp.table.unlock" />
										</option>
										<%-- <option value="remove">
											<fmt:message key="list_users_jsp.table.remove" />
										</option> --%>
								</select></td>
								</tr>
							</c:forEach>
						</table>
					</form>
					
					<form id="addAdmin" action="controller" method=post>
						<input type="hidden" name="command" value="addAdmin" />
						<table id="add_admin_table" class="table table-hover">
							<tr><fmt:message key="list_users_jsp.table" />
							</tr>
							<tr>
								<td><input class="form-control" name="name"
									placeholder="<fmt:message key="registration_jsp.label.name" />" required>
								</td>
								<td><input class="form-control" name="login" placeholder=
									"<fmt:message key="registration_jsp.label.login" />" required>
								</td>
								<td>
									<input type="password" class="form-control" name="password"
									placeholder="<fmt:message key="registration_jsp.label.password" />" required>
								</td>
								<td>
									<input type="password" class="form-control" name="repeatPassword"
									placeholder="<fmt:message key="registration_jsp.label.repeat_password" />" required>
								</td>
								<td>
									<input type="email" class="form-control" name="email"
									placeholder="<fmt:message key="registration_jsp.label.email" />" required>
								</td>
								<td>
									<button class="btn btn-sm btn-warning" type="submit">
										<fmt:message key="registration_jsp.button.confirm" />
									</button>
								</td>
							</tr>
						</table>
					</form>
						
		</div>

</body>
</html>