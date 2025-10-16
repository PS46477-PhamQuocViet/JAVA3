package dao;

import java.sql.ResultSet;

import entity.Users;
import utils.Jdbc;

public class UsersDAOImpl implements UsersDAO{
	/*Truy vấn kiểm tra Id và Password*/
	@Override
    public Users login(String id, String password) {
        try {
            String sql = "SELECT * FROM USERS WHERE Id = ? AND Password = ?";
            Object[] values = { id, password };
            ResultSet resultSet = Jdbc.executeQuery(sql, values);
            if (resultSet.next()) {
                Users user = new Users();
                user.setId(resultSet.getString("Id"));
                user.setPassword(resultSet.getString("Password"));
                user.setFullname(resultSet.getString("Fullname"));
                user.setBirthday(resultSet.getDate("Birthday"));
                user.setGender(resultSet.getBoolean("Gender"));
                user.setMobile(resultSet.getString("Mobile"));
                user.setEmail(resultSet.getString("Email"));
                user.setRole(resultSet.getBoolean("Role")); // true = admin, false = phóng viên
                return user;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
