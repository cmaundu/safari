<c:choose>
    <c:when test="${attendances.totalElements == 0}">
        <i><spring:message code="message.patientView.noAttendances" /></i><br /><br />
    </c:when>
    <c:otherwise>

        <table class="sart-att-req-table">
            <tr><th></th><th>Visit #</th><th>Attendance Type</th><th>Payment Mode</th><th>Status</th><th>Insurance #</th><th>Doctor</th><th>Date</th></tr>
                    <c:forEach items="${attendances.content}" var="attendance">
                <tr>
                    <td class="icon-button"><a href="<c:url value="/attendance/edit/${attendance.getRowID()}" />" class="icon-button"><image src=" <c:url value="/resources/images/edit_icon.png" />" /></a></td>
                    <td><a href="<c:url value="/attendance/view/${attendance.getRowID()}" />"> ${attendance.attNumber}</a></td>
                    <td> ${attendance.getAttTypeName()}</td>
                    <td>${attendance.getPaymentMode().getName()}</td>
                    <td>
                        <span class="sart-att-status sart-att-status-${attendance.getAttStatus()}">${attendance.getAttStatus()}</span>
                    </td>
                    <td>${attendance.getInsuranceNumber()}</td>
                    <td>Not Assigned</td>
                    <td>${attendance.getCreatedDate()}</td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
