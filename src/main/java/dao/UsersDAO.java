package dao;

import java.util.List;
import entity.Users;

public interface UsersDAO {
	/*Truy vấn kiểm tra Id và Password*/
	 Users login(String id, String password);
	 
	 /*Truy vấn tất cả người dùng*/
		List<Users> selectAll();

		/** Truy vấn theo mã */
		Users selectById(String id);

		/** Thêm mới */
		void create(Users entity);

		/** Cập nhật */
		void update(Users entity);

		/** Xóa theo mã */
		void deleteById(String id);
}
