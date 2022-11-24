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

public class RegistrationController extends HttpServlet {

	private static final long serialVersionUID = -6916850169887547603L;

	RequestDispatcher dispatcher = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		String login = request.getParameter("login");
		String fullName = request.getParameter("fullName");
		String region = request.getParameter("region");
		String gender = request.getParameter("gender");
		String comment = request.getParameter("comment");

		HttpSession session = request.getSession();

		String url = "WEB-INF/views/registrationform.jsp";

		if (session.getAttribute(LoginController.USER_ACCESS_GRANTED) != null) {
			url = "WEB-INF/views/registrationsuccesspage.jsp";
		} else {

			if (login != null) {
				request.setAttribute("rLogin", login);
			} else {
				request.setAttribute("rLogin", " ");
			}

			if (fullName != null) {
				request.setAttribute("rFullName", fullName);
			} else {
				request.setAttribute("rFullName", " ");
			}

			if (region != null) {
				if (region.equals("Lviv")) {
					request.setAttribute("rRegionLviv", "selected");
					request.setAttribute("rRegionKyiv", " ");
					request.setAttribute("rRegionKharkiv", " ");
				} else if (region.equals("Kyiv")) {
					request.setAttribute("rRegionLviv", " ");
					request.setAttribute("rRegionKyiv", "selected");
					request.setAttribute("rRegionKharkiv", " ");
				} else {
					request.setAttribute("rRegionLviv", " ");
					request.setAttribute("rRegionKyiv", " ");
					request.setAttribute("rRegionKharkiv", "selected");
				}
			} else {
				request.setAttribute("rRegionLviv", " ");
				request.setAttribute("rRegionKyiv", " ");
				request.setAttribute("rRegionKharkiv", "selected");
			}

			if (gender != null) {
				if (gender.equals("F")) {
					request.setAttribute("rGenderF", "checked");
					request.setAttribute("rGenderM", " ");
				} else {
					request.setAttribute("rGenderF", " ");
					request.setAttribute("rGenderM", "checked");
				}
			} else {
				request.setAttribute("rGenderF", "checked");
				request.setAttribute("rGenderM", " ");
			}

			if (comment != null) {
				request.setAttribute("rcomment", comment);
			} else {
				request.setAttribute("rcomment", " ");
			}

		}
		request.setAttribute("errMessage", " ");
		dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String rePassword = request.getParameter("rePassword");
		String fullName = request.getParameter("fullName");
		String region = request.getParameter("region");
		String gender = request.getParameter("gender");
		String comment = request.getParameter("comment");
		String agreement = request.getParameter("agreement");

		String url = "WEB-INF/views/registrationform.jsp";

		boolean isError = false;

		String message = "<ul>";
		if (login == null || login.isEmpty()) {
			isError = true;
			message += "<li>login is empty</li>";
		} else if (!login.matches(".*@.*\\.com")) {
			isError = true;
			message += "<li>incorect login</li>";
		}

		if (password == null || password.isEmpty()) {
			isError = true;
			message += "<li>password is empty</li>";
		} else {
			if (password.length() < 8) {
				isError = true;
				message += "<li>password is too short</li>";
			}
			if (!password.matches(".*[0-9].*")) {
				isError = true;
				message += "<li>password has to contain at least one digit</li>";
			}
			if (!password.matches(".*[A-Z].*")) {
				isError = true;
				message += "<li>password has to contain at least capital letter</li>";
			}
		}

		if (rePassword == null || rePassword.isEmpty()) {
			isError = true;
			message += "<li>rePassword is empty</li>";
		} else if (password != null && !password.isEmpty()) {
			if (!rePassword.equals(password)) {
				isError = true;
				message += "<li>rePassword and password are different</li>";
			}
		}

		if (fullName == null || fullName.isEmpty()) {
			isError = true;
			message += "<li>fullName is empty</li>";
		}
		if (gender == null || gender.isEmpty()) {
			isError = true;
			message += "<li>gender is not chosen</li>";
		}
		if (agreement == null || agreement.isEmpty()) {
			isError = true;
			message += "<li>agreement is not checked</li>";
		}

		message += "</ul>";

		if (!isError) {
			url = "WEB-INF/views/registrationsuccesspage.jsp";
			DBManager menager = new DBManager();
			menager.addUserToBD(login, Encoder.md5EncriptionWithSult(password), fullName, region, gender, comment);
		} else {

			if (login != null) {
				request.setAttribute("rLogin", login);
			} else {
				request.setAttribute("rLogin", " ");
			}

			if (fullName != null) {
				request.setAttribute("rFullName", fullName);
			} else {
				request.setAttribute("rFullName", " ");
			}

			if (region != null) {
				if (region.equals("Lviv")) {
					request.setAttribute("rRegionLviv", "selected");
					request.setAttribute("rRegionKyiv", " ");
					request.setAttribute("rRegionKharkiv", " ");
				} else if (region.equals("Kyiv")) {
					request.setAttribute("rRegionLviv", " ");
					request.setAttribute("rRegionKyiv", "selected");
					request.setAttribute("rRegionKharkiv", " ");
				} else {
					request.setAttribute("rRegionLviv", " ");
					request.setAttribute("rRegionKyiv", " ");
					request.setAttribute("rRegionKharkiv", "selected");
				}
			} else {
				request.setAttribute("rRegionLviv", " ");
				request.setAttribute("rRegionKyiv", " ");
				request.setAttribute("rRegionKharkiv", "selected");
			}

			if (gender != null) {
				if (gender.equals("F")) {
					request.setAttribute("rGenderF", "checked");
					request.setAttribute("rGenderM", " ");
				} else {
					request.setAttribute("rGenderF", " ");
					request.setAttribute("rGenderM", "checked");
				}
			} else {
				request.setAttribute("rGenderF", "checked");
				request.setAttribute("rGenderM", " ");
			}

			if (comment != null) {
				request.setAttribute("rcomment", comment);
			} else {
				request.setAttribute("rcomment", " ");
			}
		}

		request.setAttribute("errMessage", message);
		dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

}
