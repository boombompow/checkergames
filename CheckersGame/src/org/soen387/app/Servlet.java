package org.soen387.app;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.soen387.domain.model.user.User;
import org.soen387.domain.user.mapper.UserMapper;
import org.soen387.domain.user.tdg.UserTDG;




/**
 * Servlet implementation class Servlet
 */
@WebServlet("/")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			if(UserMapper.getOBJECT().findUser(request.getParameter("username"), request.getParameter("password")) != null) {
				request.getRequestDispatcher("/WEB-INF/jsp/logged_in.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Find if user exists
		request.getRequestDispatcher("/WEB-INF/jsp/logged_in.jsp").forward(request, response);
	}

}
