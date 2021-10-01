package com.ta.entity.lookup;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("ResourceType")
@NoArgsConstructor
public class ResourceType extends Lookup {
	public ResourceType(String id) {
		super(id);
	}
}
