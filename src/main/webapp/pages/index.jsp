<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<title>Report Application</title>
</head>
<body>

	<div class="container mt-5">
		<h2 class="text-center ">Report Application</h2>

		<form:form
			class="mt-5 pb-3 d-flex flex-column justify-content-center align-items-center"
			action="/search" modelAttribute="search" method="POST">


			<table>

				<tr>
					<td class="mx-4 pb-4">Plan Name:</td>
					<td class="mx-4 pb-4"><form:select class="mx-3" path="planName">
							<form:option value="">-Select-</form:option>
							<form:options items="${planName }" />
						</form:select></td>
					<td class="mx-4 pb-4">Plan Status:</td>
					<td class="mx-4 pb-4"><form:select class="mx-3 " path="planStatus">
							<form:option value="">-Select-</form:option>
							<form:options items="${planStatus }" />
						</form:select></td>
					<td class="mx-4 pb-4">Gender:</td>
					<td class="mx-4 pb-4"><form:select class="mx-3 " path="gender">
							<form:option value="">-Select-</form:option>
							<form:option value="Male">Male</form:option>
							<form:option value="Fe-Male">Fe-Male</form:option>
						</form:select></td>

				</tr>

				<tr>
					<td class="mx-4 pb-4">Start Date:</td>
					<td class="mx-4 pb-4"><form:input type="date" path="startDate" /></td>
					<td class="mx-4 pb-4">End Date:</td>
					<td class="mx-4 pb-4"><form:input type="date" path="endDate" /></td>
				</tr>


				<tr>
					<td class="mt-5 pb-3"><input type="submit" value="search"
						class="btn btn-primary" /></td>
						
						<td class="btn btn-danger text-white mx-5"><a class="text-white mx-2" href="/">Reset</a></td>
				</tr>

			</table>



		</form:form>

		<hr />

		<table class="table table-striped table-hover" >
			<thead>
				<tr>
					<th>S.No</th>
					<th>Holder Name</th>
					<th>Gender</th>
					<th>Plan Name</th>
					<th>Plan Status</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Benefit Amt</th>


				</tr>


			</thead>

			<tbody>
			  <c:forEach items="${lists}" var="plan" varStatus="index">
			  
			  			<tr>
			  			<td>${index.count }</td>
			  			<td>${plan.citizenName }</td>
			  			<td>${plan.gender }</td>
			  			<td>${plan.planName }</td>
			  			<td>${plan.planStatus }</td>
			  			<td>${plan.planStartDate }</td>
			  			<td>${plan.planEndDate }</td>
			  			<td>${plan.benefitAmt }</td>
			  			</tr>
			   </c:forEach>
			<tr>
			
			<c:if test="${empty lists}">
			
			<td colspan="8" class="text-center text-white bg-danger fw-bold"> No Record Found!</td>
			</c:if>
			
			</tr>
			
			
			</tbody>


		</table>
		<hr />
		<div class="text-center my-5 fw-bold"">

			Export: <a class="mx-5 " href="excel">Excel</a> <a href="pdf">Pdf</a>
		</div>


	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
</html>