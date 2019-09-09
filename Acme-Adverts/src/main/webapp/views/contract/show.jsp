<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

	<div>
	<b><spring:message code="contract.text"  /></b>: <jstl:out value="${contract.text}"  /> <br/>
	<b><spring:message code="contract.hash"  /></b>: <jstl:out value="${contract.hash}"  /> <br/> 
	<b><spring:message code="contract.momentCustomer" /></b>: <jstl:out value="${contract.momentCustomer}" /> <br/>
	<b><spring:message code="contract.momentManager"  /></b>:  <jstl:out value="${contract.momentManager}"  /> <br/>
	<b><spring:message code="contract.signCustomer"   /></b>: <jstl:out value="${contract.signCustomer}"   /> <br/>
	<b><spring:message code="contract.signManager"    /></b>:  <jstl:out value="${contract.signManager}"    /> <br/> 
	<b><spring:message code="contract.package"   /></b>: <a href="package/show.do?packageId=${contract.pakage.id}"><jstl:out value="${contract.pakage.ticker}"/> </a> <br/>
	</div>
		
	<h1> <spring:message code="contract.billboardFiles" /> </h1>
			
	<display:table name="billboardFiles" id="row" requestURI="${requestURI}" pagesize="5">
		
	<security:authorize access="hasRole('MANAGER')">
		<jstl:if test="${row.contract.signManager == null}">
		<display:column>
			<a href="billboardFile/edit.do?billboardFileId=${row.id}">   <spring:message code="file.edit"   /> </a> <br>
			<a href="billboardFile/delete.do?billboardFileId=${row.id}"> <spring:message code="file.delete" /> </a> <br>
		</display:column>
		</jstl:if>
	</security:authorize>
		
    <display:column titleKey="file.location" property="location" />
    <display:column titleKey="file.image"    property="image"    />
		    
	</display:table>
	
	<security:authorize access="hasRole('MANAGER')">
	<jstl:if test="${row.contract.signManager == null}"> <a href="billboardFile/create.do?contractId=${contract.id}"> <spring:message code="file.create.billboardFile" /> </a> <br> </jstl:if>
	</security:authorize>
	
	<h1> <spring:message code="contract.infoFiles" /> </h1>
		
	<display:table name="infoFiles" id="row" requestURI="${requestURI}" pagesize="5">
	
	<security:authorize access="hasRole('MANAGER')">
		<jstl:if test="${row.contract.signManager == null}">
		<display:column>
			<a href="infoFile/edit.do?infoFileId=${row.id}">   <spring:message code="file.edit"   /> </a> <br>
			<a href="infoFile/delete.do?infoFileId=${row.id}"> <spring:message code="file.delete" /> </a> <br>
		</display:column>
		</jstl:if>
	</security:authorize>
		
    <display:column titleKey="file.title" property="title" />
    <display:column titleKey="file.text"  property="text"  />
		    
	</display:table>
	
	<security:authorize access="hasRole('MANAGER')">
	<jstl:if test="${row.contract.signManager == null}"> <a href="infoFile/create.do?contractId=${contract.id}"> <spring:message code="file.create.infoFile" /> </a> <br> </jstl:if>
	</security:authorize>
	
	<h1> <spring:message code="contract.socialNetworkFiles" /> </h1>
	
	<display:table name="socialNetworkFiles" id="row" requestURI="${requestURI}" pagesize="5">
		
	<security:authorize access="hasRole('MANAGER')">
		<jstl:if test="${row.contract.signManager == null}">
		<display:column>
			<a href="socialNetworkFile/edit.do?socialNetworkFileId=${row.id}">   <spring:message code="file.edit"   /> </a> <br>
			<a href="socialNetworkFile/delete.do?socialNetworkFileId=${row.id}"> <spring:message code="file.delete" /> </a> <br>
		</display:column>
		</jstl:if>
	</security:authorize>
		
    <display:column titleKey="file.banner" property="banner" />
    <display:column titleKey="file.target" property="target" />
		    
	</display:table>
	
	<security:authorize access="hasRole('MANAGER')">
	<jstl:if test="${row.contract.signManager == null}"> <a href="socialNetworkFile/create.do?contractId=${contract.id}"> <spring:message code="file.create.socialNetworkFile" /> </a> <br> </jstl:if>
	</security:authorize>
	
	<h1> <spring:message code="contract.radioFiles" /> </h1>
	
	<display:table name="radioFiles" id="row" requestURI="${requestURI}" pagesize="5">
		
	<security:authorize access="hasRole('MANAGER')">
		<jstl:if test="${row.contract.signManager == null}">
		<display:column>
			<a href="radioFile/edit.do?radioFileId=${row.id}">   <spring:message code="file.edit"   /> </a> <br>
			<a href="radioFile/delete.do?radioFileId=${row.id}"> <spring:message code="file.delete" /> </a> <br>
		</display:column>
		</jstl:if>
	</security:authorize>
		
    <display:column titleKey="file.sound"    property="sound"    />
    <display:column titleKey="file.name"     property="name"     />
    <display:column titleKey="file.schedule" property="schedule" />
		    
	</display:table>
	
	<security:authorize access="hasRole('MANAGER')">
	<jstl:if test="${row.contract.signManager == null}"> <a href="radioFile/create.do?contractId=${contract.id}"> <spring:message code="file.create.radioFile" /> </a> <br> </jstl:if>
	</security:authorize>
	
	<h1> <spring:message code="contract.TVFiles" /> </h1>
	
	<display:table name="TVFiles" id="row" requestURI="${requestURI}" pagesize="5">
		
	<security:authorize access="hasRole('MANAGER')">
		<jstl:if test="${row.contract.signManager == null}">
		<display:column>
			<a href="TVFile/edit.do?TVFileId=${row.id}">   <spring:message code="file.edit"   /> </a> <br>
			<a href="TVFile/delete.do?TVFileId=${row.id}"> <spring:message code="file.delete" /> </a> <br>
		</display:column>
		</jstl:if>
	</security:authorize>
		
    <display:column titleKey="file.video"    property="video"    />
    <display:column titleKey="file.name"     property="name"     />
    <display:column titleKey="file.schedule" property="schedule" />
		    
	</display:table>
	
	<security:authorize access="hasRole('MANAGER')">
		<jstl:if test="${row.contract.signManager == null}"> <a href="TVFile/create.do?contractId=${contract.id}"> <spring:message code="file.create.TVFile" /> </a> <br> </jstl:if>
	</security:authorize>
	
	<security:authorize access="hasRole('MANAGER')">
		<acme:cancel url="/contract/manager/list.do" code="contract.back"/>
	</security:authorize>
		
	<security:authorize access="hasRole('CUSTOMER')">
		<acme:cancel url="/contract/customer/list.do" code="contract.back"/>
	</security:authorize>
