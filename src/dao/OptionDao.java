package dao;
import models.Option;

public interface OptionDao {
    public Option find(int pk);

    public boolean add(Option option);

    public boolean update(Option option);

    public boolean delete(int pk);
}
