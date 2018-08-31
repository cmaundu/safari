<div class="modal-content modal-print-content">
    <%@include file="invoiceheader.jsp" %>
    <div class="print-sub-title">The following drugs have been prescribed.</div>
    <table class="sart-print-table">
        <tbody><tr><th>Name</th><th>Dose</th></tr>
                    <c:forEach items="${prescriptions}" var="prescription">
                        <c:if test="${prescription.quantity > 0}">
                    <tr>
                        <td>${prescription.drugDef.drugName}</td> 
                        <td>${prescription.dose}</td>
                    </tr>
                </c:if>
            </c:forEach>
        </tbody>
    </table>

    <div class="sart-sign">
        <table class="sart-sign-line">
            <tbody><tr><td><div class="sart-sign-line sart-left-half">${user.getFullNames()}</div></td>
                    <td><div class="sart-sign-line sart-left-half">Date</div></td></tr>
            </tbody></table>
    </div>
</div>
