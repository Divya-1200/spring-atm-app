<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<body>


<p>Register page</p>
<form action="register/form" method="post">
  <label for="name">Username:</label><br>
  <input type="text" id="name" name="name" ><br>
  <label for="password">Password</label><br>
  <input type="password" id="password" name="password"><br><br>
  <input type="submit" value="Register">
</form> 



</body>
</html>