package com.fcul.grouper.model.types;

public enum FeedbackType {

	FORMAL,

	SUMMATIVE;

	public boolean isFormal() {
		return this == FORMAL;
	}

	public boolean isSummative() {
		return this == SUMMATIVE;
	}

}
