package tw.com.eeit.petforum.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.com.eeit.petforum.model.bean.Member;
import tw.com.eeit.petforum.service.MemberService;
import tw.com.eeit.petforum.util.PathConverter;

@WebServlet("/Login.do")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		MemberService memberService = new MemberService();
		Member m = memberService.login(email, password);

		// 登入失敗
		if (m == null) {
			request.setAttribute("message", "登入失敗");
			request.getRequestDispatcher("login").forward(request, response);
		}

		// 登入成功
		if (m != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loggedInMember", m);
			response.sendRedirect("index");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
