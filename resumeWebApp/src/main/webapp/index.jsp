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
            <form action="adduser" method="GET">
                <div class="row">
                    <div class="col-lg-4">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Name" name="name">
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Surname" name="surname">
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <button type="submit" class="btn btn-outline-primary">Search</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-lg-6">
            <form action="adduser" method="POST">
                <div class="row">
                    <div class="col-lg-4">
                        <div class="form-group">
                            <input type="text" name="name" class="form-control" placeholder="Name">
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="form-group">
                            <input type="text" name="surname" class="form-control" placeholder="Surname">
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
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Nationality</th>
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
                        <button type="submit" data-toggle="modal" data-target="#modalDelete"
                                class="getId btn btn-outline-danger" data-id="<%=u.getId()%>">
                            <i class="fas fa-trash-alt"></i>
                        </button>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="modalDelete" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Are you sure ?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <form action="userdetail" method="POST">
                    <input type="hidden" name="id" value="" class="setId">
                    <input type="hidden" name="action" value="delete">
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/6f929fe1c1.js"></script>
<script>
    $(document).ready(function (){
        $(".getId").on("click", function () {
            var id = $(this).data("id");
            $("#modalDelete").find(".setId").val(id);
        });
    });
</script>
</body>
</html>