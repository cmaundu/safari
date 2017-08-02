<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/base.jspf" %>
<spring:message code="sart.title" var="sartTitle" />
<c:url value="/patient/search" var="searchUrl" />
<mt:master>
    <jsp:attribute name="content">
        <div class="att-screen-main">
            <div class="sart-att-request-list">
                <span class="sart-title">Courier List</span>
                  <form:form method="post"  modelAttribute="CourierSearchForm">
                    <div>
                        <span>
                            <form:input class="sart-search-input" path="query" size="40" />
                            <input type="submit" class="sart-search-button" value="Search" />
                        </span>
                    </div>
                </form:form> 
                <table class="sart-att-req-table">
                    <tr><th>Slug</th><th>Name</th><th>Phone</th><th>Other Name</th><th>Url</th></tr>

                    <c:forEach items="${couriers}" var="courier">
                        <tr>
                            <td>${courier.slug}</td>
                            <td>${courier.name}</td>
                            <td>${courier.phone}</td>
                            <td>${courier.otherName}</td>
                            <td>${courier.url}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>           
        </div>
    </jsp:attribute>
</mt:master>




