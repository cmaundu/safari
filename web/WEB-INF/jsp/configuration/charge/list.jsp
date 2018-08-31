

<table class="sart-att-req-table" style="font-size: 0.9em">
    <tr><th>Item Name</th>
            <c:forEach items="${pmlist}" var="pm">
            <th>${pm.name}</th>
            </c:forEach>
    </tr>
    <c:forEach items="${cdlist.rows}" var="cd">
        <tr>
            <td>${cdlist.getLovName(cd)}</td>
            <c:forEach items="${pmlist}" var="pm">

                <td class="number">
                    <c:choose>
                        <c:when test="${cdlist.getChargeAmount(cd, pm.rowID) < 0}">
                            (Not Allowed)
                        </c:when>
                        <c:otherwise>
                            <fmt:setLocale value = "en_US"/>
                            <fmt:formatNumber value = "${cdlist.getChargeAmount(cd, pm.rowID)}" type = "currency" maxFractionDigits="0" currencySymbol=""/>            

                        </c:otherwise>
                    </c:choose>
                </td>
            </c:forEach>
        </tr>
    </c:forEach>
</table>     
</div>
