package web;

import domain.Goods;
import service.GoodsService;
import service.GoodsServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet( "/findgoodsconditionServlet")
public class findgoodsconditionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> condition = request.getParameterMap();
        GoodsService service = new GoodsServiceimpl();
        List<Goods> goods=service.findgoodsbycondition(condition);
        request.setAttribute("goods",goods);
        System.out.println("goods"+""+goods);
        //转发到页面
        request.getRequestDispatcher("/goods.jsp").forward(request,response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
