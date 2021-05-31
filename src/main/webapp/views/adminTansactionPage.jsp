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




<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@100&display=swap" rel="stylesheet">

<style>
body {
    background-color: #FFEBEE;
    font-family: 'Noto Sans JP', sans-serif;
}

.card {
    width: 400px;
    background-color: #fff;
    border: none;
    border-radius: 12px
}


.form-control {
    margin-top: 10px;
    height: 48px;
    border: 2px solid #eee;
    border-radius: 10px
}

.form-control:focus {
    box-shadow: none;
    border: 2px solid #039BE5
}


.confirm-button {
    height: 50px;
    border-radius: 10px
}
</style>
<div class="container mt-5 mb-5 d-flex justify-content-center">
	
	
    <div class="card px-1 py-4">
        <div class="card-body">
            <h2>Transactions</h2>
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                    <a href="/admin/deposit">
                    <button type="submit" class="btn btn-primary btn-block confirm-button">Deposit</button>
                    </a>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                    <a href="/admin/withdrawal">
                    <button type="submit" class="btn btn-primary btn-block confirm-button">Withdraw</button>
                    </a>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                    <a href="/admin/check/balance">
                    <button type="submit" class="btn btn-primary btn-block confirm-button">Balance enquiry</button>
                    </a>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                    <a href="/admin/logout">
                    <button type="submit" class="btn btn-danger btn-block confirm-button">Logout</button>
                    </a>
                    </div>
                </div>
            </div>

            
            
            
        </div>
        
        
    </div>
    
</div>

            
      
 </div>
    
</div>





</body>
</html>