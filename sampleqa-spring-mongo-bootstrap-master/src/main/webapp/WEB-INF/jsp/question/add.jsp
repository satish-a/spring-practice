<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<html lang="en">
	<jsp:include page="../menu/header.jsp" />

	<body>
		<jsp:include page="../menu/topMenu.jsp" />
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span10">
					<form accept-charset="UTF-8" action="" class="form-horizontal" id="addQuestion" method="post">
						<legend>Ask a Question</legend>
						<div class="control-group string required">
							<label class="string required control-label" for="subject">
								<abbr title="required">*</abbr> Subject
							</label>
							<div class="controls">
								<input class="string required span12" id="subject" name="subject" type="text">
							</div>
						</div>
						<div class="control-group text required">
							<label class="text required control-label" for="content"> 
								<abbr title="required">*</abbr> Content
							</label>
							<div class="controls">
								<textarea class="text required span12" id="content" name="content" rows="15" ></textarea>
							</div>
						</div>
						<div class="control-group string required">
							<label class="string required control-label" for="tags">
								<abbr title="required">*</abbr> Tags
							</label>
							<div class="controls">
								<div id="taglist" class="taglist">
								    <span>
								    	<input type="text" id="tags" size="10" name="tags" value="" placeholder="Input Tag" />
								    </span>
								</div>
								<a href="#" id="addTag">Add Tag</a>
							</div>
						</div>
						<div class="form-actions">
							<input class="btn btn-primary" name="commit" type="submit" value="Post Question">
							<a class="btn btn-danger" href="<c:url value="/"/>">Cancel</a> 
						</div>
					</form>
				</div><!--/span-->
				<jsp:include page="../menu/rightMenu.jsp" />
			</div><!--/row-->
			<hr>
			<footer>
				<p>&copy; Satish Ab 2012</p>
			</footer>
		</div><!--/.fluid-container-->
		<jsp:include page="../menu/includeScripts.jsp" />
		<script>
			$(function() {
				$('#addTag').live( 'click',
					function() {
						$( '<span id="tag"><input type="text" id="tags" size="10" name="tags" value="" placeholder="Input Tag" />'
							 + '<a href="#" id="remTag" class="icon-remove"></a></span>' 
						 ).appendTo('#taglist');
						return false;
					});
	
				$('#remTag').live('click', function() {
					$(this).parents('span').remove();
					return false;
				});
			});
		</script>
</body>
</html>