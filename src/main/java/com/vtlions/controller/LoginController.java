package com.vtlions.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vtlions.utils.DBManager;
import com.vtlions.utils.Encoder;

public class LoginController extends HttpServlet {

	DBManager menager = new DBManager();

	private static final long serialVersionUID = 1200711808800119310L;
	public static final String USER_ACCESS_GRANTED = "user";

	RequestDispatcher dispatcher = null;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.getAttribute(USER_ACCESS_GRANTED);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String key = request.getParameter("key");

		if (key != null) {
			session.invalidate();

		}
		String url = "WEB-INF/views/loginform.jsp";
		session = request.getSession(true);

		if (session.getAttribute(USER_ACCESS_GRANTED) != null) {

			request.setAttribute("user", menager.getUser());
			url = "WEB-INF/views/loginsuccesspage.jsp";

		}
		dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		String login = request.getParameter("login");
		String password = request.getParameter("password");

		PrintWriter out = response.getWriter();

		if (menager.getFullNameByLoginAndPassword(login, Encoder.md5EncriptionWithSult(password))) {

			HttpSession session = request.getSession();
			session.setAttribute(USER_ACCESS_GRANTED, menager.getUser());
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/loginsuccesspage.jsp");
			dispatcher.forward(request, response);

			// out.write("<a href='/secr'>secret page</a>");

		} else {
			request.setAttribute("message", "Access denied");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/loginform.jsp");
			dispatcher.forward(request, response);
		}

	}

}
