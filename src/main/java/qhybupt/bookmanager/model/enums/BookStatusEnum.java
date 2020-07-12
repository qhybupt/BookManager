package qhybupt.bookmanager.model.enums;

public enum BookStatusEnum {
	
	NORMAL(0),  //�ڼ�/�黹
	BORROW(1),  //���
	;
	
	private int value;
	
	BookStatusEnum(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}

}
