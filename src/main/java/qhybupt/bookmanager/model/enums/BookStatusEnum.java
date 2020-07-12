package qhybupt.bookmanager.model.enums;

public enum BookStatusEnum {
	
	NORMAL(0),  //ÔÚ¼Ü/¹é»¹
	BORROW(1),  //½è³ö
	;
	
	private int value;
	
	BookStatusEnum(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}

}
