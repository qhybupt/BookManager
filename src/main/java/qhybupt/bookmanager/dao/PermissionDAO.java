package qhybupt.bookmanager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import qhybupt.bookmanager.model.Permission;

@Mapper
public interface PermissionDAO {

	String table_name = " permission ";
	String insert_field = " name, desc_, url ";
	String select_field = " id, " + insert_field;
	
	@Select({"select", select_field, "from", table_name})
	List<Permission> selectAll();
	
	@Select({"select", select_field, "from", table_name, " where id=#{id}"})
	Permission selectById(@Param("id") Long id);
	
	@Select({"select", select_field, "from", table_name, " where name=#{name}"})
	Permission selectByName(@Param("name") String name);
	
	@Select({"select name from", table_name, " where id=#{id}"})
	String selectNameById(@Param("id") Long id);

	@Select({"select url from", table_name, " where id=#{id}"})
	String selectUrlById(@Param("id") Long id);
}
