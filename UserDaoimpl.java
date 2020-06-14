package dao;

import domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCutils.JDBCutil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class UserDaoimpl implements UserDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCutil.getDataSource());

    @Override
    public List<User> findAll() {
        String sql="select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    @Override
    public User findUsernameandpassword(String name, String password) {
        try {
            String sql = "select * from user where name=? and password=?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),name,password);
            return user;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public void register(User user) {

        String sql2 = "insert into user values(null,?,?,?,?,?,?)";
        //2.执行sql
        template.update(sql2, user.getNumber(), user.getName(), user.getBirthday(), user.getPassword(), user.getEmail(), user.getSex());
    }
    @Override
    public void addUser(User user) {
        String sql2 = "insert into user values(null,?,?,?,?,?,?)";
        //2.执行sql
        template.update(sql2, user.getNumber(), user.getName(), user.getBirthday(), user.getPassword(), user.getEmail(), user.getSex());
    }
    @Override
    public void deleteUser(int id) {
        String sql="delete from user where id=?";
        template.update(sql,id);
    }
    @Override
    public User finduserbyid(int id) {

            String sql = "select * from user where id=?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),id);
            return user;

    }
    @Override
    public void updateuser(User user) {
        String sql="update user set number=?,name=?,birthday=?,password=?,email=?,sex=? where id=?";
        template.update(sql,user.getNumber(), user.getName(), user.getBirthday(), user.getPassword(), user.getEmail(), user.getSex(), user.getId());
    }
    @Override
    public int findtotalCount(Map<String, String[]> condition) {

        String sql="select count(*) from user where 1=1";
        StringBuilder sb = new StringBuilder(sql);
        Set<String> strings = condition.keySet();
        List <Object> params=new ArrayList<Object>();
        for (String key:strings) {
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }
            String value = condition.get(key)[0];
            if(value!=null&&!"".equals(value)){
                sb.append(" and " +key+" like ? ");
                params.add("%"+value+"%");
            }
        }
        return template.queryForObject(sb.toString(),Integer.class,params.toArray());
    }
    @Override
    public List<User> findbypage(int start, int rows, Map<String, String[]> condition) {
        String sql="select * from user where 1=1";
        StringBuilder sb = new StringBuilder(sql);
        Set<String> strings = condition.keySet();
        List <Object> params=new ArrayList<Object>();
        for (String key:strings) {
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }
            String value = condition.get(key)[0];
            if(value!=null&&!"".equals(value)){
                sb.append(" and " +key+" like ? ");
                params.add("%"+value+"%");
            }
        }
        sb.append(" limit ?,? ");
        params.add(start);
        params.add(rows);
        sql=sb.toString();
        return template.query(sql, new BeanPropertyRowMapper<User>(User.class),params.toArray());
    }
}


