package service;

import dao.OrderDao;
import dao.OrderDaoimpl;
import domain.Consumption;

import java.util.List;
import java.util.Map;

public class OrderServiceimpl implements OrderService{
    private OrderDao dao=new OrderDaoimpl();
    @Override
    public List<Consumption> findallorder() {
        return dao.findallorder();
    }

    @Override
    public Consumption findorderbyid(String id) {
        return dao.findorderbyid(Integer.parseInt(id));
    }

    @Override
    public void updateorder(Consumption consumption) {
         dao.updateorder(consumption);
    }

    @Override
    public void deleteorder(String id) {
        dao.deleteorder(Integer.parseInt(id));
    }

    @Override
    public void delseclectgoods(String[] orderid) {
            for (String id:orderid) {
                dao.deleteorder(Integer.parseInt(id));
            }
        }

    @Override
    public void addorder(Consumption consumption) {
        dao.addorder(consumption);
    }

    @Override
    public List<Consumption> findorderbycondition(Map<String, String[]> condition) {
        return dao.findorderbycondition(condition);
    }
    public void delseclectgoodsc(String[] orderid) {
        for (String id:orderid) {
            dao.deleteorder(Integer.parseInt(id));
        }
    }
}
