<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<html>
<c:set var="title" value="Delete department" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>
    <body>
       
<%@ include file="/WEB-INF/jspf/header.jspf" %>
        <div class="container">
            <table class="table">
                <thead class="thead-light">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th scope="row"><c:out value="${departmentById.id}"/></th>
                        <td><c:out value="${departmentById.name}"/></td>
                    </tr>
                </tbody>
            </table>    
        <form action="DeleteDepartment" method="POST">
            <div class="form-group">
                <input type="hidden" name="id" value="<c:out value="${departmentById.id}"/>"/>
                <button type="submit" class="btn btn-primary btn-block">Delete</button>
            </div>
        </form>
        </div>
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>