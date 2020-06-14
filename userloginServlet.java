package web;

import domain.Admin;
import domain.User;
import org.apache.commons.beanutils.BeanUtils;
import service.AdminService;
import service.AdminServiceimpl;
import service.UserService;
import service.UserServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/userloginServlet")
public class userloginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取数据
        //2.1获取用户填写验证码
        String verifycode = request.getParameter("verifycode");
        //3.验证码校验
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//确保验证码一次性
        if(!checkcode_server.equalsIgnoreCase(verifycode)){
            //验证码不正确
            //提示信息
            request.setAttribute("login_msg","验证码错误！");
            //跳转登录页面
            request.getRequestDispatcher("/userlogin.jsp").forward(request,response);

            return;
        }

        Map<String, String[]> map = request.getParameterMap();
        //4.封装User对象
        Admin admin = new Admin();
        try {
            BeanUtils.populate(admin,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        //5.调用Service查询
        AdminService service = new AdminServiceimpl();
        Admin loginadmin = service.login(admin);
        //6.判断是否登录成功
        if(loginadmin != null){
            //登录成功
            //将用户存入session
            session.setAttribute("user",loginadmin);
            //跳转页面
            response.sendRedirect(request.getContextPath()+"/goodslistServlet");
//            finduserbypageServlet
        }else{
            //登录失败
            //提示信息
            request.setAttribute("login_msg","用户名或密码错误！");
            //跳转登录页面
            request.getRequestDispatcher("/userlogin.jsp").forward(request,response);

        }




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
