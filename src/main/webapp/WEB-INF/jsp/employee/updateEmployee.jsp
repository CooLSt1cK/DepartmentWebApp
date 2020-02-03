<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<%@ page import = "java.time.LocalDate" %>
<html>
<c:set var="title" value="Add employee for ${departmentById.name}" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>
    <body>
       
<%@ include file="/WEB-INF/jspf/header.jspf" %>
        <div class="container">
        <form action="UpdateEmployee" method="POST">
        <c:if test="${not empty errorMessage}"><p class="text-danger"><c:out value="${errorMessage}"/></p></c:if>
            <input type="hidden" name="id" value="<c:out value="${employeeById.id}"/>"/>
            <input type="hidden" name="employeeDepartmentId" value="<c:out value="${employeeDepartmentId}"/>"/>
            <div class="form-group">
                <label for="email" class="col-form-label">Email</label>
                <input type="email" class="form-control" maxlength="255" pattern="^[a-zA-Z0-9_!#$%&’*+\/=?`{|}~^-]+(?:\.[a-zA-Z0-9_!#$%&’*+\/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$" value="<c:out value="${employeeById.email}"/>" placeholder="you@example.com" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="name" class="col-form-label">Name</label>
                <input type="text" class="form-control" value="<c:out value="${employeeById.name}"/>" placeholder="Last name and first name, example (Ivanov Ivan)" pattern="[A-Z][a-z]{1,29} [A-Z][a-z]{1,29}" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label for="birthday" class="col-form-label">Birthday</label>
                <input type="date" class="form-control" max="<c:out value="<%= LocalDate.now().minusYears(18) %>"/>" value="<c:out value="${employeeById.birthday}"/>" id="birthday" name="birthday" required>
            </div>
            <div class="form-group">
                <label for="payment" class="col-form-label">Payment</label>
                <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text">$</span>
                  </div>
                <input type="number" class="form-control" value="<c:out value="${employeeById.payment}"/>" id="payment" min="1" step="1" name="payment" required>
                </div>
            </div>
            <div class="form-group">
                <label for="departmentId" class="col-form-label">Department</label>
                <select class="form-control" id="departmentId" name="departmentId" required>
                    <c:forEach var="e" items="${departmentList}">
                    <option value="${e.id}" <c:if test="${e.id == employeeById.departmentId}">selected</c:if>><c:out value="${e.name}"/></option>
                    </c:forEach>
                </select>
            </div>
                  <hr/>
                  <div class="form-group">
                      <button type="submit" class="btn btn-primary btn-block">Save</button>
                  </div>
        </form>
        </div>
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>