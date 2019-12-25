package org.java.freemarker.test;

public class Student {
	private String stuid;
	private String name;
	private String age;
	public String getStuid() {
		return stuid;
	}
	public void setStuid(String stuid) {
		this.stuid = stuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public Student() {
		super();
	}
	public Student(String stuid, String name, String age) {
		super();
		this.stuid = stuid;
		this.name = name;
		this.age = age;
	}
	
}
