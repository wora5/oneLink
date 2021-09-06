package com.plusitsolution.testone.entity;

import javax.persistence.Column;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document (indexName = "onelink-index")
public class OnelinkEntity {
	@Id
	@ReadOnlyProperty
	private String Id;
	@Field (type = FieldType.Keyword )
	private String name;
	@Field (type = FieldType.Keyword )
	@Column(unique = true, nullable = false)
	private String generateLink;
	@Field (type = FieldType.Keyword )
	private String iosLink;
	@Field (type = FieldType.Keyword )
	private String andriodLink;
	@Field (type = FieldType.Keyword )
	private String desktopLink;
	
	

	@Override
	public String toString() {
		return "OnelinkEntity [Id=" + Id + ", name=" + name + ", generateLink=" + generateLink + ", iosLink=" + iosLink
				+ ", andriodLink=" + andriodLink + ", desktopLink=" + desktopLink + "]";
	}



	public String getId() {
		return Id;
	}



	public void setId(String id) {
		Id = id;
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
