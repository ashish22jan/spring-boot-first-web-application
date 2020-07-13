<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  
 
<title>Todo's for ${Username}</title>


	<marquee behaviour="right">
		<b><h1>Welcome to Google, ${Username}!!</h1></b>
	</marquee>

	<div class="container">
		<table class="table table-dark table-hover">
			<caption>Your todos are:</caption>
			<thead>
				<tr>
					<th>Description</th>
					<th>Date</th>
					<th>Status</th>
					<th>Update row</th>
					<th>Delete row</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${todos}" var="todo">

					<tr>
						<td>${todo.desc}</td>
						<td><fmt:formatDate value="${todo.targetDate}" pattern="dd/MM/yyyy"/></td>
						<td>${todo.done}</td>
						<td><a type="button" class="btn btn-success"
							href="/update-todo?id=${todo.id}">Update</a></td>
						<td><a type="button" class="btn btn-warning"
							href="/delete-todo?id=${todo.id}">DELETE</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>



		<h2>
			Your name is: <font color="Red">${Username}Ashish Ramtri</font>
			<div>
				<a class="button" href="/add-todo">Add a To-Do</a>
			</div>
	</div>
	
<%@ include file="common/footer.jspf" %>