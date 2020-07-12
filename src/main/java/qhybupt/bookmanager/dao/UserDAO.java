package qhybupt.bookmanager.dao;

import qhybupt.bookmanager.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;

@Mapper
public interface UserDAO {
	String table_name = " user ";
	String insert_field = " name, email, password, salt ";
	String select_field = " id, " + insert_field;

	@Insert({"insert into", table_name, "(", insert_field,
	      ") values (#{name},#{email},#{password},#{salt})"})
	public void addUser(User user);
	
	@Delete({"delet from", table_name, "where id=#{id}"})
	public void deleteUser(long id);

	@Select({"select", select_field, "from", table_name, "where id=#{id}"})
	public User selectById(long id);

	@Select({"select", select_field, "from", table_name, "where name=#{name}"})
	public User selectByName(String name);

//	@Select({"select", select_field, "from", table_name, "where email=#{email}"})
//	User selectByEmail(String email);

	@Update({"update", table_name, "set name=#{name},email=#{email},password=#{password},salt=#{salt} where id=#{id}"})
	public void updateUser(User user);
}
