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

	<form:form action="radioFile/edit.do" modelAttribute="radioFile">

		<form:hidden path="id" />
		<form:hidden path="version"  />
		<form:hidden path="contract" />
		
		<acme:textbox code="file.sound"    path="sound"    />
		<acme:textbox code="file.name"     path="name"     /> 
		<!-- <acme:textbox code="file.schedule" path="schedule" /> <br/> -->
		
		<spring:message code = "file.schedule"/>: 
		<input type= "datetime-local" name="schedule" placeholder="HH:mm - HH:mm">
		<form:errors path="schedule" cssClass="error" /><br>
		
		<acme:submit code="file.save" name="save" />
		
		<jstl:if test="${radioFile.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="file.delete" />"
			onclick="return confirm('<spring:message code="file.confirm.delete" />')" />&nbsp;
		</jstl:if>
	
	<acme:cancel url="contract/manager/list.do" code="file.cancel" /> <br/>
	
	</form:form>
	
</security:authorize>