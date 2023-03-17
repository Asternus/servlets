package forward;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/lesson52/welcome-page")
public class WelcomePage extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();

        String login = request.getParameter("login");
        printWriter.print("Welcome " + login + "!");
        printWriter.print("<br>Login Success!!!");
    }

}
