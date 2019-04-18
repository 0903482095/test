package com.hoangnt.model;

public class Card implements Comparable<Card>{
	int number;
	String meterial;

	public Card(int number, String meterial) {
		super();
		this.number = number;
		this.meterial = meterial;
	}

	public Card() {
		super();
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getMeterial() {
		return meterial;
	}

	public void setMeterial(String meterial) {
		this.meterial = meterial;
	}
	@Override
	public int compareTo(Card o) {
		if(this.number>=o.number) {
			return 1;
		}
		else return -1;
	}
}
