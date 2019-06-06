package dao;

import models.Subject;

public interface SubjectDao {
    public Subject find(int pk);

    public boolean add(Subject subject);

    public boolean update(Subject subject);

    public boolean delete(int pk);
}
