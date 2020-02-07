<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<html>
<c:set var="title" value="Delete employee" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>
    <body class="d-flex flex-column h-100">
       
<%@ include file="/WEB-INF/jspf/header.jspf" %>
        <div class="container">
            <table class="table">
                <thead class="thead-light">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Email</th>
                        <th scope="col">Name</th>
                        <th scope="col">Birthday</th>
                        <th scope="col">Payment</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th scope="row"><c:out value="${employeeById.id}"/></th>
                        <td><c:out value="${employeeById.email}"/></td>
                        <td><c:out value="${employeeById.name}"/></td>
                        <td><c:out value="${employeeById.birthday}"/></td>
                        <td><c:out value="${employeeById.payment}"/>$</td>
                    </tr>
                </tbody>
            </table>    
        <form action="DeleteEmployee" method="POST">
                  <div class="form-group">
                      <input type="hidden" name="id" value="${employeeById.id}"/>
                      <input type="hidden" name="departmentId" value="${departmentId}"/>
                      <button type="submit" class="btn btn-primary btn-block">Delete</button>
                  </div>
        </form>
        </div>
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>