package web;

import dao.GoodsDaoimpl;
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

@WebServlet("/goodslistServlet")
public class goodslistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          request.setCharacterEncoding("utf-8");
          GoodsService service = new GoodsServiceimpl();
          List<Goods> goods=service.findallgoods();
          request.setAttribute("goods",goods);
          request.getRequestDispatcher("goods.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           doPost(request,response);
    }
}
