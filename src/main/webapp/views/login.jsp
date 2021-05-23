<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<body>


<p>Login page</p>
<form action="login/form" method="post">
  <label for="fname">Username:</label><br>
  <input type="text" id="fname" name="fname" ><br>
  <label for="lname">Password</label><br>
  <input type="password" id="lname" name="lname"><br><br>
  <input type="submit" value="Login">
</form> 



</body>
</html>
