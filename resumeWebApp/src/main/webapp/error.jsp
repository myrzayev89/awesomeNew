<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Why you here ?</title>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="alert alert-danger">
                    <%=request.getParameter("msg")%>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
