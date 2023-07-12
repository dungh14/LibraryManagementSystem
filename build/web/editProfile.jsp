<%-- 
    Document   : editProfile
    Created on : Mar 6, 2023, 12:57:07 AM
    Author     : DUNG HOANG PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account</title>

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
                    <h2>Edit your Info</h2>
                </div>
                <form method="post" action="EditProfile">
                    <p><a href="BookList">Back to Home Page</a></p>
                    <label for="name">Name</label>
                    <br/>
                    <input type="text" name="name" value="${sessionScope.user.name}">
                    <br/>
                    <label for="gender">Gender</label>
                    <br/>
                    <input type="radio" name="gender" value="1" ${sessionScope.user.gender?"checked":""}>Male <input type="radio" name="gender" value="0" ${!sessionScope.user.gender?"checked":""}>Female
                    <br/>
                    <label for="email">Email</label>
                    <br/>
                    <input type="email" name="email"value="${sessionScope.user.email}">
                    <br/>
                    <label for="phone">Phone</label>
                    <br/>
                    <input type="text" name="phone" value="${sessionScope.user.phone}">
                    <br/>
                    <label for="address">Address</label>
                    <br/>
                    <input type="text" name="address" value="${sessionScope.user.address}">
                    <br/>
                    <label for="dob">DOB</label>
                    <br/>
                    <input type="date" name="dob" value="${sessionScope.user.dob}">
                    <br/>
                    <input type="hidden" name="id" value="${sessionScope.user.id}">
                    <br/>
                    <button type="submit">Confirm</button>
                    <br/>
                </form> 
            </div>
        </div>
    </body>
</html>
