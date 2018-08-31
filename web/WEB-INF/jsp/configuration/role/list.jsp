
            <div class="sart-record-banner">            
                <span class="sart-title">Role List</span>
                <a href="<c:url value="/administration/role/add" />" class="btn btn-result" data-toggle="modal" data-target="#myModal"><i class="fa fa-plus"></i> New Role</a>
                <a href="<c:url value="/administration/user" />" class="btn btn-view" data-toggle="modal" data-target="#myModal"><i class="fa fa-plus"></i> User List</a>
            </div>
                <table class="sart-att-req-table">
                    <tr><th></th><th>Role Name</th><th>Description</th><th>Created On</th></tr>
                            <c:forEach items="${roles}" var="item">
                        <tr><td class="icon-button"><a href="<c:url value="/administration/role/edit/${item.rowID}" />" class="icon-button"><image src=" <c:url value="/resources/images/edit_icon.png" />" /></a></td>
                            <td>${item.roleName}</td>
                            <td>${item.roleDesc}</td>
                            <td>${item.getCreatedDateTime()}</td></tr>
                        </c:forEach>
                </table>     
        </div>
