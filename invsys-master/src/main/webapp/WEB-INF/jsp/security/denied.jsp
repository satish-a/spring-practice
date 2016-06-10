<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<html>
<jsp:include page="../menu/header.jsp" />
<body>

	<h1 id="banner">Unauthorized</h1>
	<hr/>
	
	<p class="message">Access denied!</p>
</body>

<jsp:include page="../menu/includescripts.jsp" />

</html>