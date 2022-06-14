package com.example.demo_javafx03.dao;

import com.example.demo_javafx03.entity.Department;
import com.example.demo_javafx03.util.DaoService;
import com.example.demo_javafx03.util.MySQLUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoImpl implements DaoService<Department> {
    @Override
    public List<Department> fetchAll() throws SQLException, ClassNotFoundException {
        List<Department> departments = new ArrayList<>();
        try (Connection connection = MySQLUtil.createConnection()) {
            String query = "SELECT id, name FROM department ORDER BY name";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Department department = new Department();
                        department.setId(rs.getInt("id"));
                        department.setName(rs.getString("name"));
                        departments.add(department);
                    }
                }
            }
        }

        return departments;
    }

    @Override
    public int addData(Department department) throws SQLException, ClassNotFoundException {
        int result = 0;
        try (Connection connection = MySQLUtil.createConnection()) {
            String query = "INSERT INTO department(id, name) VALUES (?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, department.getId());
                ps.setString(2, department.getName());
                if(ps.executeUpdate() != 0){
                    result = 1;
                    connection.commit();
                }else {
                    connection.rollback();
                }
            }
        }
        return result;
    }

    @Override
    public int updateData(Department department) {
        return 0;
    }

    @Override
    public int deleteData(Department department) {
        return 0;
    }
}
