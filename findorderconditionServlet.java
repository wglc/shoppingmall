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
import java.util.Map;

@WebServlet("/findorderconditionServlet")
public class findorderconditionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> condition = request.getParameterMap();
        OrderService service = new OrderServiceimpl();
        List<Consumption> consumption=service.findorderbycondition(condition);
        request.setAttribute("consumption",consumption);
        //转发到页面
        request.getRequestDispatcher("/orderlist.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
