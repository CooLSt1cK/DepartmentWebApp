<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<html>
<c:set var="title" value="Add department" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>
    <body class="d-flex flex-column h-100">
       
<%@ include file="/WEB-INF/jspf/header.jspf" %>
        <div class="container">
        <form action="AddDepartment" method="POST">
                  <div class="form-group">
                      <c:if test="${not empty errorMessage}"><p class="text-danger"><c:out value="${errorMessage}"/></p></c:if>
                      <label for="name" class="col-form-label">Name</label>
                      <input type="text" class="form-control" value="<c:out value="${departmentWithoutId.name}"/>" pattern="([A-Z][a-z]{1,30}( |\b)){1,4}" id="name" name="name" required>
                  </div>
                  <hr/>
                  <div class="form-group">
                      <button type="submit" class="btn btn-primary btn-block">Add</button>
                  </div>
        </form>
        </div>
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>