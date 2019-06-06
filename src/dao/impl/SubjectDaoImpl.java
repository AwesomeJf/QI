package dao.impl;

import dao.SubjectDao;
import models.Subject;
import utils.db.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectDaoImpl extends BaseDao implements SubjectDao {

    public SubjectDaoImpl() {
        table = "subject";
    }

    private Subject makeSubject(ResultSet resultSet) {
        Subject subject = new Subject();
        try {
            subject.setId(resultSet.getInt("id"));
            subject.setIndex(resultSet.getInt("index"));
            subject.setTitle(resultSet.getString("title"));
            subject.setType(resultSet.getString("type"));
            subject.setPaperId(resultSet.getInt("paperId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subject;
    }

    public Subject find(int pk) {
        return new Subject();
    }

    public boolean add(Subject subject) {
        return true;
    }


    public boolean update(Subject subject) {
        return true;
    }

    public boolean delete(int pk) {
        return true;
    }
}
