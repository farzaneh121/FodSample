package com.farzaneh.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.farzaneh.exception.ProcessException;
import com.farzaneh.model.Person;
import com.farzaneh.service.DbConneection;

public class PersonDAO {
	private DbConneection dbConneection;
	private static final String INSERT_PERSON = "INSERT INTO fod.persons (person_id , principal_name,first_name"
			+ ",last_name,person_type_code,email)\n" + "VALUES (nextval('fod.person_seq'),?,?,?,?,?)";
	private static final String FIND_MEMBER_INFO = "select p.first_name , p.last_name , p.principal_name"
			+ " from fod.persons p where p.principal_name=? and p.email=?";
	public static final Map<String, Person> PERSON_MAP = new HashMap<String, Person>();

	public PersonDAO() {
	}

	public void insertUser(Person person) throws SQLException {
		dbConneection = new DbConneection();
		try (Connection connection = dbConneection.getConnectionInstance();
				PreparedStatement statement = connection.prepareStatement(INSERT_PERSON)) {
			statement.setString(1, person.getPrincipalName());
			statement.setString(2, person.getFirstName());
			statement.setString(3, person.getLastName());
			statement.setString(4, person.getPersonTypeCode());
			statement.setString(5, person.getEmailAddress());
			statement.execute();

		} finally {
			dbConneection.closeConnection();
		}

	}

	public Person findPerson(Person person) throws SQLException {
		dbConneection = new DbConneection();
		try (PreparedStatement statement = dbConneection.getConnectionInstance().prepareStatement(FIND_MEMBER_INFO)) {
			statement.setString(1, person.getPrincipalName());
			statement.setString(2, person.getEmailAddress());
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				person = new Person();
				person.setFirstName(result.getString(1));
				person.setLastName(result.getString(2));
				person.setPrincipalName(result.getString(3));
				return person;
			} else {
				throw new ProcessException("unfortunatly we can't find any member with your info!");
			}

		} finally {
			dbConneection.closeConnection();
		}
	}

}
