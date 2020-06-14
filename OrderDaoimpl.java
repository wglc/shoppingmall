package dao;

import domain.Consumption;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCutils.JDBCutil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrderDaoimpl implements  OrderDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCutil.getDataSource());
    @Override
    public List<Consumption> findallorder() {
        String sql="select * from consumption";
        List<Consumption> consumption = template.query(sql, new BeanPropertyRowMapper<Consumption>(Consumption.class));
        return consumption;
    }

    @Override
    public Consumption findorderbyid(int id) {
        String sql="select * from consumption where orderid=?";
        Consumption consumption = template.queryForObject(sql, new BeanPropertyRowMapper<Consumption>(Consumption.class),id);
        return consumption;
    }

    @Override
    public void updateorder(Consumption consumption) {
        String sql="update consumption set uname=?,gname=?,gamount=?,zprice=?,ordertime=?,address=? where orderid=?";
        template.update(sql,consumption.getUname(), consumption.getGname(), consumption.getGamount(), consumption.getZprice(), consumption.getOrdertime(), consumption.getAddress(), consumption.getOrderid());
    }

    @Override
    public void deleteorder(int id) {
        String sql ="delete from consumption where orderid=?";
        System.out.println(id);
        template.update(sql,id);
    }

    @Override
    public void addorder(Consumption consumption) {
        String sql="insert into  consumption values(null,?,?,?,?,?,?)";
        template.update(sql,consumption.getUname(), consumption.getGname(), consumption.getGamount(), consumption.getZprice(), consumption.getOrdertime(), consumption.getAddress());
    }

    @Override
    public List<Consumption> findorderbycondition(Map<String, String[]> condition) {
        String sql = "select * from consumption where 1=1";
        StringBuilder sb = new StringBuilder(sql);
        Set<String> strings = condition.keySet();
        List<Object> params = new ArrayList<Object>();

        for (String key : strings) {
            String value = condition.get(key)[0];
            if (value != null && !"".equals(value)) {
                sb.append(" and " + key + " like ? ");
                params.add("%" + value + "%");
            }
        }

        sql = sb.toString();
        System.out.println(sql);
        System.out.println(params.toArray());
        return template.query(sql, new BeanPropertyRowMapper<Consumption>(Consumption.class), params.toArray());


    }
    public List<Consumption> myorder(String uname) {
        String sql="select * from consumption where uname=?";
        List<Consumption> consumption = template.query(sql, new BeanPropertyRowMapper<Consumption>(Consumption.class),uname);
        return consumption;
    }

}
