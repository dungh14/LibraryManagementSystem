<%-- 
    Document   : login
    Created on : Mar 5, 2023, 11:07:27 PM
    Author     : DUNG HOANG PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">

<head>
	<meta charset="utf-8">
	<title>Log in</title>

	<!-- Google Fonts -->
	<link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700|Lato:400,100,300,700,900' rel='stylesheet' type='text/css'>

	<!-- Custom Stylesheet -->
	<link rel="stylesheet" href="css/login.css">

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
</head>

<body>
	<div class="container">
		<div class="top">
			<h1 id="title" class="hidden"><span id="logo">Book <span>Store</span></span></h1>
		</div>
		<div class="login-box">
			<div class="box-header">
				<h2>Log In</h2>
			</div>
                    <form method="post" action="login">
			<label for="email">Email</label>
			<br/>
			<input type="email" name="email" value="${param["email"]}" required="">
			<br/>
			<label for="password">Password</label>
			<br/>
			<input type="password" name="pass" value="${param["pass"]}" required="">
			<br/>
			<button type="submit">Sign In</button>
			<br/>
                        <p style="color: red">${mess}</p>
                        <a href="register">Create an account</a>
                    </form> 
		</div>
	</div>
</body>
