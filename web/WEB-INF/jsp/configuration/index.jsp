<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/base.jspf" %>

<mt:master>
    <jsp:attribute name="content">
        <div class="att-screen-main">
            <div class="nav-tabs-custom">
                <ul class="nav nav-tabs">
                    
                    <li class=<c:if test="${not empty userlist }">"active"</c:if>>
                        <a href="<c:url value="/administration/user" />" data-toggle="tab" aria-expanded="false">User</a>
                    </li> 
                    <li class=<c:if test="${not empty rolelist}">"active"</c:if>>
                        <a href="<c:url value="/administration/role" />" data-toggle="tab" aria-expanded="true">Roles</a>
                    </li>
                    <li class=<c:if test="${not empty chargelist}">"active"</c:if>>
                        <a href="<c:url value="/administration/charges/procedure" />" data-toggle="tab" aria-expanded="false">Charges</a>
                    </li>
                    <li class="pull-right">
                        <a href="#" class="text-muted"><i class="fa fa-gear"></i>
                        </a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane active" id="tab_1">
                        <c:choose>
                            <c:when test="${not empty userlist}">
                                <%@include file="user/list.jsp" %>
                            </c:when>
                            <c:when test="${not empty rolelist}">
                                <%@include file="role/list.jsp" %>
                            </c:when> 
                            <c:when test="${not empty chargelist}">
                                <div class="sart-record-banner">            
                                    <span class="sart-title">${title}
                                        <span class="sart-mini-title">
                                            (<a href="<c:url value="/administration/charges/labtest" />">Lab Tests</a> |
                                            <a href="<c:url value="/administration/charges/procedure" />">Procedures</a> |
                                            <a href="<c:url value="/administration/charges/radiology" />">Radiology</a> |
                                            <a href="<c:url value="/administration/charges/prescription" />">Prescriptions</a> |
                                            <a href="<c:url value="/administration/charges/attendance" />">Attendances</a>)
                                        </span>
                                    </span>


                                </div> 
                                <%@include file="charge/list.jsp" %>
                            </c:when>                             
                            <c:otherwise>
                                <div class="sart-record-banner">
                                    <span class="sart-title">System Configuration</span>

                                    Select an option.
                                </div>
                            </c:otherwise>
                        </c:choose>

                        <!-- /.tab-pane -->

                        <!-- /.tab-pane -->
                    </div>
                    <!-- /.tab-content --> 
                </div>
            </jsp:attribute>
        </mt:master>




