<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<fmt:message key="settings_jsp.title" var="title"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>


		<%@ include file="/WEB-INF/jspf/header.jspf" %>


			<div class="container">
				<form id="settings_form" action="controller" method="post">
					<input type="hidden" name="command" value="updateSettings" />

					<div class="form-group">
						<p>
							<fmt:message key="settings_jsp.label.localization"/>
						</p>
						<select name="localeToSet" class="form-control input-sm">
							<c:choose>
								<c:when test="${not empty defaultLocale}">
									<option value="">${defaultLocale}[Default]</option>
								</c:when>
								<c:otherwise>
									<option value=""/>
								</c:otherwise>
							</c:choose>
														
							<c:forEach var="localeName" items="${locales}">
								<option value="${localeName}">${localeName}</option>							
							</c:forEach>
						</select>
					</div>
					<c:if test="${not empty userRole.name}">
						<div class="form-group">
							<input name="name" class="form-control input-sm" placeholder="<fmt:message key="settings_jsp.label.change_name"/>">
						</div>
					</c:if>		
					<input type="submit" class="btn btn-sm btn-warning" value='<fmt:message key="settings_jsp.button.update"/>'><br/>
				</form> 
			</div>


		<%@ include file="/WEB-INF/jspf/footer.jspf" %>
		

</body>
</html>