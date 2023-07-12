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
        <title>Register</title>

        <!-- Google Fonts -->
        <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700|Lato:400,100,300,700,900' rel='stylesheet' type='text/css'>

        <!-- Custom Stylesheet -->
        <link rel="stylesheet" href="css/register.css">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    </head>

    <body>
        <div class="container">
            <div class="login-box">
                <div class="box-header">
                    <h2>Register</h2>
                </div>
                <form method="post" action="register">
                    <label for="name">Name</label>
                    <br/>
                    <input type="text" name="name">
                    <br/>
                    <label for="gender">Gender</label>
                    <br/>
                    <input type="radio" name="gender" value="1">Male <input type="radio" name="gender" value="0">Female 
                    <br/>
                    <label for="email">Email</label>
                    <br/>
                    <input type="email" name="email">
                    <br/>
                    <label for="password">Password</label>
                    <br/>
                    <input type="password" name="pass">
                    <br/>
                    <label for="repassword">Re-enter Password</label>
                    <br/>
                    <input type="password" name="repass">
                    <br/>
                    <label for="phone">Phone</label>
                    <br/>
                    <input type="text" name="phone">
                    <br/>
                    <label for="address">Address</label>
                    <br/>
                    <input type="text" name="address">
                    <br/>
                    <label for="dob">DOB</label>
                    <br/>
                    <input type="date" name="dob">
                    <br/>
                    <button type="submit">Sign Up</button>
                    <br/>
                    <p style="color: red">${mess}</p>
                    <p style="color: red">${mess1}</p>
                </form> 
            </div>
        </div>
    </body>