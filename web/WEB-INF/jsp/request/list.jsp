<table class="sart-att-req-table">
    <tbody>
        <tr><th></th><th></th><th></th><th>Type</th><th>Created</th><th>Created By</th><th colspan="2" class="number">Amount (Kes)</th><th></th><th>Additional Info</th></tr>
    </tbody>
    <c:url value="/resources/images/edit_icon.png" var="editIcon" />
    <c:forEach items="${requests}" var="request">
        <c:url value="/request/view/${request.rowID}" var="viewurl" />
        <c:url value="/request/${request.attendanceID}/${request.rowID}/payment/add" var="payurl" />
        <c:url value="/request/${request.attendanceID}/${request.rowID}/labtest/fulfil" var="labfulfil" />
        <c:url value="/request/${request.attendanceID}/${request.rowID}/radiology/fulfil" var="radfulfil" />
        <c:url value="/request/${request.attendanceID}/${request.rowID}/procedure/fulfil" var="procfulfil" />
        <c:url value="/request/${request.attendanceID}/${request.rowID}/prescription/edit" var="editrx" />
        <c:url value="/request/${request.attendanceID}/${request.rowID}/prescription/dispense" var="dispenserx" />
        <c:url value="/resources/images/request_${request.requestType}.png" var="requestimg" />

        <c:url value="/request/${request.attendanceID}/${request.rowID}/charge/edit" var="editcharge" />  
        <c:url value="/request/${request.attendanceID}/${request.rowID}/observation/edit" var="editobsrv" />          

        <tr>
            <td class="sart-req-icons">
                <c:choose>
                    <c:when test="${(pageContext.request.isUserInRole('ROLE_VIEW_VITAL') && request.requestType == 14) ||
                                    (pageContext.request.isUserInRole('ROLE_VIEW_OBSV') && request.requestType == 5) ||
                                    (pageContext.request.isUserInRole('ROLE_VIEW_DX') && request.requestType == 2) ||
                                    (pageContext.request.isUserInRole('ROLE_VIEW_LABREQ') && request.requestType == 11) ||
                                    (pageContext.request.isUserInRole('ROLE_VIEW_PROC') && request.requestType == 8) ||
                                    (pageContext.request.isUserInRole('ROLE_VIEW_RX') && request.requestType == 7) ||
                                    (pageContext.request.isUserInRole('ROLE_VIEW_RAD') && request.requestType == 9) ||
                                    (pageContext.request.isUserInRole('ROLE_VIEW_CHARGE') && request.requestType == 15) || 
                                    (pageContext.request.isUserInRole('ROLE_VIEW_DISCHARGE') && request.requestType == 3) 
                            }">
                        <a href="javascript:showrequestview(${request.rowID},'${viewurl}');">
                            <div class="sart_request_item_img">
                                <img class="sart_req_img sart_img" src="${requestimg}">
                            </div>
                        </a>
                    </c:when>
                    <c:otherwise>
                        <div class="sart_request_item_img">
                            <img class="sart_req_img sart_img" src="${requestimg}">
                        </div>
                    </c:otherwise>
                </c:choose>
            </td>
            <td class="icon-button">
                <c:choose>
                    <c:when test="${request.requestType == 7 && pageContext.request.isUserInRole('ROLE_EDIT_RX')}">
                        <a href="${editrx}" class="icon-button">
                            <image src=" <c:url value="/resources/images/edit_icon.png" />" />
                        </a>           
                    </c:when>
                    <c:when test="${request.requestType == 15 && pageContext.request.isUserInRole('ROLE_EDIT_CHARGE')}">
                        <a href="${editcharge}" class="icon-button">
                            <image src=" <c:url value="/resources/images/edit_icon.png" />" />
                        </a>           
                    </c:when>  
                    <c:when test="${request.requestType == 5 && pageContext.request.isUserInRole('ROLE_EDIT_OBSV')}">
                        <a href="${editobsrv}" class="icon-button">
                            <image src=" <c:url value="/resources/images/edit_icon.png" />" />
                        </a>           
                    </c:when>  

                    <c:otherwise>
                        <a href="#" class="icon-button">
                            <image src=" <c:url value="/resources/images/readonly.png" />" />
                        </a>

                    </c:otherwise>
                </c:choose>
            </td>
            <td class="icon-button">
                <c:choose>
                    <c:when test="${request.requestType == 7 && pageContext.request.isUserInRole('ROLE_DEL_RX')}">
                        <a href="${editrx}" class="icon-button">
                            <image src=" <c:url value="/resources/images/delete_icon.png" />" />
                        </a>           
                    </c:when>
                    <c:when test="${request.requestType == 15 && pageContext.request.isUserInRole('ROLE_DEL_CHARGE')}">
                        <a href="${editcharge}" class="icon-button">
                            <image src=" <c:url value="/resources/images/delete_icon.png" />" />
                        </a>           
                    </c:when>  
                    <c:when test="${request.requestType == 5 && pageContext.request.isUserInRole('ROLE_DEL_OBSV')}">
                        <a href="${editobsrv}" class="icon-button">
                            <image src=" <c:url value="/resources/images/delete_icon.png" />" />
                        </a>           
                    </c:when>  

                    <c:otherwise>
                        <a href="#" class="icon-button">
                            <image src=" <c:url value="/resources/images/readonly.png" />" />
                        </a>

                    </c:otherwise>
                </c:choose>
            </td>            
            <td>${request.title}</td>
            <td>${request.getCreatedDateTime()}</td>
            <td>${request.getCreatedByUser().surname}</td>
            <c:choose>
                <c:when test="${request.requestType == 3 && pageContext.request.isUserInRole('ROLE_VIEW_DISCHARGE')}">
                    <td class="sart-td-moreinfo" colspan="9" >${request.note}</td>
                </c:when>
                <c:when test="${request.requestType == 14 && pageContext.request.isUserInRole('ROLE_VIEW_VITAL')}">
                    <td class="sart-td-moreinfo" colspan="9" >${request.note}</td>
                </c:when>                        
                <c:when test="${request.requestType == 5 && pageContext.request.isUserInRole('ROLE_VIEW_OBSV')}">
                    <td class="sart-td-moreinfo" colspan="9" >${request.note}</td>
                </c:when> 
                <c:when test="${request.requestType == 2 && pageContext.request.isUserInRole('ROLE_VIEW_DX')}">
                    <td class="sart-td-moreinfo" colspan="9" >${request.note}</td>
                </c:when> 
                <c:when test="${request.requestType == 2 || request.requestType == 3 || request.requestType == 5 || request.requestType == 14 }">
                    <td class="sart-td-moreinfo" colspan="9" >(No Access)</td>
                </c:when>                          
                <c:otherwise>
                    <td class="number">

                        <c:choose>
                            <c:when test="${(request.amountCharged ==100000 && request.requestType == 7)}" >
                                <span class="sart-att-status sart-request-pending">(Pending Qty)</span>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${pageContext.request.isUserInRole('ROLE_VIEW_CHARGE')}">                                        <fmt:setLocale value = "en_US"/>
                                        <fmt:formatNumber value = "${request.amountCharged}" type = "currency" currencySymbol=""/>
                                    </c:when>
                                    <c:otherwise>
                                        (No Access)
                                    </c:otherwise>
                                </c:choose>                                    
                            </c:otherwise>
                        </c:choose>

                    </td>
                    <td class="sart-td-button">
                        <c:choose>
                            <c:when test="${(request.amountCharged ==0 || !request.chargeable)}" >
                                <span class="sart-att-status sart-request-noaction">(No Charges)</span>
                            </c:when>
                            <c:when test="${request.paid}" >
                                <span class="sart-att-status sart-request-completed">(Fully Paid)</span>

                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${(request.amountCharged ==100000 && request.requestType == 7)}" >
                                        <a href="${editrx}" class="btn btn-result"><i class="fa fa-plus"></i> Update</a>
                                    </c:when>
                                    <c:otherwise>
                                        <c:choose>
                                            <c:when test="${pageContext.request.isUserInRole('ROLE_ADD_PAY')}">  
                                                <a href="${payurl}" class="btn btn-pay" ><i class="fa fa-plus"></i> Pay</a>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="sart-att-status sart-request-pending">(Payment Pending)</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${((request.amountCharged ==0 || !request.chargeable) || request.paid)}">
                                <c:choose>
                                    <c:when test="${!request.fulfilled}">
                                        <c:choose>
                                            <c:when test="${request.requestType == 11 && pageContext.request.isUserInRole('ROLE_EDIT_LABREQ')}">
                                                <a href="${labfulfil}" class="btn btn-result" ><i class="fa fa-plus"></i> Submit Results</a>
                                            </c:when>
                                            <c:when test="${request.requestType == 9 && pageContext.request.isUserInRole('ROLE_EDIT_RAD')}">
                                                <a href="${radfulfil}" class="btn btn-result" ><i class="fa fa-plus"></i> Submit Results</a>
                                            </c:when>   
                                            <c:when test="${request.requestType == 8 && pageContext.request.isUserInRole('ROLE_VIEW_PROC')}">
                                                <a href="${procfulfil}" class="btn btn-result" ><i class="fa fa-plus"></i> Submit Results</a>
                                            </c:when>
                                            <c:when test="${request.requestType == 8 || request.requestType == 9 || request.requestType == 11}" >
                                                <span class="sart-att-status sart-request-pending">(Pending Results)</span>
                                            </c:when>
                                            <c:when test="${request.requestType == 7 && pageContext.request.isUserInRole('ROLE_EDIT_RX')}">
                                                <a href="${dispenserx}" class="btn btn-result" ><i class="fa fa-plus"></i> Dispense</a>
                                            </c:when>
                                            <c:when test="${request.requestType == 7}" >
                                                <span class="sart-att-status sart-request-pending">(Pending Dispense)</span>
                                            </c:when>
                                        </c:choose>                            
                                    </c:when>
                                    <c:otherwise>
                                        <c:choose>
                                            <c:when test="${request.requestType == 11 && pageContext.request.isUserInRole('ROLE_VIEW_LABREQ')}">
                                                <a href="javascript:showrequestview(${request.rowID},'${viewurl}');" class="btn btn-view" >Lab Results</a>
                                            </c:when>
                                            <c:when test="${request.requestType == 9 && pageContext.request.isUserInRole('ROLE_VIEW_RAD')}">
                                                <a href="javascript:showrequestview(${request.rowID},'${viewurl}');" class="btn btn-view" >Radiology Results</a>
                                            </c:when>   
                                            <c:when test="${request.requestType == 8 && pageContext.request.isUserInRole('ROLE_VIEW_PROC')}">
                                                <a href="javascript:showrequestview(${request.rowID},'${viewurl}');" class="btn btn-view" >Procedure Results</a>
                                            </c:when>
                                            <c:when test="${request.requestType == 7 && pageContext.request.isUserInRole('ROLE_VIEW_RX')}">
                                                <a href="javascript:showrequestview(${request.rowID},'${viewurl}');" class="btn btn-view" >Rx Dispensed</a>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="sart-att-status sart-request-completed">(Results Available)</span>
                                            </c:otherwise>
                                        </c:choose>                                         
                                    </c:otherwise>
                                </c:choose>
                            </c:when>                      
                        </c:choose>
                    </td><td class="sart-td-moreinfo">${request.note}</td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
</table>