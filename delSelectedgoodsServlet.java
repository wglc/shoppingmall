package web;

import service.GoodsService;
import service.GoodsServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delSelectedgoodsServlet")
public class delSelectedgoodsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String[] goodsid = request.getParameterValues("goodsid");
        GoodsService service = new GoodsServiceimpl();
        service.delseclectgoods(goodsid);
        response.sendRedirect(request.getContextPath()+"/goodslistServlet");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
