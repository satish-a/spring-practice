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
					<div class="question">
						<div class="header">
							${question.subject}
						</div>
						<div class="metainfo">
							<a class="icon-pencil"></a> ${fn:length(question.answers)}
							<a class="icon-thumbs-up"></a> ${fn:length(question.votes)}
							<a class="icon-bookmark"></a> ${fn:length(question.bookmarks)}
							<a class="icon-eye-open"></a> ${question.viewCount}
						</div>
						<div class="content">${question.content}</div>
						<div class="tags">
							<c:forEach items="${question.tags}" var="tag">
								<a href="<c:url value="/tags/view?name=${tag}"/>">${tag}</a>
							</c:forEach>
						</div>
						<div class="actions">
							<a data-toggle="collapse" data-target="#qcomment" class="icon-comment"></a>
							<a class="icon-bookmark" href="<c:url value="/question/bookmark?questionId=${question.id}"/>"></a>
							<a class="icon-thumbs-up" href="<c:url value="/question/vote?questionId=${question.id}"/>"></a>
							<div id="qcomment" class="collapse">
								<form method="post" action="<c:url value="/question/comment/add"/>">
								  <fieldset>
								    <label><b>Your Comment</b></label>
								    <input name="questionId" type="hidden" value="${question.id}">
								    <textarea class="text required span12" id="content" name="content" rows="5" ></textarea>
								    <input class="btn btn-primary" name="commit" type="submit" value="Post Comment">
								  </fieldset>
								</form> 
							</div>
						</div>
						<div class="userinfo">
							by <b>${question.createdBy.firstName}</b>
							<i>(<fmt:formatDate value="${question.createdDate}" pattern="dd-MM-yyyy" />)</i>
						</div>
						<c:forEach items="${question.comments}" var="comment">
							<div class="comment">
								${comment.content} - <i>by ${comment.createdBy.firstName}</i> 
								<i>(<fmt:formatDate value="${comment.createdDate}" pattern="dd-MM-yyyy" />)</i>
							</div>
						</c:forEach>
						<div class="answers">
							<h4>Answers</h4>
							<c:forEach items="${question.answers}" var="answer">
				        		<div class="answer">
					        		<div class="content">${answer.content}</div>
					        		<div class="actions">
										<a data-toggle="collapse" data-target="#${answer.id}" class="icon-comment"></a>
										<a class="icon-star" href="#"></a>
										<div id="${answer.id}" class="collapse">
											<form method="post" action="<c:url value="/question/answer/comment/add"/>">
											  <fieldset>
											    <label><b>Your Comment</b></label>
											    <input name="questionId" type="hidden" value="${question.id}">
											    <input name="answerId" type="hidden" value="${answer.id}">
											    <textarea class="text required span12" id="content" name="content" rows="5" ></textarea>
											    <input class="btn btn-primary" name="commit" type="submit" value="Post Comment">
											  </fieldset>
											</form> 
										</div>
									</div>
									<div class="userinfo">
										by <b>${answer.createdBy.firstName}</b>
										<i>(<fmt:formatDate value="${answer.createdDate}" pattern="dd-MM-yyyy" />)</i>
									</div>
					        		<c:forEach items="${answer.comments}" var="comment">
										<div class="comment">
											${comment.content} - <i>by ${comment.createdBy.firstName}</i>
											<i>(<fmt:formatDate value="${comment.createdDate}" pattern="dd-MM-yyyy" />)</i>
										</div>
									</c:forEach>
			        			</div>
							</c:forEach>
						</div>
						<form method="post" action="<c:url value="/question/answer/add"/>">
						  <fieldset>
						    <label><b>Your Answer</b></label>
						    <input name="questionId" type="hidden" value="${question.id}">
						    <textarea class="text required span12" id="content" name="content" rows="5" ></textarea>
						    <input class="btn btn-primary" name="commit" type="submit" value="Post Answer">
						  </fieldset>
						</form>
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