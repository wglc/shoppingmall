package web;

import dao.OrderDaoimpl;
import domain.Consumption;
import service.OrderServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/myorderServlet")
public class myorderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String uname = (String) session.getAttribute("user1");
        OrderDaoimpl orderDaoimpl = new OrderDaoimpl();
        List<Consumption> myorder = orderDaoimpl.myorder(uname);
        request.setAttribute("myorder",myorder);
        request.getRequestDispatcher("/my.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                  doPost(request,response);
    }
}
