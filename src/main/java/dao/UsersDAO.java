package dao;

import entity.Users;

public interface UsersDAO {
	/*Truy vấn kiểm tra Id và Password*/
	 Users login(String id, String password);
}
