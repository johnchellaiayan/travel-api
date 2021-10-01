package com.ta.entity.lookup;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "GROUP_NAME")
public class Lookup {

	@Id
	private String id;

	@Column(length = 100)
	private String name;	

	@Transient
	private String groupName;

	public Lookup(String id) {
		super();
		this.id = id;
	}

	public Lookup(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Lookup(String id , String name, Class group) {
		super();
		// System.out.println(group+" "+group.getSimpleName());
		this.id = id;
		this.name = name;
		this.groupName = group.getSimpleName();
	}
}
