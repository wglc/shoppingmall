package web;

import service.GoodsService;
import service.GoodsServiceimpl;
import domain.Goods;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findgoodsServlet")
public class findgoodsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        System.out.println(id);
        GoodsService service= new GoodsServiceimpl();
        Goods goods=service.findgoodsbyid(id);
        request.setAttribute("goods",goods);
        request.getRequestDispatcher("/goodsupdate.jsp").forward(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
