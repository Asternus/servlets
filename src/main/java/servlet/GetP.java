package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/lesson52/parameter", initParams = @WebInitParam(name = "name", value = "Guest"))
public class GetP extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String name = req.getParameter("name");

        if (name == null) {
            name = getInitParameter("name");
        }

        PrintWriter writer = resp.getWriter();
        writer.println("<h2>Hello " + name + "</h2>");

        writer.close();
    }
}
