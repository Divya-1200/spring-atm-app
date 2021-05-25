<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<body>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<style>
input{
	margin : 0 auto;
}
</style>





<div class="container text-center pt-4">
<h2>Register page</h2>
<form action="register/form" method="post">
	<div class="form-group pt-4">
    	<label for="exampleFormControlInput1">Email</label>
    	<input type="email" class="form-control" name="name" id="exampleFormControlInput1" required="required" autocomplete="off" style="width: 300px;">
   </div>
   <div class="form-group">
    	<label for="exampleFormControlInput1">Password</label>
    	<input type="password" class="form-control" name="password" id="exampleFormControlInput1" required="required" style="width: 300px;">
   </div>
   <button type="submit" class="btn btn-primary">Register</button>

  
</form> 

</div>



</body>
</html>