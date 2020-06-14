package service;

import dao.UserDao;
import dao.UserDaoimpl;
import domain.PageBean;
import domain.User;

import java.util.List;
import java.util.Map;

public class UserServiceimpl implements UserService {
   private UserDao dao= new UserDaoimpl();
    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public User login(User user) {
        return dao.findUsernameandpassword(user.getName(),user.getPassword());
    }

    @Override
    public void register(User user) {
        dao.register(user);
    }

    @Override
    public void addUser(User user) {
        dao.addUser(user);
    }

    @Override
    public void deleteUser(String id) {

        dao.deleteUser(Integer.parseInt(id));
    }

    @Override
    public User finduserbyid(String id) {
        return dao.finduserbyid(Integer.parseInt(id));
    }

    @Override
    public void updateuser(User user) {
        dao.updateuser(user);
    }

    @Override
    public void delselectuser(String[] uids) {
        for (String id:uids) {
            dao.deleteUser(Integer.parseInt(id));
        }
    }

    @Override
    public PageBean<User> finduserbypage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage=Integer.parseInt(_currentPage);
        int rows=Integer.parseInt(_rows);
        PageBean<User> pb=new PageBean<User>();
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        int totalCount=dao.findtotalCount(condition);
         pb.setTotalCount(totalCount);

         int start=(currentPage-1) * rows;

         List<User> list=dao.findbypage(start,rows,condition);
         pb.setList(list);

         int totalPage=(totalCount % rows) == 0 ? totalCount/rows:(totalCount/rows)+1;
         pb.setTotalPage(totalPage);

        return pb;
    }
}
