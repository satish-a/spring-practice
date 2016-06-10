<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder"%>

<c:url value="/" var="homeUrl" />
<c:url value="user" var="userUrl" />
<c:url value="admin" var="adminUrl" />
<c:url value="logout" var="logoutUrl" />

<div class="span2">
	<div class="bs-docs-sidebar">
		<ul class="nav nav-list bs-docs-sidenav"
			style="font-weight: bold; font-size: 14px;">
			<li><a href="${homeUrl}"><i class="icon-chevron-right"></i> Questions</a></li>
			<li><a href="<c:url value="/tags/list" />"><i class="icon-chevron-right"></i> Tags</a></li>
			<li><a href="#navs"><i class="icon-chevron-right"></i> Unanswered</a></li>
			<li><a href="#buttonGroups"><i class="icon-chevron-right"></i> Most Viewed</a></li>
			<li><a href="#buttonDropdowns"><i class="icon-chevron-right"></i> Most Answered</a></li>
			<li><a href="#buttonDropdowns"><i class="icon-chevron-right"></i> Most Voted</a></li>
			<li><a href="#navbar"><i class="icon-chevron-right"></i> Most Favorite</a></li>
		</ul>
	</div>
</div> <!--/span-->