package qhybupt.bookmanager.dao;

import qhybupt.bookmanager.model.Book;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface BookDAO {
	
	String table_name = " book ";
	String insert_field = " name, author, price ";
	String select_field = " id, status, " + insert_field;
	
	@Select({"select", select_field, "from", table_name, " where id=#{id}"})
	Book get(Long id);

	@Select({"select", select_field, "from", table_name})
	List<Book> selectAll();

	@Insert({"insert into", table_name, "(", insert_field,
	") values (#{name},#{author},#{price})"})
	void addBook(Book book);

	@Update({"update ", table_name, " set status=#{status} where id=#{id}"})
	void updateBookStatus(@Param("id") Long id, @Param("status") int status);

	@Update({"update ", table_name, " set name=#{name},author=#{author},price=#{price} where id=#{id}"})
	void updateBook(Book book);
	
	@Delete({"delete from", table_name, "where id=#{id}"})
	void deleteBook(@Param("id") Long id);
}
