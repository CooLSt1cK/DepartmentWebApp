<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<html>
<c:set var="title" value="Error page" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

    <body>
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
        <div class="container">
        <div class="d-flex justify-content-center">
            <div class="lead md-3"><svg width="60" height="53" viewBox="0 0 60 53" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path fill-rule="evenodd" clip-rule="evenodd" d="M33.349 4.92422C32.661 3.90558 31.3997 3.28125 30.0237 3.28125C28.6477 3.28125 27.3863 3.90558 26.6983 4.92422L0.516002 44.2898C-0.172001 45.3085 -0.172001 46.5571 0.516002 47.5758C1.24223 48.5944 2.50357 49.2187 3.84135 49.2187H56.206C57.582 49.2187 58.8434 48.5944 59.4931 47.5758C60.1429 46.5571 60.1811 45.3085 59.5314 44.2898L33.349 4.92422ZM33.8459 42.6469H26.2014V36.075H33.8459V42.6469V42.6469ZM33.8459 32.789H26.2014V19.6453H33.8459V32.789V32.789Z" fill="#DC3545"/>
                </svg> <c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
                       				<c:set var="message" value="${requestScope['javax.servlet.error.message']}"/>

                       				<%-- this way we get the exception --%>
                       				<c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>

                       				<c:if test="${not empty code}">
                       					<h3>Error code: ${code}</h3>
                       				</c:if>

                       				<c:if test="${not empty message}">
                       					<h3>Message: ${message}</h3>
                       				</c:if>

                       				<%-- if get this page using forward --%>
                       				<c:if test="${not empty errorMessage and empty exception and empty code}">
                       					<h3>Error message: ${errorMessage}</h3>
                       				</c:if>

                       				<%-- this way we print exception stack trace --%>
                       				<c:if test="${not empty exception}">
                       					<hr/>
                       					<h3>Stack trace:</h3>
                       					<c:forEach var="stackTraceElement" items="${exception.stackTrace}">
                       						${stackTraceElement}
                       					</c:forEach>
                       				</c:if>
		</div>
		</div>
            <div class = "row mt-1">
            <a class="btn btn-danger btn-block" href="index.jsp" role="button"><svg width="50" height="37" viewBox="0 0 50 37" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path fill-rule="evenodd" clip-rule="evenodd" d="M50 5.71875L16.6667 36.2188L0 20.9688L6.25 15.25L16.6667 24.7813L43.75 0L50 5.71875V5.71875Z" fill="white"/>
                </svg></a>
                </div>
                <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
