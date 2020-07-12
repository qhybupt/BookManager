package qhybupt.bookmanager.model.enums;

public enum RoleEnum {
	
	Admin(1), //����Ա
	User(2),  //��ͨ�û�
	;
	
	private long value;
	
	RoleEnum(long value){
		this.value = value;
	}
	
	public long getValue(){
		return value;
	}

}
