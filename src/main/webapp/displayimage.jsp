<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Display Image</title>
</head>
<body>

	<h1 align="center"> Display Image Detail </h1>
	
	<div align="center">
	<form action="DisplayServlet" method="post">
		Enter image id:
		<input type="number" name="imageId">
		<input type="submit" value="Display Image">
		
	</form >
	</div>
	
	<hr>
	
	
	<%
	String imgFileName = (String) request.getAttribute("img");
	String imgId = (String) request.getAttribute("id");
	//System.out.println(imgId);
	//System.out.println(imgFileName);
	
	
	%>
	
	<div align="center">
		<table border="2px" width="400px">
			<tr>
				<th>Image Id</th>
				<th>Image</th>
			</tr>
<%
if (imgFileName != null && !imgFileName.isEmpty() && imgId != null && !imgId.isEmpty()) {
%>
			
			<tr>
			<td><%= imgId %></td>
			<td><img width="100px" height="100px" src="images/<%= imgFileName %>"></td>		
			</tr>
		<%
		
		}
		
		%>
		
		
		
		</table>
	
	
	</div>
	
	
	
	
</body>
</html>