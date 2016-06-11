<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<html lang="en">
	<jsp:include page="../menu/header.jsp" />
	<body>
		<jsp:include page="../menu/topMenu.jsp" />
		<div class="container-fluid">
			<div class="row-fluid">
		        <div class="span10">
		        	<c:forEach items="${tags}" var="tag">
		        		<div class="tag">
			        		<div class="name">
								<a href="<c:url value="/tags/view?id=${tag.id}"/>">${tag.name}</a>
							</div>
							<div class="questions">
								${fn:length(tag.questions)} Questions
							</div>
							<div class="userinfo">
								by <b>${tag.createdBy.firstName}</b>
								<i>(<fmt:formatDate value="${tag.createdDate}" pattern="dd-MM-yyyy" />)</i>
							</div>
	        			</div>
					</c:forEach>
					<div class="spacer"> &nbsp; </div>
					<div class="pagination pagination-centered">
						<ul>
							<li> <a href="<c:url value="/${pageInfo.firstPage}"/>"> &laquo; &nbsp; First </a> </li>
							<li> <a href="<c:url value="/${pageInfo.previousPage}"/>"> &lt; &nbsp; Previous </a> </li>
							<li> <a href="<c:url value="/${pageInfo.nextPage}"/>"> Next &nbsp; &gt; </a> </li>
							<li> <a href="<c:url value="/${pageInfo.lastPage}"/>"> Last &nbsp; &raquo; </a> </li>
						</ul>
					</div>
				</div><!--/span-->
				<jsp:include page="../menu/rightMenu.jsp" />
			</div><!--/row-->
			<hr>
			<jsp:include page="../menu/footer.jsp" />
		</div><!--/.fluid-container-->
		<jsp:include page="../menu/includeScripts.jsp" />
	</body>
</html>