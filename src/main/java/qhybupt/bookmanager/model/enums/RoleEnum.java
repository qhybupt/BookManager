package qhybupt.bookmanager.model.enums;

public enum RoleEnum {
	
	Admin(1), //管理员
	User(2),  //普通用户
	;
	
	private long value;
	
	RoleEnum(long value){
		this.value = value;
	}
	
	public long getValue(){
		return value;
	}

}
