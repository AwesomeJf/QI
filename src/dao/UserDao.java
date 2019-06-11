package dao;

import models.User;

public interface UserDao {
    public User find(int pk);

    public User find(String username, String password);

    public User find(String username);

    public boolean add(User user);

    public boolean update(User user);

    public boolean delete(int pk);
}
