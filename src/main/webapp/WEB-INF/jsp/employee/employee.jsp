<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<html>
<c:set var="title" value="Employee list for ${departmentById.name}" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

    <body class="d-flex flex-column h-100">
        
<%@ include file="/WEB-INF/jspf/header.jspf" %>
        <div class="container">
        <c:if test="${not empty errorMessage}"><p class="text-danger"><c:out value="${errorMessage}"/></p></c:if>
            <table class="table">
                <thead class="thead-light">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Email</th>
                        <th scope="col">Name</th>
                        <th scope="col">Birthday</th>
                        <th scope="col">Payment</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="e" items="${employeeList}">
                    <tr>
                        <th scope="row"><c:out value="${e.id}"/></th>
                        <td><c:out value="${e.email}"/></td>
                        <td><c:out value="${e.name}"/></td>
                        <td><c:out value="${e.birthday}"/></td>
                        <td><c:out value="${e.payment}"/>$</td>
                        <td>
                            <div class="d-flex justify-content-center">
                            <a href="UpdateEmployee?id=<c:out value="${e.id}"/>" class="btn btn-primary btn-sm mr-3">edit</a>
                            <a href="DeleteEmployee?id=<c:out value="${e.id}"/>" class="btn btn-primary btn-sm">delete</a>
                            </div>
                        </td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a href="AddEmployee?departmentId=<c:out value="${departmentById.id}"/>" class="btn btn-primary btn-block">Add</a href="#add">
        </div>
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>