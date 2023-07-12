<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : bookreturn
    Created on : Mar 8, 2023, 10:07:57 PM
    Author     : DUNG HOANG PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book return</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap-theme.min.css" integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ" crossorigin="anonymous">

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="css/bookreturn.css">
    </head>
    <body>
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="BookList">Book Store</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <c:choose>
                            <c:when test="${empty sessionScope.user}">
                            </c:when>
                            <c:otherwise>
                                <li><a href='BookReturn'>Return book</a></li>
                                <li><a href='MyOrder'>My Order</a></li>
                                <li class="cart__link-container">
                                    <a href="ViewCart">Cart</a>
                                    <div class="cart__link-counter">${sessionScope.cart.getTotalBooks()==null?0:sessionScope.cart.getTotalBooks()}</div>
                                </li>
                                <li><a href="ManageBook">Manage Book</a></li>
                                </c:otherwise>
                            </c:choose>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <c:choose>
                            <c:when test="${empty sessionScope.user}">
                                <li><a href="login">Login</a></li>
                                </c:when>
                                <c:otherwise>
                                <li><a href="logout">Logout</a></li>
                                <li><a href='EditProfile'>Account</a></li>
                                </c:otherwise>
                            </c:choose>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
        <div style="min-height: 100vh" class="container">
            <form action="BookReturn" method="post" class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="-book_id">Book ID:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="-book_id" required="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="borrowing_id">Borrowing ID:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="borrowing_id" required="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="quantity">Quantity:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="quantity" required="">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Return this Book</button>
                    </div>
                </div>
                <p style="color: red">${mess}</p>
            </form>
        </div>
        <footer class="footer-bs">
            <div class="row">
                <div class="col-md-3 footer-brand animated fadeInLeft">
                    <h2>Book Store</h2>
                    <p>Created by Hoang Dung, Duong Viet Hoang, Ngo Minh Vu and Nguyen Duc Minh.</p>
                    <hr><!-- comment -->
                    <p>© 2023 Book Store, All rights reserved</p>
                </div>
                <div class="col-md-4 footer-nav animated fadeInUp">
                    <h4>SHOP BY CATEGORY</h4>
                    <div class="col-md-6">
                        <ul class="pages">
                            <li><a href="#">Books</a></li>
                            <li><a href="#">Fiction</a></li>
                            <li><a href="#">Nonfiction</a></li>
                            <li><a href="#">Kids</a></li>
                            <li><a href="#">eBooks</a></li>
                        </ul>
                    </div>
                    <div class="col-md-6">
                        <ul class="list">
                            <li><a href="#">About Us</a></li>
                            <li><a href="#">Contacts</a></li>
                            <li><a href="#">Terms & Condition</a></li>
                            <li><a href="#">Privacy Policy</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-2 footer-social animated fadeInDown">
                    <h4>Follow Us</h4>
                    <ul>
                        <li><a href="#">Facebook</a></li>
                        <li><a href="#">Twitter</a></li>
                        <li><a href="#">Instagram</a></li>
                    </ul>
                </div>
                <div class="col-md-3 footer-ns animated fadeInRight">
                    <h4>SIGN UP FOR SAVINGS, NEWS, AND UPDATES</h4>
                    <p>Submit your email address to receive Book Store offers & updates. Unsubscribe from our emails at any time.</p>
                    <br>
                    <p>
                    <div class="input-group">
                        <input type="email" class="form-control" placeholder="Your Email">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-envelope"></span></button>
                        </span>
                    </div><!-- /input-group -->
                    </p>
                </div>
            </div>
        </footer>
    </body>
</html>
