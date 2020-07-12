package qhybupt.bookmanager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RolePermissionDAO {
	
	String table_name = " role_permission ";
	String insert_field = " rid, pid ";
	String select_field = " id, " + insert_field;
	
	@Select({"select pid from", table_name, " where rid=#{rid}"})
	List<Long> selectPermissionIdByRoleId(long rid);
}
