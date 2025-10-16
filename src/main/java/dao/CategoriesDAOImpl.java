package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Categories;
import utils.Jdbc;

public class CategoriesDAOImpl implements CategoriesDAO{
	@Override
	public List<Categories> selectAll() {
		try {
			List<Categories> list = new ArrayList<>();
			Object[] values = {};
			String sql = "SELECT * FROM CATEGORIES";
			ResultSet resultSet = Jdbc.executeQuery(sql);
			while (resultSet.next()) {
				Categories categories = new Categories();
				categories.setId(resultSet.getString("Id"));
				categories.setName(resultSet.getString("Name"));
				list.add(categories);
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public Categories selectById(String id) {
	    try {
	    	Object[] values = {id};
	        String sql = "SELECT * FROM CATEGORIES WHERE Id = ?";
	        ResultSet resultSet = Jdbc.executeQuery(sql, values);
	        if (resultSet.next()) {
	            Categories categories = new Categories();
	            categories.setId(resultSet.getString("Id"));
	            categories.setName(resultSet.getString("Name"));
	            return categories;
	        }
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	    throw new RuntimeException("Không tìm thấy danh mục có id = " + id);
	}
	
	
	@Override
	public void create(Categories entity) {
		String sql = "INSERT INTO CATEGORIES (Id, Name) VALUES (?, ?)";
		try {
			Object[] values = {
				entity.getId(),
				entity.getName()
			};
			Jdbc.executeUpdate(sql, values);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	/**Cập nhật*/
	@Override
	public void update(Categories entity) {
		String sql = "UPDATE CATEGORIES SET Name=? WHERE Id=?";
		try {
			Object[] values = {
				entity.getName(),
				entity.getId()
			};
			Jdbc.executeUpdate(sql, values);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**Xóa theo mã*/
	@Override
	public void deleteById(String id) {
		String sql = "DELETE FROM CATEGORIES WHERE Id=?";
		try {
			Object[] values = { id };
			Jdbc.executeUpdate(sql, values);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
