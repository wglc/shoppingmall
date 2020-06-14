package web;

import domain.Consumption;
import service.OrderService;
import service.OrderServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/orderlistServlet")
public class orderlistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        OrderService service = new OrderServiceimpl();
        List<Consumption> consumption =service.findallorder();
        request.setAttribute("consumption", consumption);
        request.getRequestDispatcher("orderlist.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                 doPost(request,response);
    }
}
