package service;

import dao.AdminDao;
import dao.AdminDaoimpl;
import domain.Admin;

public class AdminServiceimpl  implements AdminService{
    private AdminDao dao= new AdminDaoimpl();
    @Override
    public Admin login(Admin admin) {
            return dao.findUsernameandpassword(admin.getName(),admin.getPassword());
        }

}
