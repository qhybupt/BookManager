package qhybupt.bookmanager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;
//import org.apache.ibatis.annotations.Delete;

@Mapper
public interface UserRoleDAO {
	
	String table_name = " user_role ";
	String insert_field = " uid, rid ";
	String select_field = " id, " + insert_field;
	
	@Select({"select rid from", table_name, " where uid=#{uid}"})
	List<Long> selectRoleIdByUserId(Long id);
	
	@Insert({"insert into", table_name, "(", insert_field, ") values (#{uid},#{rid})"})
	public void initRole(@Param("uid") Long userId, @Param("rid") Long roleId);

}
