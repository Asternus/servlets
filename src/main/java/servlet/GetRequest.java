package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/lesson52/request")
public class GetRequest extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        String title = "HTTP Request Example";

        String contentType = "<!DOCTYPE html>\n";

        writer.println(contentType + "<html>\n" +
                "<head><title>" + title + "</title></head>" +
                "<body>" +
                "<h1>HTTP Servlet Request </h1>"
        );

        writer.print("<h3>" + "Method: " + request.getMethod());
        writer.print("<h3>" + "Request URI: " + request.getRequestURI());
        writer.print("<h3>" + "Protocol: " + request.getProtocol());
        writer.print("<h3>" + "PathInfo: " + request.getPathInfo());
        writer.print("<h3>" + "Remote Address: " + request.getRemoteAddr());

        Enumeration headers = request.getHeaderNames();

        while (headers.hasMoreElements()) {
            String parameterName = (String) headers.nextElement();
            String parameterValue = request.getHeader(parameterName);
            writer.println("<h3>" + parameterName + ": " + parameterValue + "</h3>" + "\n");
        }

        writer.println("</body>");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
