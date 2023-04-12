package org.example.DAO;

public interface ILoginDao {
    public int authenticate(String username,String password);
    public int addUser(String user,String role);
}
