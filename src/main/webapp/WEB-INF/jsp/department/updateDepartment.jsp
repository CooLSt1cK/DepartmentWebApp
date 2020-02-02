<jsp:include file="/WEB-INF/jspf/taglib.jspf" %>
<html>
<c:set var="title" value="Delete department" scope="page" />
<jsp:include file="/WEB-INF/jspf/head.jspf" %>
    <body>
       
<jsp:include file="/WEB-INF/jspf/header.jspf" %>
        <div class="container">
            <form action="UpdateDepartment" method="POST">
            <c:if test="${not empty errorMessage}"><p class="text-danger"><c:out value="${errorMessage}"/></p></c:if>
                <div class="form-group">
                    <input type="hidden" name="id" value="<c:out value="${departmentById.id}"/>"/>
                    <label for="name" class="col-form-label">Name</label>
                    <input type="text" class="form-control" value="<c:out value="${departmentById.name}"/>" pattern="([A-Z][a-z]{1,30}( |\b)){1,4}" id="name" name="name" required>
                </div>
                <hr/>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block">Save</button>
                </div>
      </form>
        </div>
        <jsp:include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>