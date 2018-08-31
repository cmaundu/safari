<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/base.jspf" %>
<spring:message code="sart.title" var="sartTitle" />
<c:url value="/patient/search" var="searchUrl" />
<mt:master>
    <jsp:attribute name="content">
        <div class="att-screen-main">
                <div class="sart-record-banner">
                    <span class="sart-title">Open Attendance List</span>
                </div>
                <c:choose>
                    <c:when test="${attendances.size() == 0}">
                        <i><spring:message code="message.patientView.noAttendances" /></i><br /><br />
                    </c:when>
                    <c:otherwise>

                        <table class="sart-att-req-table">
                            <tr><th></th><th>Visit #</th><th>Patient</th><th>Attendance Type</th><th>Payment Mode</th><th>Status</th><th>Insurance #</th><th>Date</th></tr>
                                    <c:forEach items="${attendances}" var="attendance">
                                <tr>
                                    <td class="icon-button"><a href="<c:url value="/attendance/edit/${attendance.attendanceID}" />" class="icon-button"><image src=" <c:url value="/resources/images/edit_icon.png" />" /></a></td>
                                    <td><a href="<c:url value="/attendance/view/${attendance.attendanceID}" />"> ${attendance.attendanceNumber}</a></td>
                                    <td><a href="<c:url value="/patient/view/${attendance.patientID}" />"> ${attendance.patientName}</a></td>
                                    <td>${attendance.attendanceType}</td>
                                    <td>${attendance.paymentMode}</td>
                                    <td>
                                        <span class="sart-att-status sart-att-status-${attendance.attendanceStatus}">${attendance.attendanceStatus}</span>
                                    </td>
                                    <td>${attendance.insuranceNumber}</td>
                                    <td>${attendance.created}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:otherwise>
                </c:choose>       
        </div>
    </jsp:attribute>
</mt:master>





