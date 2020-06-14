package dao;

import domain.Goods;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCutils.JDBCutil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GoodsDaoimpl implements GoodsDao{
    private JdbcTemplate template=new JdbcTemplate(JDBCutil.getDataSource());
    @Override
    public List<Goods> findallgoods() {
        String sql="select * from goods";
        List<Goods> goods = template.query(sql, new BeanPropertyRowMapper<Goods>(Goods.class));
        return goods;
    }

    @Override
    public void delgoods(int id) {
        String sql="delete from goods where goodsid=?";
        System.out.println(id);
        template.update(sql,id);
    }

    @Override
    public Goods findgoodsbyid(int id) {
        String sql="select * from goods where goodsid=?";
        Goods goods=template.queryForObject(sql, new BeanPropertyRowMapper<Goods>(Goods.class),id);
        return goods;
    }

    @Override
    public void updategoods(Goods goods) {
        String sql="update goods set goodsname=?,goodsvalue=?,vipvalue=?,amount=? where goodsid=? ";
        template.update(sql,goods.getGoodsname(),goods.getGoodsvalue(),goods.getVipvalue(),goods.getAmount(),goods.getGoodsid());
        System.out.println(goods.toString());
    }

    @Override
    public void addgoods(Goods goods) {
        String sql2 = "insert into goods values(null,?,?,?,?)";
        //2.执行sql
        template.update(sql2, goods.getGoodsname(), goods.getGoodsvalue(),goods.getVipvalue(),goods.getAmount());
    }

    @Override
    public List<Goods> findgoodsbycondition(Map<String, String[]> condition) {
        String sql = "select * from goods where 1=1";
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
        return template.query(sql, new BeanPropertyRowMapper<Goods>(Goods.class), params.toArray());


    }

    @Override
    public List<Goods> findbytime() {
        String sql="select * from goods where 1=1 limit 10    ";
        List<Goods> goods = template.query(sql, new BeanPropertyRowMapper<Goods>(Goods.class));
        return goods;
    }

}
