<%@ tag language="java" body-content="empty" %>

<%-- Taglibs --%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<%-- Attributes --%> 
 
<%@ attribute name="path" required="true" %>
<%@ attribute name="code" required="true" %>

<%@ attribute name="readonly"    required="false" %>
<%@ attribute name="placeholder" required="false" %>
<%@ attribute name="type"  required="false" %>
<%@ attribute name="min"   required="false" %>
<%@ attribute name="max"   required="false" %>
<%@ attribute name="value" required="false" %>
<%@ attribute name="step"  required="false" %>
<%@ attribute name="id"    required="false" %>

<jstl:if test="${readonly == null}">
	<jstl:set var="readonly" value="false" />
</jstl:if>

<jstl:if test="${type == null}">
	<jstl:set var="type" value="text" />
</jstl:if>

<%-- Definition --%>

<div>
	<form:label path="${path}">
		<spring:message code="${code}" />
	</form:label>	
	<form:input path="${path}" readonly="${readonly}" placeholder="${placeholder}" type="${type}" min="${min}" max="${max}" value ="${value}" step="${step}" id="${id}"/>	
	<form:errors path="${path}" cssClass="error" />
</div>	
