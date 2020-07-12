package qhybupt.bookmanager.dao;

import qhybupt.bookmanager.model.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

import org.apache.ibatis.annotations.Delete;

@Mapper
public interface RoleDAO {
	
	String table_name = " role ";
	String insert_field = " name, desc_ ";
	String select_field = " id, " + insert_field;
	
	@Insert({"insert into", table_name, "(", insert_field, ") values (#{name},#{desc_})"})
	public void addRole(Role role);

	@Delete({"delet from", table_name, "where id=#{id}"})
	public void deleteRole(Long id);
	
	@Select({"select role from", table_name, "where uid=#{uid}"})
	List<Role> selectRoleByUserId(long uid);

	@Select({"select", select_field, "from", table_name, "where id=#{id}"})
	public Role selectById(long id);

	@Update({"update", table_name, "set name=#{name}, desc_=#{desc_} where id=#{id}"})
	public void updateRole(Role role);
}
