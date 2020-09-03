<%@ page import="java.util.List" %>
<%@ page import="com.myrzayev.entity.User" %>
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
    List<User> list = (List<User>) request.getAttribute("usersList");
%>
<div class="container mt-4">
    <div class="row">
        <div class="col-lg-6">
            <form action="users" method="GET">
                <div class="row">
                    <div class="col-lg-4">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Adi" name="name">
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Soyadi" name="surname">
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <button type="submit" class="btn btn-outline-primary">Axtar...</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-lg-6">
            <form action="users" method="POST">
                <div class="row">
                    <div class="col-lg-4">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Adi" name="name">
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Soyadi" name="surname">
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <button type="submit" class="btn btn-outline-success">Add</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-lg-12">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Adi</th>
                    <th>Soyadi</th>
                    <th>Milleti</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <% for (User u : list) { %>
                <tr>
                    <td><%=u.getName()%></td>
                    <td><%=u.getSurname()%></td>
                    <td><%=u.getNationality().getName() == null ? "N/A" : u.getNationality().getName()%></td>
                    <td style="width: 3px;">
                        <form action="userdetail" method="GET">
                            <input type="hidden" name="id" value="<%=u.getId()%>">
                            <input type="hidden" name="action" value="update">
                            <button type="submit" class="btn btn-outline-primary">
                                <i class="fas fa-pen"></i>
                            </button>
                        </form>
                    </td>
                    <td style="width: 3px;">
                        <form action="userdetail" method="POST">
                            <input type="hidden" name="id" value="<%=u.getId()%>">
                            <input type="hidden" name="action" value="delete">
                            <button type="submit" class="btn btn-outline-danger">
                                <i class="fas fa-trash-alt"></i>
                            </button>
                        </form>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/6f929fe1c1.js"></script>
</body>
</html>