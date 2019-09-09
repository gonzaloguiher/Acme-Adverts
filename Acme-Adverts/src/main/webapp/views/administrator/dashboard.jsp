<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('ADMIN')">

	<spring:message code="administrator.datatable"/>
	<table style="width:'100%' border='0' align='center' ">
			<tr>
				<th><spring:message code="administrator.type"/></th>
				<th><spring:message code="administrator.requestsPerManager" /></th>
				<th><spring:message code="administrator.requestsPerCustomer"/></th>
				<th><spring:message code="administrator.requestsPerManagerStatusPending" /></th>
				<th><spring:message code="administrator.requestsPerCustomerStatusPending"/></th>
			</tr>
			<tr>
				<td><spring:message code="administrator.average"/></td>
				<td><jstl:out value="${avgRequestsPerManager}  "/></td>
				<td><jstl:out value="${avgRequestsPerCustomer} "/></td>	
				<td><jstl:out value="${avgRequestsPerManagerStatusPending}  "/></td>		
				<td><jstl:out value="${avgRequestsPerCustomerStatusPending} "/></td>
			</tr>
			<tr>
				<td><spring:message code="administrator.minimum"/></td>
				<td><jstl:out value="${minRequestsPerManager}  "/></td>
				<td><jstl:out value="${minRequestsPerCustomer} "/></td>
				<td><jstl:out value="${minRequestsPerManagerStatusPending}  "/></td>
				<td><jstl:out value="${minRequestsPerCustomerStatusPending} "/></td>
			</tr>	
			<tr>
				<td><spring:message code="administrator.maximum"/></td>
				<td><jstl:out value="${maxRequestsPerManager}  "/></td>
				<td><jstl:out value="${maxRequestsPerCustomer} "/></td>
				<td><jstl:out value="${maxRequestsPerManagerStatusPending}  "/></td>
				<td><jstl:out value="${maxRequestsPerCustomerStatusPending} "/></td>
			</tr>
			<tr>
				<td><spring:message code="administrator.stdv"/></td>
				<td><jstl:out value="${stdevRequestsPerCustomer}"/></td>
				<td><jstl:out value="${stdevRequestsPerCustomer}"/></td>
			</tr>
	</table>
	
	<spring:message code="administrator.datatable.files"/>
	<table style="width:'100%' border='0' align='center' ">
			<tr>
				<th><spring:message code="administrator.type"/></th>
				<th><spring:message code="administrator.billboardFilesPerContract"/></th>
				<th><spring:message code="administrator.infoFilesPerContract" /></th>
				<th><spring:message code="administrator.radioFilesPerContract"/></th>
				<th><spring:message code="administrator.TVFilesPerContract"   /></th>
				<th><spring:message code="administrator.socialNetworkFilesPerContract"/></th>
				<th><spring:message code="administrator.filesPerContract"/></th>
			</tr>
			<tr>
				<td><spring:message code="administrator.average"/></td>
				<td><jstl:out value="${avgBillboardFilesPerContract}"/></td>
				<td><jstl:out value="${avgInfoFilesPerContract} "/></td>
				<td><jstl:out value="${avgRadioFilesPerContract}"/></td>
				<td><jstl:out value="${avgTVFilesPerContract}   "/></td>
				<td><jstl:out value="${avgSocialNetworkFilesPerContract}"/></td>
				<td><jstl:out value="${avgFilesPerContract}"/></td>	
			</tr>
			<tr>
				<td><spring:message code="administrator.minimum"/></td>
				<td><jstl:out value="${minBillboardFilesPerContract}"/></td>	
				<td><jstl:out value="${minInfoFilesPerContract} "/></td>	
				<td><jstl:out value="${minRadioFilesPerContract}"/></td>
				<td><jstl:out value="${minTVFilesPerContract}   "/></td>
				<td><jstl:out value="${minSocialNetworkFilesPerContract}"/></td>
				<td><jstl:out value="${minFilesPerContract}"/></td>		
			</tr>	
			<tr>
				<td><spring:message code="administrator.maximum"/></td>
				<td><jstl:out value="${maxBillboardFilesPerContract}"/></td>	
				<td><jstl:out value="${maxInfoFilesPerContract} "/></td>	
				<td><jstl:out value="${maxRadioFilesPerContract}"/></td>
				<td><jstl:out value="${maxTVFilesPerContract}   "/></td>
				<td><jstl:out value="${maxSocialNetworkFilesPerContract}"/></td>
				<td><jstl:out value="${maxFilesPerContract}"/></td>		
			</tr>
			<tr>
				<td><spring:message code="administrator.stdv"/></td>
				<td><jstl:out value="${stdevBillboardFilesPerContract}"/></td>
				<td><jstl:out value="${stdevInfoFilesPerContract} "/></td>
				<td><jstl:out value="${stdevRadioFilesPerContract}"/></td>
				<td><jstl:out value="${stdevTVFilesPerContract}   "/></td>
				<td><jstl:out value="${stdevSocialNetworkFilesPerContract}"/></td>
				<td><jstl:out value="${stdevFilesPerContract}"/></td>
			</tr>
	</table>
	
	<b><spring:message code="administrator.top10ManagersRequestsStatusPending"/></b>
	<jstl:if test="${empty top10ManagersRequestsStatusPending}"><spring:message code="administrator.empty"/></jstl:if>
	<table style="width:'100%' border='0' align='center' ">
		<jstl:forEach var="i" items="${top10ManagersRequestsStatusPending}">
		<tr>
			<td><jstl:out value="${i.name}"/> <jstl:out value="${i.surname}"/></td>
		</tr>			
		</jstl:forEach>
	</table>
	
</security:authorize>