/*
package com.wu.demo.fileupload.demo.model;


import javax.persistence.*;

@Entity
@Table(name="filemodel")
public class FileModel {
	@Id
	@GeneratedValue
    @Column(name = "id")
    private int id;
	
    @Column(name = "name")
	private String name;

    @Column(name = "description")
	private String description;
    
    */
/*@Column(name = "mimetype")
	private String mimetype;
	
	@Lob
    @Column(name="pic")
    private byte[] pic;*//*

	
	public FileModel(){}
	
	public FileModel(String name){
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}*/
