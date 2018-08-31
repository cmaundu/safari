<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/base.jspf" %>
<spring:message code="sart.title" var="sartTitle" />
<c:url value="/patient/search" var="searchUrl" />
<c:url value="/attendance/add/${patient.getRowID()}" var="newattendance" />
<mt:master>
    <jsp:attribute name="content">

            <div class="att-screen-main">
                <!--span class="sart-title">Patient Detail</span-->
                <div class="sart_pat_top_bar part_pat_underline">
                    <div class="patdisplay">
                        ${patient.getFullNames()}, Age: ${patient.getAge()}, Gender: ${patient.getGender()} , Contact: ${patient.getPatientContact()} (${patient.getPatientNumber()}) >> 
                        <sec:authorize access="hasRole('ROLE_ADD_ATT')">
                        <a href="${newattendance}" class="btn btn-result" data-toggle="modal" data-target="#myModal"><i class="fa fa-plus"></i>New Attendance</a>
                        </sec:authorize>
                    </div>

                </div>   
                    <div class="sart-att-div1">
                         <%@include file="../attendance/list.jsp" %>
                    </div>                    
            </div>           

    </jsp:attribute>
</mt:master>