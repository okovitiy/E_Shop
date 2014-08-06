<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>
	<fmt:message key="registration_jsp.title" var="title" />
	<%@ include file="/WEB-INF/jspf/head.jspf"%>

	<body>
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		<div class="container">
			<form id="registration" action="controller" method="post" class="form-signin" role="form">
				<input type="hidden" name="command" value="registration" />
				<h3 class="form-signin-heading">
					<fmt:message key="registration_jsp.please_sign_in" />
				</h3>
				
				<input class="form-control" name="name" placeholder=
				"<fmt:message key="registration_jsp.label.name" />" required>
				
				<input class="form-control" name="login" placeholder=
				"<fmt:message key="registration_jsp.label.login" />" required>
				
				<input type="password" class="form-control" name="password"
				placeholder="<fmt:message key="registration_jsp.label.password" />" required>
				
				<input type="password" class="form-control" name="repeatPassword"
				placeholder="<fmt:message key="registration_jsp.label.repeat_password" />" required>
				
				<input type="email" class="form-control" name="email"
				placeholder="<fmt:message key="registration_jsp.label.email" />" required>
				
				<button class="btn btn-lg btn-warning btn-block" type="submit">
					<fmt:message key="registration_jsp.button.confirm" />
				</button>
			</form>
		</div>
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
		
	</body>
</html>