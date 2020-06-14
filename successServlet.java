package web;

import dao.success;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/successServlet")
public class successServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String gname = request.getParameter("gname");
        String uname = (String) session.getAttribute("user1");
        String number = (String) session.getAttribute("user2");
        String gamount = request.getParameter("gamount");
        String  zprice= request.getParameter("zprice");
        Date date = new Date();
        String ordertime= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
        System.out.println(gname);
        System.out.println(uname);
        System.out.println(gamount);
        System.out.println(zprice);
        System.out.println(number);
        System.out.println(ordertime);
        success success = new success();
        success.addorder(uname,gname,gamount,zprice,ordertime,number);
        response.sendRedirect(request.getContextPath()+"/bytimeServlet");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
             doPost(request,response);
    }
}
