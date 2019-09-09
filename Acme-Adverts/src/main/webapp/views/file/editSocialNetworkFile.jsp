<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<security:authorize access="hasRole('MANAGER')">

	<form:form action="socialNetworkFile/edit.do" modelAttribute="socialNetworkFile">

		<form:hidden path="id" />
		<form:hidden path="version"  />
		<form:hidden path="contract" />
		
		<acme:textbox code="file.banner" path="banner" />
		<acme:textbox code="file.target" path="target" /> <br/>
		
		<acme:submit code="file.save" name="save" />
		
		<jstl:if test="${socialNetworkFile.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="file.delete" />"
			onclick="return confirm('<spring:message code="file.confirm.delete" />')" />&nbsp;
		</jstl:if>
	
	<acme:cancel url="contract/manager/list.do" code="file.cancel" /> <br/>
	
	</form:form>
	
</security:authorize>