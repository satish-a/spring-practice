<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page
	import="org.springframework.security.core.context.SecurityContextHolder"%>

<c:url value="/" var="homeUrl" />
<c:url value="user" var="userUrl" />
<c:url value="admin" var="adminUrl" />
<c:url value="logout" var="logoutUrl" />

<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="brand span2" href="<c:url value="/"/>">Q &amp; A</a>

			<form class="navbar-search">
				<input type="text" class="search-query span6" placeholder="Search Question">
			</form>

			<a class="btn btn-danger span2" style="color: #fff; font-weight: bold; font-size: 14px;" href="<c:url value="/question/add" />">Ask a Question</a>

			<div class="btn-group pull-right">
				<a class="btn btn-inverse dropdown-toggle" data-toggle="dropdown" href="#"> 
					<%=SecurityContextHolder.getContext().getAuthentication().getName()%>
					<span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href="#">Profile</a></li>
					<li><a href="#">Settings</a></li>
					<li><a href="<c:url value="/users"/>">List</a></li>
					<li><a href="<c:url value="/logout"/>">Logout</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>