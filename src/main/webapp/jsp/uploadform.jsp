<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h3 style="color:red">${filesuccess}</h3>  
	<form:form method="post" action="savefile" enctype="multipart/form-data">  
	<p><label for="image">Choose Image</label></p>  
	<p><input name="file" id="fileToUpload" type="file" /></p>  
	<p><input type="submit" value="Upload"></p>  
	</form:form>  

</body>
</html>