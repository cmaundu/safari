<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/base.jspf" %>
<spring:message code="sart.title" var="sartTitle" />
<mt:master>
    <jsp:attribute name="content">
        <div class="att-screen-main">
            <div class="sart-record-banner">
                <span class="sart-title"> Prescription</span>
                <a href="<c:url value="/reports/rx" />" class="btn btn-result" data-toggle="modal" data-target="#myModal"><i class="fa fa-plus"></i> Prescription</a>
                <a href="<c:url value="/reports/" />" class="btn btn-view" data-toggle="modal" data-target="#myModal"><i class="fa fa-plus"></i> Daily Summaries</a>
            </div>
                <table class="sart-report-table">
                    <tbody>
                        <tr>
                            <th>Drug Name</th><th>Total</th><th>Quantity</th>
                                <c:forEach items="${rx.cols}" var="col">
                                <th>${col}</th>
                                </c:forEach>
                        </tr>
                        <c:forEach items="${rx.rows}" var="row">
                            <tr>
                                <td>${row}</td>                                    
                                <td class="sart-report-total">
                                    <fmt:setLocale value = "en_US"/>
                                    <fmt:formatNumber value = "${rx.getAmount(row)}" type = "currency" currencySymbol=""/>
                                </td>
                                <td class="sart-report-total">
                                    <fmt:setLocale value = "en_US"/>
                                    <fmt:formatNumber value = "${rx.getQuantity(row)}"/>
                                </td>                                
                                <c:forEach items="${rx.cols}" var="col">
                                    <td>
                                        <fmt:setLocale value = "en_US"/>
                                        <fmt:formatNumber value = "${rx.getQuantity(row,col)}" type = "currency" maxFractionDigits="0" currencySymbol=""/>
                                    </td>
                                </c:forEach>
                            </tr>
                        </c:forEach>

                </table>       
        </div>
    </jsp:attribute>
</mt:master>