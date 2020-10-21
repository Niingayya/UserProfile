package com.modestack.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pepper")
public class Pepper {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="pepper_id")
	private int pepperId;
	
	@Column(name="pepper_name")
	private String pepperName;

	public int getPepperId() {
		return pepperId;
	}

	public void setPepperId(int pepperId) {
		this.pepperId = pepperId;
	}

	public String getPepperName() {
		return pepperName;
	}

	public void setPepperName(String pepperName) {
		this.pepperName = pepperName;
	}

	@Override
	public String toString() {
		return "Pepper [pepperId=" + pepperId + ", pepperName=" + pepperName + "]";
	}
	
}
