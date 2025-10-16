package dao;

import java.util.List;

import entity.News;

public interface NewsDAO {
	/**	Truy vấn tất cả bản tin có thuộc tính home bằng true*/
	List<News> selectHome();
	
	/**	Truy vấn bản tin theo khóa chính ( khóa chính là tham số )*/
	News selectById(String id);
	
	/**	Cập nhật viewcount lên 1*/
	void updateViewCount(String id);
	
	/**	Truy vấn các bản tin của phóng viên theo id User ( tham số )*/
	List<News> selectByUser(String userId);
	
	/** ✅ Lấy danh sách bản tin cùng loại (theo categoryId, loại trừ id hiện tại) */
	List<News> selectRelated(String categoryId, String id);
	
	/**	Truy vấn 5 bản tin có lượng view ( viewcount ) cao nhất*/
	List<News> selectTopView();
	
	/**	Truy vấn 5 bản tin được Post mới nhất*/
	List<News> selectTopNew();
	
	/*Truy vấn theo loại tin*/
	List<News> selectByCategory(String categoryId);
	
	/**	Thêm mới bản tin ( tham số )*/
	void create(News news);
	
	/**	Cập nhật bản tin theo khóa chính ( tham số )*/
	void update(News news);
	
	/**	Xóa bản tin theo khóa chính( tham số )*/
	void delete(String id);
}
