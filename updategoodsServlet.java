package web;

import domain.Goods;
import org.apache.commons.beanutils.BeanUtils;
import service.GoodsService;
import service.GoodsServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/updategoodsServlet")
public class updategoodsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String goodsid = request.getParameter("goodsid");
        System.out.println(goodsid);
        Map<String, String[]> map = request.getParameterMap();
        Goods goods = new Goods();
        try {
            BeanUtils.populate(goods,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        GoodsService service = new GoodsServiceimpl();
        service.updategoods(goods);
        response.sendRedirect(request.getContextPath()+"/goodslistServlet");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                    doPost(request,response);
    }
}
