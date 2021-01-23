package com.gilbert.test.dataaccess;

//加上这个不会报错
//但是返回的jason会是空的
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {

	private long id;
	private String firstName, lastName;

	//java.lang.NoSuchMethodException: com.gilbert.test.dataaccess.Customer.<init>()
	//at java.lang.Class.getConstructor0(Class.java:3082) ~[na:1.8.0_212]
	public Customer() {
	}

	public Customer(long id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	//com.fasterxml.jackson.databind.exc.InvalidDefinitionException:
	// No serializer found for class com.gilbert.test.dataaccess.
	// Customer and no properties discovered to create BeanSerializer
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return String.format(
				"Customer[id=%d, firstName='%s', lastName='%s']",
				id, firstName, lastName);
	}
}
