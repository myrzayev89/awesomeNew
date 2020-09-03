<%@ page import="com.myrzayev.daoInter.UserDaoInter" %>
<%@ page import="com.myrzayev.main.Context" %>
<%@ page import="java.util.List" %>
<%@ page import="com.myrzayev.entity.User" %>
<%@ page import="com.myrzayev.resume.controller.UserDetailController" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>Hello, world!</title>
</head>
<body>
<%
    User user = (User) request.getAttribute("user");
%>
<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-header">
                    <h3><%=user.getName()%>
                    </h3>
                </div>
                <div class="card-body">
                    <form action="userdetail" method="POST">
                        <input type="hidden" name="id" value="<%=user.getId()%>">
                        <input type="hidden" name="action" value="update">
                        <div class="form-group">
                            <label for="name">Adi</label>
                            <input type="text" name="name" value="<%=user.getName()%>" class="form-control" id="name">
                        </div>
                        <div class="form-group">
                            <label for="surname">Soyadi</label>
                            <input type="text" name="surname" value="<%=user.getSurname()%>" class="form-control" id="surname">
                        </div>
                        <button type="submit" class="btn btn-outline-primary">Redakte et</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/6f929fe1c1.js"></script>
</body>
</html>
