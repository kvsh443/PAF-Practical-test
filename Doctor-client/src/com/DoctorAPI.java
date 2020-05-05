package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Doctor;

/**
 * Servlet implementation class DoctorAPI
 */
@WebServlet("/DoctorAPI")
public class DoctorAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Doctor doctor = new Doctor();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoctorAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("rawtypes")
	private static Map getParamtersToMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();

		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();

			String[] params = queryString.split("&");

			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
			System.out.print("HASHMAP ERROR:-");
			System.out.println(e.getMessage());
		}

		return map;

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fName = request.getParameter("DocNameF");
		String mName = request.getParameter("DocNameM");
		String lName = request.getParameter("DocNameL");
		String number = request.getParameter("DocNumber");
		String status = request.getParameter("DocStatus");
		String out = doctor.insertDoctors(fName, mName, lName, number, status);
//		System.out.println(" IO:_ "+out);
		response.getWriter().write(out);
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		@SuppressWarnings("rawtypes")
		Map parameter = getParamtersToMap(request);

		String did = parameter.get("DocIdSave").toString();
		String fName = parameter.get("DocNameF").toString();
		String mName = parameter.get("DocNameM").toString();
		String lName = parameter.get("DocNameL").toString();
		String number = parameter.get("DocNumber").toString();
		String status = parameter.get("DocStatus").toString();
		String out = doctor.updateDoctors(did, fName, mName, lName, number, status);

		parameter.clear();
		response.getWriter().write(out);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		@SuppressWarnings("rawtypes")
		Map parameter = getParamtersToMap(request);

		String did = parameter.get("doc_id").toString();
		String out = doctor.deleteDoctor(did);
		
		parameter.clear();
		response.getWriter().write(out);
	}

}
