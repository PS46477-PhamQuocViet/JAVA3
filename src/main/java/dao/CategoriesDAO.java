package dao;

import java.util.List;

import entity.Categories;
import entity.News;

public interface CategoriesDAO {
	List<Categories> selectAll();
	
	/**Truy vấn theo mã*/
	Categories selectById(String id);
	/**Thêm mới*/
	void create(Categories entity);
	/**Cập nhật*/
	void update(Categories entity);
	/**Xóa theo mã*/
	void deleteById(String id);
}
