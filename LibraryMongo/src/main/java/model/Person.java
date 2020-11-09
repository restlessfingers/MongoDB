package model;

import java.util.Date;

public class Person {
	private String name;
	private String surname;
	private Date birthdate;
	private int id;
	private int age;
	
	public Person(String name, Date birthdate, int id) {
		this.name = name;
		this.birthdate = birthdate;
		this.id = id;
	}
	public Person(String name,  int id) {
		this.name = name;
		this.birthdate = null;
		this.id = id;
	}
	public Person(String name,  String surname, int age) {
		this.name = name;
		this.surname = null;
		this.age = age;
	}
	
	
	  public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void imprimirDatos() { System.out.print("ID: ");
	  System.out.println(id); System.out.println("NAME: " + name);
	  System.out.print("BIRTHDATE: "); birthdate.getClass();
	  System.out.println(); }
	 
	 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Date getbirthdate() {
		return birthdate;
	}

	public void setbirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}