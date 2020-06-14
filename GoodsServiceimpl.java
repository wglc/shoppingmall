package service;

import dao.GoodsDao;
import dao.GoodsDaoimpl;
import domain.Goods;

import java.util.List;
import java.util.Map;

public class GoodsServiceimpl implements GoodsService{
    private GoodsDao dao= new GoodsDaoimpl();
    @Override
    public List<Goods> findallgoods() {
        return dao.findallgoods();
    }

    @Override
    public void delgoods(String id) {
        dao.delgoods(Integer.parseInt(id));
    }

    @Override
    public Goods findgoodsbyid(String id) {
         return dao.findgoodsbyid(Integer.parseInt(id));
    }

    @Override
    public void updategoods(Goods goods) {
        dao.updategoods(goods);
    }

    @Override
    public void addgoods(Goods goods) {
        dao.addgoods(goods);
    }

    @Override
    public void delseclectgoods(String[] goods) {
        for (String id:goods){
            dao.delgoods(Integer.parseInt(id));
            System.out.println(id);
        }
    }

    @Override
    public List<Goods> findgoodsbycondition(Map<String, String[]> condition) {
        return dao.findgoodsbycondition(condition);
    }

    @Override
    public List<Goods> findbytime() {
         return dao.findbytime();
    }
}
