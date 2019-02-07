package com.karenngomes.sistema.utils;

public enum AcademicTypes {
	UNDERGRADUATE(1), POSTGRADUATE(2);
	
	private int value;
	
	AcademicTypes(int valueOp) {
		value = valueOp;
	}
	
	int getValue() {
		return value;
	}
	void setValue(int value) {
		this.value = value;
	}
	
}
