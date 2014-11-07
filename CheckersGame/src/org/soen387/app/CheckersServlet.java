package org.soen387.app;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dsrg.soenea.domain.MapperException;
import org.soen387.domain.challenge.mapper.ChallengeMapper;
import org.soen387.domain.checkerboard.mapper.CheckerBoardDataMapper;
import org.soen387.domain.model.challenge.IChallenge;
import org.soen387.domain.model.checkerboard.CheckerBoard;
import org.soen387.domain.model.player.Player;
import org.soen387.domain.model.user.User;
import org.soen387.domain.player.mapper.PlayerMapper;
import org.soen387.domain.user.mapper.UserMapper;


/**
 * Servlet implementation class Servlet
 */
public class CheckersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User user;
	private Player player;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
		//doLogin, doRegister, doChallenge, listGames, listPlayers
		
		String action = request.getRequestURI().substring(
				request.getRequestURI().lastIndexOf("/"));
		
		if(action.equals("/") || action.equals("/home") || action == null 
				|| action.isEmpty()) {
			// verify is user already logged in
			if(verifyUser(request)) {
				doProfilePage(request, response); // logged in
			} else {
				doLogin(request, response); // inform user to log in
			}
		} else if(action.equals("/register")) {
			request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
			//doRegister(request, response);
			
		} else if(action.equals("/logout")) {
			request.getSession().removeAttribute("user");
			request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
		
		} else if(action.equals("/profile")){
			//doHomePage(request, response);
			request.getRequestDispatcher("/WEB-INF/jsp/home_2.jsp").forward(request, response);
		} else if(action.equals("/makeCH")) {
			doChallenge(request, response);
		}
	}
	
	private void doChallenge(HttpServletRequest request,
			HttpServletResponse response) {
		ChallengeMapper.getOBJECT().fin
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getRequestURI().substring(
				request.getRequestURI().lastIndexOf("/"));
		
		if(action.equals("/doLogin")) { // click from login.jsp
			try {
				user = UserMapper.getOBJECT().findUser(request.getParameter("username"), request.getParameter("password"));
				if(user != null) {
					doProfilePage(request, response);
				} else {
					request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equals("/doRegister")) { // click from register.jsp
			doRegister(request, response);
		}
	}
	
	private void doProfilePage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {		
		try {
			player = PlayerMapper.getOBJECT().findById(user.getId());
			
			// Set user/player
			request.setAttribute("user", user);
			request.setAttribute("player", player);
			
			// Set games
			List<CheckerBoard> games = CheckerBoardDataMapper.findByPlayer(user.getId());
			request.setAttribute("games", games);
			
			// Set players and status
			List<Player> players = PlayerMapper.getOBJECT().findAll();
			request.setAttribute("players", players);
			
			List<IChallenge> challenges = ChallengeMapper.getOBJECT().findAllById(user.getId());
			request.setAttribute("challenges", challenges);
			
			
			// Set sessions
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setAttribute("player", player);
			
		} catch (MapperException | SQLException e) {
			e.printStackTrace();
		}
		// Set players and status
		
		request.getRequestDispatcher("/WEB-INF/jsp/logged_in.jsp").forward(request, response);
	}

	private void doRegister(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		long uid = 0; // user id 
		
		try {
			// New user/player id
			uid = UserMapper.getOBJECT().getId() + 1;
			
			// Create user
			User newuser = new User(uid, username, password, 1);
			UserMapper.getOBJECT().insert(newuser);
			
			// Create player
			Player newplayer = new Player(uid, firstname, lastname, email, 1);
			PlayerMapper.getOBJECT().insert(newplayer);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			// do automatic login
			doLogin(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * doLogin
	 * 
	 * Redirect to login page
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Find if user exists
		try {
			user = UserMapper.getOBJECT().findUser(request.getParameter("username"), request.getParameter("password"));
			if(user != null) {
				doProfilePage(request, response);
			} else {
				request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * verifUser
	 * 
	 * Verify whether user has logged in or not
	 * 
	 * @param request
	 * @return boolean
	 */
	private boolean verifyUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		user = (User) session.getAttribute("user");

		return (user != null) ? true : false;
	}

}
