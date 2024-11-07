<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container-fluid">
        <nav class="navbar navbar-light bg-light mb-4">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">My App</a>
            </div>
        </nav>
        <div class="row mt-4">
            <div class="col-md-6 offset-md-3">
                <div class="card">
                    <div class="card-header">LOGIN</div>
                    <div class="card-body">
                        <form method="get" action="/login-form">
                            <% if (request.getAttribute("msg") != null) { %>
                                <div class="alert alert-danger">
                                    <%= request.getAttribute("msg") %>
                                </div>
                            <% } %>
                            <label>Enter username:</label>
                            <input type="text" name="username" class="form-control">
                            <label>Enter password:</label>
                            <input type="password" name="password" class="form-control">
                            <input type="submit" value="LOGIN" class="btn btn-primary mt-3">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
