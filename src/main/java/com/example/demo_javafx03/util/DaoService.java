package com.example.demo_javafx03.util;

import java.sql.SQLException;
import java.util.List;

public interface DaoService<T> {
    List<T> fetchAll() throws SQLException, ClassNotFoundException;

    int addData(T t) throws SQLException, ClassNotFoundException;

    int updateData(T t);

    int deleteData(T t);
}
