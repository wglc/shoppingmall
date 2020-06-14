package dao;

import domain.Admin;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCutils.JDBCutil;

public class AdminDaoimpl implements AdminDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCutil.getDataSource());

    @Override
    public Admin findUsernameandpassword(String name, String password) {
        try {
            String sql = "select * from admin where name=? and password=?";
            Admin admin = template.queryForObject(sql, new BeanPropertyRowMapper<Admin>(Admin.class),name,password);
            return admin;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    }

