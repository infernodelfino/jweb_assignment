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
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/login.css">
    <title>Login page</title>
</head>

<body>

    <div class="container-fluid">
        <div class="row justify-content-center">
            <form method="post" action="<%=request.getContextPath() %>/login" id="login-form" onsubmit="return validateForm()">
                <div class="card" style="width: 23rem;">
                    <div class="card-header">
                        Please sign in
                    </div>
                    <div class="col-12 mt-3">
                    	<p class="text-danger">${message}</p>
                        <div class="form-group">
                            <input type="text" id="input-email" name="email" class="form-control" placeholder="Please enter your email" onchange="emailOnChange()">
                        </div>
                        <div class="form-group">
                            <input type="password" id="input-password" name="password" class="form-control" placeholder="Please enter your password">
                        </div>
                        <div class="form-group form-check">
                            <input class="form-check-input" type="checkbox" id="checkbox-remember">
                            <label class="form-check-label" for="remember-me-checkbox">Remember me</label>
                        </div>
                        <div class="form-group">
                            <input class="btn btn-success btn-block mb-2" id="btn-login" type="submit">Login</button>
                            <a href="<%=request.getContextPath()%>/html/register.jsp">Click here to register</a>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <script src="../js/login.js"></script>

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