package web;

import service.OrderService;
import service.OrderServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delSelectedorderServlet")
public class delSelectedorderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String[] orderid = request.getParameterValues("orderid");
        OrderService service = new OrderServiceimpl();
        service.delseclectgoods(orderid);
        response.sendRedirect(request.getContextPath()+"/orderlistServlet");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           doPost(request,response);
    }
}
