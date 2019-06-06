package dao;

import models.Paper;

public interface PaperDao {
    public Paper find(int pk);

    public boolean add(Paper paper);

    public boolean update(Paper paper);

    public boolean delete(int pk);
}
