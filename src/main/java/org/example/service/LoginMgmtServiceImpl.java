package org.example.service;

import org.example.DAO.ILoginDao;

public class LoginMgmtServiceImpl implements ILoginMgmt {
    private ILoginDao iLoginDao;

    public LoginMgmtServiceImpl(ILoginDao iLoginDao) {
        this.iLoginDao = iLoginDao;
    }

    public LoginMgmtServiceImpl() {
    }

    @Override
    public boolean login(String username, String password) {
        if (username.equals("") || password.equals("")){
            throw new IllegalArgumentException("Empty Credentials");
        }
        int count=iLoginDao.authenticate(username,password);
        if (count==0)
        return false;
        else
            return true;
    }
    public String registerUser(String user,String role){
        if (!user.equalsIgnoreCase("") || role.equalsIgnoreCase("guest")){
            iLoginDao.addUser(user,role);
            return "User added";
        }
        else{
            return "User Not added";
        }

    }
}
