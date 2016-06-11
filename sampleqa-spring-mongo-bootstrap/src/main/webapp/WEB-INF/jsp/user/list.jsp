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
					<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="example">
                        <thead>
                            <tr>
                                <th width="20%">User Name</th>
                                <th width="25%">First Name</th>
                                <th width="25%">Last Name</th>
                                <th width="15%">State</th>
                                <th width="15%">Country</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td colspan="5" class="dataTables_empty">Loading data from server</td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <th>User Name</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>State</th>
                                <th>Country</th>
                            </tr>
                        </tfoot>
                    </table>
				</div><!--/span-->
				<jsp:include page="../menu/rightMenu.jsp" />
			</div><!--/row-->
			<hr>
			<footer>
				<p>&copy; Satish Ab 2012</p>
			</footer>
		</div><!--/.fluid-container-->
		<jsp:include page="../menu/includeScripts.jsp" />
		<script type="text/javascript" charset="utf-8">
            $(document).ready(function() {
                $('#example').dataTable( {
                    "bProcessing": true,
                    "bServerSide": true,
                    "sAjaxSource": "<c:url value="/users/list"/>",
                    "fnServerData": function ( sSource, aoData, fnCallback ) {
                        $.ajax( {
                            "dataType": 'json',
                            "type": "GET",
                            "url": sSource,
                            "data": aoData,
                            "success": fnCallback
                        } );
                    },
                    "aoColumns": [
                        { "mData": "userName" },
                        { "mData": "firstName" },
                        { "mData": "lastName" },
                        { "mData": "countryState" },
                        { "mData": "country" }
                        ]
                } );
            } );
        </script>
	</body>
</html>