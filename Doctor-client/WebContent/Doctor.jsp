<?xml version="1.0" encoding="utf-8" ?>
<%@page import="model.Doctor"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8" />

<title>Doctor Management</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<script type='text/javascript' src='./Components/Doctor.js'></script>
</head>

<body>

	<div class="container">
		<h1 class="text-center">Doctor Management</h1>
		<div class="row">
			<div class="col">
				<form id="formDoc" method="post" action="Doctor.jsp">
					<div class="form-group">
						Doctor First Name:<input class="form-control form-control-sm"
							id="DocNameF" name="DocNameF" type="text" required /> <br />
						Doctor Middle Name:<input class="form-control form-control-sm"
							id="DocNameM" name="DocNameM" type="text" /> <br />
						Doctor Last Name:<input class="form-control form-control-sm"
							id="DocNameL" name="DocNameL" type="text" required /> <br />
						Doctor Contact: <input class="form-control form-control-sm"
							id="DocNumber" name="DocNumber" type="text" required /> <br />
						Doctor Status: <input class="form-control form-control-sm"
							id="DocStatus" name="DocStatus" type="text" required /> <br />
						<input class="btn btn-primary" id="btnSave" name="btnSave"
							type="button" value="Save" /> <input id="DocIdSave"
							name="DocIdSave" type="hidden" />

					</div>
				</form>



				<div class="alert alert-success" id="alertSuccess"></div>
				<div class="alert alert-danger" id="alertError"></div>
				<br /> <br />
				<div id="divDocGrid">
					<%
						Doctor doc = new Doctor();
					out.print(doc.readDoctors());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>