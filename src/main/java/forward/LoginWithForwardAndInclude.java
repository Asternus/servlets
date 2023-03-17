package forward;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/lesson52/login-forward")
public class LoginWithForwardAndInclude extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter pwriter = response.getWriter();

        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        if (login.equals("admin") && pass.equals("admin")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/lesson52/welcome-page");
            dispatcher.forward(request, response);
        } else {
            pwriter.print("User name or password is incorrect!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/lesson52/login-forward.html");
            dispatcher.include(request, response);
        }
    }
}
