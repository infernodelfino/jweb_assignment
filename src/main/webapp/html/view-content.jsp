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
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/view-content.css">
    <title>View content</title>
</head>

<body>

    <div class="container-fluid banner">
        <div class="row banner shadow rounded" id="banner">
            <div class="col-10 banner pt-2">
                CMS
            </div>
            <div class="col-2 banner">
                <div class="btn-group float-right">
                <p class="pt-2 pr-2">Hi, ${email}</p>
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
            <div class="col-10 pt-5 pl-5 pr-5">
                <h1>
                    View content
                </h1>
                <hr>
                <div class="card">
                    <div class="card-header">
                        View content list
                    </div>
                    <div class="pl-4 pr-4 pt-3 pb-5">
                        <table id="view-content-table" class="table table-striped table-bordered">
                            <tr class="d-flex">
                                <th class="col-1">#</th>
                                <th class="col-2">Title</th>
                                <th class="col-5">Brief</th>
                                <th class="col-2">Created Date</th>
                                <th class="col-1 text-center">Edit</th>
                                <th class="col-1 text-center">Delete</th>
	                        </tr>
                            <c:forEach items="${contentList}" var="content">
                            	<tr class="d-flex">
	                                <td class="col-1">${content.id}</td>
	                                <td class="col-2">${content.title }</td>
	                                <td class="col-5">${content.brief }</td>
	                                <td class="col-2">${content.createDate }</td>
	                                <td class="col-1 text-center"><a href="<%=request.getContextPath() %>/content?action=editOrCreate&id=${content.id}" id="${content.id}" class="text-muted"><i class="fa fa-eraser text-muted" aria-hidden="true"></i></a></td>
	                                <td class="col-1 text-center"><a href="<%=request.getContextPath() %>/content?action=delete&id=${content.id}" id="${content.id}" class="text-muted"><i class="fa fa-trash" aria-hidden="true"></i></a></td>
	                            </tr>
                            </c:forEach>
                        </table>
                        <div class="text-center">
                        	<c:forEach begin="1" end="${numberOfPage}" var="pageNumber">
                        		<a href="<%=request.getContextPath()%>/content?action=view&page=${pageNumber}">${pageNumber}</a>
                        	</c:forEach>
                        </div>
                    </div>
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