package com.plusitsolution.testone.domain;

import com.plusitsolution.testone.entity.OnelinkEntity;

public class oneLinkDomain {
	private String name;
	private String generateLink;
	private String iosLink;
	private String andriodLink;
	private String desktopLink;
	
	
	
	public OnelinkEntity toEntity() {
		OnelinkEntity entity = new OnelinkEntity();
		entity.setName(name);
		entity.setGenerateLink(generateLink);
		entity.setIosLink(iosLink);
		entity.setAndriodLink(andriodLink);
		entity.setDesktopLink(desktopLink);
		return entity;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGenerateLink() {
		return generateLink;
	}
	public void setGenerateLink(String generateLink) {
		this.generateLink = generateLink;
	}
	public String getIosLink() {
		return iosLink;
	}
	public void setIosLink(String iosLink) {
		this.iosLink = iosLink;
	}
	public String getAndriodLink() {
		return andriodLink;
	}
	public void setAndriodLink(String andriodLink) {
		this.andriodLink = andriodLink;
	}
	public String getDesktopLink() {
		return desktopLink;
	}
	public void setDesktopLink(String desktopLink) {
		this.desktopLink = desktopLink;
	}
	
}
