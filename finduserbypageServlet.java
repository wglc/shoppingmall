package web;

import domain.PageBean;
import domain.User;
import service.UserService;
import service.UserServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/finduserbypageServlet")
public class finduserbypageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");
        if(currentPage==null||"".equals(currentPage)){
            currentPage="1";
        }
        if(rows==null||"".equals(rows)){
            rows="5";
        }
        Map<String, String[]> condition = request.getParameterMap();
        UserService service = new UserServiceimpl();
        PageBean<User> pb=service.finduserbypage(currentPage,rows,condition);
        request.setAttribute("condition",condition);

//        System.out.println(pb);

        request.setAttribute("pb",pb);
        System.out.println(pb.toString());
        System.out.println(pb);

        //转发到页面
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           doPost(request,response);


    }
}
