<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
		
	<display:table name="billboardFiles" id="row" requestURI="${requestURI}" pagesize="5">
		
	<security:authorize access="hasRole('MANAGER')">
		<jstl:if test="${row.contract.signManager == null}">
		<display:column>
			<a href="billboardFile/edit.do?billboardFileId=${row.id}">   <spring:message code="file.edit" />  </a><br>
			<a href="billboardFile/delete.do?billboardFileId=${row.id}"> <spring:message code="file.delete" /></a><br>
		</display:column>
		</jstl:if>
	</security:authorize>
		
    <display:column titleKey="file.location" property="location" />
    <display:column titleKey="file.image"    property="image"    />
		    
	</display:table>
	
	<display:table name="infoFiles" id="row" requestURI="${requestURI}" pagesize="5">
		
	<security:authorize access="hasRole('MANAGER')">
		<jstl:if test="${row.contract.signManager == null}">
		<display:column>
			<a href="infoFile/edit.do?infoFileId=${row.id}">   <spring:message code="file.edit" />  </a><br>
			<a href="infoFile/delete.do?infoFileId=${row.id}"> <spring:message code="file.delete" /></a><br>
		</display:column>
		</jstl:if>
	</security:authorize>
		
    <display:column titleKey="file.title" property="title" />
    <display:column titleKey="file.text"  property="text"  />
		    
	</display:table>
	
	<display:table name="radioFiles" id="row" requestURI="radioFile/list.do" pagesize="5">
		
	<security:authorize access="hasRole('MANAGER')">
		<jstl:if test="${row.contract.signManager == null}">
		<display:column>
			<a href="radioFile/edit.do?radioFileId=${row.id}">   <spring:message code="file.edit" />  </a><br>
			<a href="radioFile/delete.do?radioFileId=${row.id}"> <spring:message code="file.delete" /></a><br>
		</display:column>
		</jstl:if>
	</security:authorize>
		
    <display:column titleKey="file.sound"    property="sound"    />
    <display:column titleKey="file.name"     property="name"     />
    <display:column titleKey="file.schedule" property="schedule" />
		    
	</display:table>
	
	<display:table name="TVFiles" id="row" requestURI="TVFile/list.do" pagesize="5">
		
	<security:authorize access="hasRole('MANAGER')">
		<jstl:if test="${row.contract.signManager == null}">
		<display:column>
			<a href="TVFile/edit.do?TVFileId=${row.id}">   <spring:message code="file.edit" />  </a><br>
			<a href="TVFile/delete.do?TVFileId=${row.id}"> <spring:message code="file.delete" /></a><br>
		</display:column>
		</jstl:if>
	</security:authorize>
		
    <display:column titleKey="file.video"    property="video"    />
    <display:column titleKey="file.name"     property="name"     />
    <display:column titleKey="file.schedule" property="schedule" />
		    
	</display:table>
	
	<display:table name="socialNetworkFiles" id="row" requestURI="socialNetworkFile/list.do" pagesize="5">
		
	<security:authorize access="hasRole('MANAGER')">
		<jstl:if test="${row.contract.signManager == null}">
		<display:column>
			<a href="socialNetworkFile/edit.do?socialNetworkFileId=${row.id}">   <spring:message code="file.edit" />  </a><br>
			<a href="socialNetworkFile/delete.do?socialNetworkFileId=${row.id}"> <spring:message code="file.delete" /></a><br>
		</display:column>
		</jstl:if>
	</security:authorize>
		
    <display:column titleKey="file.banner" property="banner" />
    <display:column titleKey="file.target" property="target" />
		    
	</display:table>