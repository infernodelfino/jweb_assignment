<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- font awesome -->
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>

    <!-- bootstrap4 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <!-- css -->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/register.css">
    <title>Register page</title>
</head>
<body>
    <div class="container-fluid">
        <div class="row justify-content-center">
            <form method="post" action="<%= request.getContextPath()%>/register" id="login-form">
                <div class="card" style="width: 23rem;">
                    <div class="card-header">
                        Register
                    </div>
                    <div class="col-12 mt-3">
                    	<p class="text-danger">${message}</p>
                        <div class="form-group">
                            <input type="text" class="form-control" name="username" placeholder="Username" id="input-username">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" name="email" placeholder="Email" id="input-email">
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" name="password" placeholder="Password" id="input-password">
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" name="re-password" placeholder="re-Password" id="input-re-password">
                        </div>
                        <div class="form-group">
                            <button class="btn btn-success btn-block mb-2" type="submit">Register</button>
                            <a href="<%=request.getContextPath()%>/html/login.jsp" >Click here to login</a>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <script src=""></script>

    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

    <!-- popper.js jQuery -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>

    <!-- boostrap.min.js -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <!-- jQuery validator -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>
</body>
</html>