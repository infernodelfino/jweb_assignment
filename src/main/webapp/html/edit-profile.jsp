<%@ page isELIgnored="false" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/edit-profile.css">
    <title>Edit profile</title>
</head>

<body>
    <div class="container-fluid banner">
        <div class="row banner shadow rounded" id="banner">
            <div class="col-10 banner pt-2">
                CMS
            </div>
            <div class="col-2 banner">
                <div class="btn-group float-right">
                  	<p class="pt-2 pr-2">Hi, ${member.email}</p>
                    <button type="button" class="btn dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
                        aria-expanded="false">
                        <i class="fa fa-user" aria-hidden="true"></i>
                    </button>
                    <div class="dropdown-menu dropdown-menu-right">
                        <a href="<%=request.getContextPath() %>/edit-profile" class="dropdown-item">User Profile</a>
                        <div class="dropdown-divider"></div>
                        <a href="<%=request.getContextPath() %>/logout" class="dropdown-item">Logout</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container-fluid web-body">
        <div class="row web-body border-top">
            <div class="col-2 web-body side-bar shadow rounded">
                <ul class="list-group list-group-flush">
                    <div class="input-group mt-2 mb-2">
                        <input type="text" class="form-control" id="search-bar" placeholder="Seach...">
                        <div class="input-group-append">
                            <div class="input-group-text"><i class="fa fa-search" id="search-icon"
                                    aria-hidden="true"></i></div>
                        </div>
                    </div>
                    <a href="<%=request.getContextPath() %>/content?action=view&page=1" class="list-group-item">View contents</a>
                    <a href="<%=request.getContextPath() %>/content?action=editOrCreate" class="list-group-item">Form contents</a>
                </ul>
            </div>
            <div class="col-10 mt-5 pl-5 pr-5">
                <h1>
                    Edit profile
                </h1>
                <h5 class="text-${message == 'Successfully updated'? 'success' : 'danger' }">${message}</h5>
                <hr>
                <div class="card">
                    <div class="card-header">
                        Profile form elements
                    </div>
                    <form method="post" action="<%=request.getContextPath() %>/edit-profile" id="edit-profile-form" class="pl-4 pr-4 pt-3 pb-5">
						<input type="hidden" name="id" id="id" value="${member.id}">
                        <div class="form-group">
                            <label for="first-name">First name</label>
                            <input type="text" class="form-control col-7" name="firstName" id="first-name" value="${member.firstName}">
                        </div>
                        <div class="form-group">
                            <label for="last-name">Last name</label>
                            <input type="text" class="form-control col-7" name="lastName" id="last-name" value="${member.lastName}">
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="text" class="form-control-plaintext pl-0 col-7" id="email" value="${member.email}" disabled>
                        </div>
                        <div class="form-group">
                            <label for="phone">Phone</label>
                            <input type="text" class="form-control col-7" name="phone" id="phone" value="${member.phone}">
                        </div>
                        <div class="form-group">
                            <label for="description">Description</label>
                            <textarea class="form-control col-7" name="description" id="description" cols="30" rows="5">${member.description}</textarea>
                        </div>

                        <button type="submit" class="btn btn-outline-dark">Submit</button>
                        <button type="reset" class="btn btn-outline-dark">Reset</button>
                    </form>
                </div>
            </div>
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