package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBConnection;

public class Doctor {

	public Doctor() {

	}

	// Doctor Table
	public String readDoctors() {
		String out = "";
		Connection connection = DBConnection.getConnection();
		out = "<table border='1'><tr><th>First Name</th><th>Middle Name</th><th>Last Name</th><th>Contact</th><th>Status</th><th>Update</th><th>Remove</th></tr>";

		String query = "SELECT * FROM doctor";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String did = Integer.toString(resultSet.getInt("id"));
				String fname = resultSet.getString("firstname");
				String mname = resultSet.getString("middlename");
				String lname = resultSet.getString("lastname");
				String contact = resultSet.getString("contact");
				String status = resultSet.getString("status");

				out += "<tr>";

				out += "<td><input type='hidden' id='hiddendocIDUpdate' name='hiddendocIDUpdate' value='" + did
						+ "'/>"+fname+"</td>";
//				out += "<td>" + fname + "</td>";
				out += "<td>" + mname + "</td>";
				out += "<td>" + lname + "</td>";
				out += "<td>" + contact + "</td>";
				out += "<td>" + status + "</td>";

				out += "<td><input id='btnUpdate' name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"

						+ "<td><input id='btnRemove' name='btnRemove' type='button' value='Remove' class='btn btn-danger' data-docid='"
						+ did + "'>";
				
				out += "</td>";
				out += "</tr>";
				
			}
			

			out += "</table>";
			connection.close();
		} catch (Exception e) {
			out = "Reading Interrupted by Error! ";
			System.err.println(e.getMessage());
		}
		
		return out;
	}
	
	public String insertDoctors(String fName,String mName,String lName, String number, String status ) {
		String out="";
		Connection connection = DBConnection.getConnection();
		String query = "INSERT INTO `doctor`" + "(`firstname`,`middlename`,`lastname`,`contact`,`status`)"
				+ "values (?,?,?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, fName);
			preparedStatement.setString(2, mName);
			preparedStatement.setString(3, lName);
			preparedStatement.setString(4, number);
			preparedStatement.setString(5, status);

			preparedStatement.execute();
			connection.close();
			String newDoctors = readDoctors();
			out="{\"status\":\"success\", \"data\": \"" + newDoctors + "\"}";
		} catch (Exception e) {
			out="{\"status\":\"error\", \"data\": \"Error While Inserting The Doctor!\"}";
			System.err.println(e.getMessage());
		}
		return out;
	}
	
	public String updateDoctors(String did,String fName,String mName,String lName, String number, String status  ) {
		String out="";
		Connection connection = DBConnection.getConnection();
		String query = "UPDATE `doctor` SET"
				+ " `firstname` = ?, `middlename` = ?, `lastname` = ? , `contact` = ?, `status` = ?" + "WHERE id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, fName);
			preparedStatement.setString(2, mName);
			preparedStatement.setString(3, lName);
			preparedStatement.setString(4, number);
			preparedStatement.setString(5, status);

			preparedStatement.setInt(6, Integer.parseInt(did));
			preparedStatement.execute();
			connection.close();
			String updatedDoctors = readDoctors();
			out="{\"status\":\"success\", \"data\": \"" + updatedDoctors + "\"}";
		} catch (Exception e) {
			out="{\"status\":\"error\", \"data\": \"Error While Updating Doctor ID = "+did+"!\"}";
			System.err.println(e.getMessage());
		}
		return out;
		
	}
	
	public String deleteDoctor(String did) {
		String out="";
		Connection connection = DBConnection.getConnection();
	
		String query = "DELETE FROM doctor WHERE id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(did));
			preparedStatement.execute();
			connection.close();
			String remainDoctors = readDoctors();
			out="{\"status\":\"success\", \"data\": \"" + remainDoctors + "\"}";
		} catch (Exception e) {
			out="{\"status\":\"error\", \"data\": \"Error While Deleting Doctor ID = "+did+"!\"}";
			System.err.println(e.getMessage());
		}
		return out;
	}
}
