<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/base.jspf" %>
<spring:message code="sart.title" var="sartTitle" />
<c:url value="/patient/search" var="searchUrl" />
<c:url value="/attendance/add/${patient.getRowID()}" var="newattendance" />
<c:url value="/patient/view/${patient.getRowID()}" var="viewpatient" />
<c:url value="/request/${attendance.getRowID()}/0/charge/add/0" var="addcharge" />
<c:url value="/request/${attendance.getRowID()}/0/diagnosis/add" var="adddiagnosis" />
<c:url value="/request/${attendance.getRowID()}/0/vital/add/0" var="addvital" />
<c:url value="/request/${attendance.getRowID()}/0/observation/add" var="addobservation" />
<c:url value="/request/${attendance.getRowID()}/0/labtest/add" var="addlabtest" />
<c:url value="/request/${attendance.getRowID()}/0/procedure/add" var="addprocedure" />
<c:url value="/request/${attendance.getRowID()}/0/prescription/add" var="addprescription" />
<c:url value="/request/${attendance.getRowID()}/0/radiology/add" var="addradiology" />
<c:url value="/request/${attendance.getRowID()}/0/discharge/add" var="adddischarge" />
<c:url value="/print/lab/${attendance.getRowID()}" var="printlab" />
<c:url value="/print/prescription/${attendance.getRowID()}" var="printrx" />
<c:url value="/print/procedure/${attendance.getRowID()}" var="printprocedure" />
<c:url value="/print/invoice/${attendance.getRowID()}" var="printinvoice" />
<c:url value="/print/radiology/${attendance.getRowID()}" var="printradiology" />
<mt:master>
    <jsp:attribute name="content">
        <div class="att-screen-main">
            <div class="att-screen-header sart-att-status-${attendance.getAttStatus()}">
                <div class="sart_pat_top_bar">
                    <a href="${viewpatient}" class="a-nodecorate">
                        <div class="patdisplay">
                            (${patient.getPatientNumber()}) - ${patient.getFullNames()}, Age: ${patient.getAge()}, Gender: ${patient.getGender()} , Contact: ${patient.getPatientContact()}  
                        </div>
                    </a>
                </div>
                <div class="sart_req_header sart_att_top_bar">
                    Attendance Type: ${attendance.getAttTypeName()}, Start Time : ${attendance.getCreatedDateTime()}, Payment Type: ${paymentMode.getName()}, Status: ${attendance.getAttStatus()}
                </div>
            </div>  
            <div class="sart_req_view">
                <div class="sart-att-action-buttons">
                    <sec:authorize access="hasRole('ROLE_ADD_VITAL')">
                    <a class="btn btn-primary" data-toggle="modal" data-target="#myModal" href="${addvital}" ><i class="fa fa-plus"></i> Vital</a>    
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_ADD_OBSV')">
                    <a class="btn btn-primary" data-toggle="modal" data-target="#myModal" href="${addobservation}" ><i class="fa fa-plus"></i> Doctor's Observation</a>    
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_ADD_DX')">
                    <a class="btn btn-primary" data-toggle="modal" data-target="#myModal" href="${adddiagnosis}" ><i class="fa fa-plus"></i> Diagnosis</a>    
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_ADD_LABREQ')">
                    <a class="btn btn-primary" data-toggle="modal" data-target="#myModal" href="${addlabtest}" ><i class="fa fa-plus"></i> Lab Request</a>    
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_ADD_PROC')">
                    <a class="btn btn-primary" data-toggle="modal" data-target="#myModal" href="${addprocedure}" ><i class="fa fa-plus"></i> Procedure</a>    
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_ADD_RX')">
                    <a class="btn btn-primary" data-toggle="modal" data-target="#myModal" href="${addprescription}" ><i class="fa fa-plus"></i> Prescription</a>    
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_ADD_RAD')">
                    <a class="btn btn-primary" data-toggle="modal" data-target="#myModal" href="${addradiology}" ><i class="fa fa-plus"></i> Radiology</a>    
</sec:authorize>
                    <sec:authorize access="hasRole('ROLE_ADD_CHARGE')">
                    <a class="btn btn-primary" data-toggle="modal" data-target="#myModal" href="${addcharge}" ><i class="fa fa-plus"></i> Charge</a>    
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_ADD_DISCHARGE')">
                    <a class="btn btn-primary" data-toggle="modal" data-target="#myModal" href="${adddischarge}"><i class="fa fa-plus"></i> Discharge</a>        
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_VIEW_PRINTINV')">
                    <a href="#" class="btn btn-primary" data-toggle="modal" data-target="#myModal" onclick="javascript:showrequestview(0, '${printinvoice}');"><i class="fa fa-plus"></i> Print Invoice/Receipt</a>        

                    <a href="#" class="btn btn-primary" data-toggle="modal" data-target="#myModal" onclick="javascript:showrequestview(0, '${printlab}');"><i class="fa fa-plus"></i> Print Lab</a>     

                    <a href="#" class="btn btn-primary" data-toggle="modal" data-target="#myModal" onclick="javascript:showrequestview(0, '${printrx}');"><i class="fa fa-plus"></i> Print Rx</a>        

                    <a href="#" class="btn btn-primary" data-toggle="modal" data-target="#myModal" onclick="javascript:showrequestview(0, '${printradiology}');"><i class="fa fa-plus"></i> Print Radiology</a>        

                    <a href="#" class="btn btn-primary" data-toggle="modal" data-target="#myModal" onclick="javascript:showrequestview(0, '${printprocedure}');"><i class="fa fa-plus"></i> Print Procedure</a>        
</sec:authorize>

                </div>
            </div>
                    <%@include file="../request/list.jsp" %>
        </div>
    </jsp:attribute>
</mt:master>