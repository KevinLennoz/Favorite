package fr.eql.al35.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
public class User {

	private Integer id;
	private String login;
	private String password;
	private String email;
	private String name;
	private String surname;
	private LocalDate birthDate;
	private String phoneNumber;
	private LocalDateTime subscribingDate;
	private LocalDateTime unsubscribingDate;
	
	private Gender gender;
	
	private UserType userType;
	
	private Set<Address> addresses;
	
	private Set<Command> commands;

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + ", email=" + email + ", name=" + name
				+ ", surname=" + surname + ", birthDate=" + birthDate + ", phoneNumber=" + phoneNumber
				+ ", subscribingDate=" + subscribingDate + ", unsubscribingDate=" + unsubscribingDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((subscribingDate == null) ? 0 : subscribingDate.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((unsubscribingDate == null) ? 0 : unsubscribingDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (subscribingDate == null) {
			if (other.subscribingDate != null)
				return false;
		} else if (!subscribingDate.equals(other.subscribingDate))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (unsubscribingDate == null) {
			if (other.unsubscribingDate != null)
				return false;
		} else if (!unsubscribingDate.equals(other.unsubscribingDate))
			return false;
		return true;
	}
}
