package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Categories;
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
	
	
	/*Truy vấn tất cả người dùng*/
	@Override
	public List<Users> selectAll() {
		try {
			List<Users> list = new ArrayList<>();
			Object[] values = { };
			String sql = "SELECT * FROM USERS";
			ResultSet resultSet = Jdbc.executeQuery(sql, values);
			while (resultSet.next()) {
				Users users = new Users();
				users.setId(resultSet.getString("Id"));
				users.setPassword(resultSet.getString("Password"));
				users.setFullname(resultSet.getString("Fullname"));
				users.setBirthday(resultSet.getDate("Birthday"));
				users.setGender(resultSet.getBoolean("Gender"));
				users.setMobile(resultSet.getString("Mobile"));
				users.setEmail(resultSet.getString("Email"));
				users.setRole(resultSet.getBoolean("Role"));
				list.add(users);
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	/** Truy vấn theo mã */
	 @Override
	    public Users selectById(String id) {
	        try {
	        	Object[] values = {id};
	            String sql = "SELECT * FROM USERS WHERE Id = ?";
	            ResultSet resultSet = Jdbc.executeQuery(sql, values);
	            if (resultSet.next()) {
	            	Users users = new Users();
					users.setId(resultSet.getString("Id"));
					users.setPassword(resultSet.getString("Password"));
					users.setFullname(resultSet.getString("Fullname"));
					users.setBirthday(resultSet.getDate("Birthday"));
					users.setGender(resultSet.getBoolean("Gender"));
					users.setMobile(resultSet.getString("Mobile"));
					users.setEmail(resultSet.getString("Email"));
					users.setRole(resultSet.getBoolean("Role"));
	                return users;
	            }
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	        return null;
	    }
	 
	 
	 /** Thêm mới */
	 @Override
	    public void create(Users entity) {
		 String sql = "INSERT INTO USERS (Id, Password, Fullname, Birthday, Gender, Mobile, Email, Role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	        try {
	            Object[] values = {
	                entity.getId(),
	                entity.getPassword(),
	                entity.getFullname(),
	                entity.getBirthday(),
	                entity.isGender(),
	                entity.getMobile(),
	                entity.getEmail(),
	                entity.isRole()
	            };
	            Jdbc.executeUpdate(sql, values);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
	 
	 
	 /** Cập nhật */
	 @Override
	    public void update(Users entity) {
		 String sql = "UPDATE USERS SET Password=?, Fullname=?, Birthday=?, Gender=?, Mobile=?, Email=?, Role=? WHERE Id=?";
	        try {
	            Object[] values = {
	                entity.getPassword(),
	                entity.getFullname(),
	                entity.getBirthday(),
	                entity.isGender(),
	                entity.getMobile(),
	                entity.getEmail(),
	                entity.isRole(),
	                entity.getId()
	            };
	            Jdbc.executeUpdate(sql, values);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
	 
	 
	 /** Xóa theo mã */
	 @Override
	    public void deleteById(String id) {
		 String sql = "DELETE FROM USERS WHERE Id = ?";
	        try {
	        	Object[] values = {id};
	            Jdbc.executeUpdate(sql, values);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
}
