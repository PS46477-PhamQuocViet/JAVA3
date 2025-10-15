package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.News;
import utils.Jdbc;

public class NewsDAOImpl implements NewsDAO {
	/** ✅ Truy vấn tất cả bản tin có thuộc tính home = true */
	@Override
	public List<News> selectHome() {
		try {
			String sql = "SELECT * FROM News WHERE Home = ?";
			List<News> entities = new ArrayList<>();
			Object[] values = { 1 };
			ResultSet resultSet = Jdbc.executeQuery(sql, values);
			while (resultSet.next()) {
				News entity = new News();
				entity.setId(resultSet.getString("Id"));
				entity.setTitle(resultSet.getString("Title"));
				entity.setContent(resultSet.getString("Content"));
				entity.setImage(resultSet.getString("Image"));
				entity.setPostedDate(resultSet.getDate("PostedDate"));
				entity.setAuthor(resultSet.getString("Author"));
				entity.setViewCount(resultSet.getInt("ViewCount"));
				entity.setCategoryId(resultSet.getString("CategoryId"));
				entity.setHome(resultSet.getBoolean("Home"));
				entities.add(entity);
			}
			return entities;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	 /** ✅ Truy vấn bản tin thuộc loại Văn Hoá */
	 @Override
		public List<News> selectVanHoa() {
			try {
				String sql = "SELECT * FROM News WHERE CategoryId = ?";
				List<News> entities = new ArrayList<>();
				Object[] values = {"VH"};
				ResultSet resultSet = Jdbc.executeQuery(sql, values);
				while (resultSet.next()) {
					News entity = new News();
					entity.setId(resultSet.getString("Id"));
					entity.setTitle(resultSet.getString("Title"));
					entity.setContent(resultSet.getString("Content"));
					entity.setImage(resultSet.getString("Image"));
					entity.setPostedDate(resultSet.getDate("PostedDate"));
					entity.setAuthor(resultSet.getString("Author"));
					entity.setViewCount(resultSet.getInt("ViewCount"));
					entity.setCategoryId(resultSet.getString("CategoryId"));
					entity.setHome(resultSet.getBoolean("Home"));
					entities.add(entity);
				}
				return entities;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	 
	 
	 /** ✅ Truy vấn bản tin thuộc loại Pháp Luật */
	 @Override
		public List<News> selectPhapLuat() {
			try {
				String sql = "SELECT * FROM News WHERE CategoryId = ?";
				List<News> entities = new ArrayList<>();
				Object[] values = {"PL"};
				ResultSet resultSet = Jdbc.executeQuery(sql, values);
				while (resultSet.next()) {
					News entity = new News();
					entity.setId(resultSet.getString("Id"));
					entity.setTitle(resultSet.getString("Title"));
					entity.setContent(resultSet.getString("Content"));
					entity.setImage(resultSet.getString("Image"));
					entity.setPostedDate(resultSet.getDate("PostedDate"));
					entity.setAuthor(resultSet.getString("Author"));
					entity.setViewCount(resultSet.getInt("ViewCount"));
					entity.setCategoryId(resultSet.getString("CategoryId"));
					entity.setHome(resultSet.getBoolean("Home"));
					entities.add(entity);
				}
				return entities;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	 
	 
	 /** ✅ Truy vấn bản tin thuộc loại Thể Thao */
	 @Override
		public List<News> selectTheThao() {
			try {
				String sql = "SELECT * FROM News WHERE CategoryId = ?";
				List<News> entities = new ArrayList<>();
				Object[] values = {"TT"};
				ResultSet resultSet = Jdbc.executeQuery(sql, values);
				while (resultSet.next()) {
					News entity = new News();
					entity.setId(resultSet.getString("Id"));
					entity.setTitle(resultSet.getString("Title"));
					entity.setContent(resultSet.getString("Content"));
					entity.setImage(resultSet.getString("Image"));
					entity.setPostedDate(resultSet.getDate("PostedDate"));
					entity.setAuthor(resultSet.getString("Author"));
					entity.setViewCount(resultSet.getInt("ViewCount"));
					entity.setCategoryId(resultSet.getString("CategoryId"));
					entity.setHome(resultSet.getBoolean("Home"));
					entities.add(entity);
				}
				return entities;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	 
	 
	 /** ✅ Lấy danh sách bản tin cùng loại (theo categoryId, loại trừ id hiện tại) */
		@Override
		public List<News> selectRelated(String categoryId, String id) {
			try {
				String sql = "SELECT * FROM News WHERE CategoryId = ? AND Id <> ? ORDER BY PostedDate DESC";
				List<News> entities = new ArrayList<>();
				Object[] values = {categoryId, id};
				ResultSet rs = Jdbc.executeQuery(sql, values);
				while (rs.next()) {
					News entity = new News();
					entity.setId(rs.getString("Id"));
					entity.setTitle(rs.getString("Title"));
					entity.setImage(rs.getString("Image"));
					entity.setContent(rs.getString("Content"));
					entity.setAuthor(rs.getString("Author"));
					entity.setPostedDate(rs.getDate("PostedDate"));
					entity.setViewCount(rs.getInt("ViewCount"));
					entity.setCategoryId(rs.getString("CategoryId"));
					entity.setHome(rs.getBoolean("Home"));
					entities.add(entity);
				}
				return entities;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

	/** ✅ Truy vấn bản tin theo khóa chính */
	@Override
    public News selectById(String id) {
        String sql = "SELECT * FROM News WHERE Id = ?";
        try {
            Object[] values = { id };
            ResultSet resultSet = Jdbc.executeQuery(sql, values);
            if (resultSet.next()) {
                News entity = new News();
                entity.setId(resultSet.getString("Id"));
                entity.setTitle(resultSet.getString("Title"));
                entity.setContent(resultSet.getString("Content"));
                entity.setImage(resultSet.getString("Image"));
                entity.setPostedDate(resultSet.getDate("PostedDate"));
                entity.setAuthor(resultSet.getString("Author"));
                entity.setViewCount(resultSet.getInt("ViewCount"));
                entity.setCategoryId(resultSet.getString("CategoryId"));
                entity.setHome(resultSet.getBoolean("Home"));
                return entity;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Không tìm thấy bản tin có id = " + id);
    }
	
	
	/** ✅ Tăng viewCount lên 1 */
	 @Override
	    public void updateViewCount(String id) {
	        String sql = "UPDATE News SET ViewCount = ViewCount + 1 WHERE Id = ?";
	        try {
	            Object[] values = { id };
	            Jdbc.executeUpdate(sql, values);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }

	/** ✅ Thêm mới bản tin */
	 @Override
	    public void create(News news) {
	        String sql = "INSERT INTO News (Id, Title, Content, Image, PostedDate, Author, ViewCount, CategoryId, Home) "
	                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        try {
	            Object[] values = {
	                news.getId(),
	                news.getTitle(),
	                news.getContent(),
	                news.getImage(),
	                news.getPostedDate().getTime(),
	                news.getAuthor(),
	                news.getViewCount(),
	                news.getCategoryId(),
	                news.isHome()
	            };
	            Jdbc.executeUpdate(sql, values);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }

	/** ✅ Cập nhật bản tin theo khóa chính */
	 @Override
	    public void update(News news) {
	        String sql = "UPDATE News SET Title=?, Content=?, Image=?, PostedDate=?, Author=?, ViewCount=?, CategoryId=?, Home=? WHERE Id=?";
	        try {
	            Object[] values = {
	                news.getTitle(),
	                news.getContent(),
	                news.getImage(),
	                news.getPostedDate().getTime(),
	                news.getAuthor(),
	                news.getViewCount(),
	                news.getCategoryId(),
	                news.isHome(),
	                news.getId()
	            };
	            Jdbc.executeUpdate(sql, values);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }

	/** ✅ Xóa bản tin theo id */
	 @Override
	    public void delete(String id) {
	        String sql = "DELETE FROM News WHERE Id=?";
	        try {
	            Object[] values = { id };
	            Jdbc.executeUpdate(sql, values);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }

	

	/** ✅ Truy vấn 5 bản tin có lượng view cao nhất */
	 @Override
	    public List<News> selectTopView() {
	        String sql = "SELECT TOP 5 * FROM News ORDER BY ViewCount DESC";
	        try {
	            List<News> entities = new ArrayList<>();
	            Object[] values = {};
	            ResultSet resultSet = Jdbc.executeQuery(sql, values);
	            while (resultSet.next()) {
	                News entity = new News();
	                entity.setId(resultSet.getString("Id"));
	                entity.setTitle(resultSet.getString("Title"));
	                entity.setContent(resultSet.getString("Content"));
	                entity.setImage(resultSet.getString("Image"));
	                entity.setPostedDate(resultSet.getDate("PostedDate"));
	                entity.setAuthor(resultSet.getString("Author"));
	                entity.setViewCount(resultSet.getInt("ViewCount"));
	                entity.setCategoryId(resultSet.getString("CategoryId"));
	                entity.setHome(resultSet.getBoolean("Home"));
	                entities.add(entity);
	            }
	            return entities;
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }

	/** ✅ Truy vấn 5 bản tin mới nhất */
	 @Override
	    public List<News> selectTopNew() {
	        String sql = "SELECT TOP 5 * FROM News ORDER BY PostedDate DESC";
	        try {
	            List<News> entities = new ArrayList<>();
	            Object[] values = {};
	            ResultSet resultSet = Jdbc.executeQuery(sql, values);
	            while (resultSet.next()) {
	                News entity = new News();
	                entity.setId(resultSet.getString("Id"));
	                entity.setTitle(resultSet.getString("Title"));
	                entity.setContent(resultSet.getString("Content"));
	                entity.setImage(resultSet.getString("Image"));
	                entity.setPostedDate(resultSet.getDate("PostedDate"));
	                entity.setAuthor(resultSet.getString("Author"));
	                entity.setViewCount(resultSet.getInt("ViewCount"));
	                entity.setCategoryId(resultSet.getString("CategoryId"));
	                entity.setHome(resultSet.getBoolean("Home"));
	                entities.add(entity);
	            }
	            return entities;
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
	
	/** ✅ Truy vấn bản tin theo id phóng viên (userId) */
	 @Override
	    public List<News> selectByUser(String userId) {
	        String sql = "SELECT * FROM News WHERE Author = ?";
	        try {
	            List<News> entities = new ArrayList<>();
	            Object[] values = { userId };
	            ResultSet resultSet = Jdbc.executeQuery(sql, values);
	            while (resultSet.next()) {
	                News entity = new News();
	                entity.setId(resultSet.getString("Id"));
	                entity.setTitle(resultSet.getString("Title"));
	                entity.setContent(resultSet.getString("Content"));
	                entity.setImage(resultSet.getString("Image"));
	                entity.setPostedDate(resultSet.getDate("PostedDate"));
	                entity.setAuthor(resultSet.getString("Author"));
	                entity.setViewCount(resultSet.getInt("ViewCount"));
	                entity.setCategoryId(resultSet.getString("CategoryId"));
	                entity.setHome(resultSet.getBoolean("Home"));
	                entities.add(entity);
	            }
	            return entities;
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
}
