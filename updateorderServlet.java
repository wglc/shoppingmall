package web;

import domain.Consumption;
import org.apache.commons.beanutils.BeanUtils;
import service.OrderService;
import service.OrderServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/updateorderServlet")
public class updateorderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String orderid = request.getParameter("orderid");
        Map<String, String[]> map = request.getParameterMap();
        System.out.println(map.toString());
        Consumption consumption = new Consumption();
        try {
            BeanUtils.populate(consumption,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        OrderService service = new OrderServiceimpl();
        service.updateorder(consumption);
        request.setAttribute("updatemsg","修改成功");
        response.sendRedirect(request.getContextPath()+"/orderlistServlet");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);

    }
}
