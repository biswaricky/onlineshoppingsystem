<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
		  	<style>
					table, tr, td{
						 border: 2px solid black;
						 width : 30%;
						 text-align: center;
					}
				</style>
</head>
<body >
   <h2>CUSTOMERS LIST</h2>
			<c:forEach var="Customer" items="${list }">
						<table>
							<tr>
								<td>
									<h5><c:out value="${Customer.getCustomerid()}"></c:out></h5>
								</td>
								<td>
									<h5><c:out value="${Customer.getCustomername()}"></c:out></h5>
								</td>
								<td>
									<h5><c:out value="${Customer.getPassword()}"></c:out></h5>
								</td>	
								<td>
									<h5><c:out value="${Customer.getBalance()}"></c:out></h5>
								</td>	
							</tr>
						</table>
	     </c:forEach>
	
		
</body>
</html>


