package com.example.demo.es;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import lombok.Data;

@Document(indexName = "myIndex",type = "goods")
@Data
public class Goods {
	@Id
	private Long id;
	@Field
	private String name;
	@Field
	private String description;
	public Goods(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
}
