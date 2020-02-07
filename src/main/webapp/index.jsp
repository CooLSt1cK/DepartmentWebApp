<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<html>
<c:set var="title" value="Department Web App" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

    <body class="d-flex flex-column h-100">
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
        <div class="container">
        <c:if test="${not empty errorMessage}"><p class="text-danger"><c:out value="${errorMessage}"/></p></c:if>
            <table class="table">
                <thead class="thead-light">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="e" items="${departmentList}">
                    <tr>
                        <th scope="row"><c:out value="${e.id}"/></th>
                        <td><c:out value="${e.name}"/></td>
                        <td><a href="UpdateDepartment?id=<c:out value="${e.id}"/>" class="btn btn-primary btn-sm mr-3">edit</a>
                            <a href="DeleteDepartment?id=<c:out value="${e.id}"/>" class="btn btn-primary btn-sm mr-3">delete</a>
                            <a href="EmployeeServlet?id=<c:out value="${e.id}"/>" class="btn btn-primary btn-sm mr-3">employee list</a>
                        </td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a href="AddDepartment" class="btn btn-primary btn-block">Add</a href="#add">
        </div>
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>