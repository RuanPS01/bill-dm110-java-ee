package br.inatel.billproject.bill.entities;

import org.bson.types.ObjectId;

public class Bill {
	private ObjectId id;
	private String bill_code;
    private String description;
    private double value;
    private String expiration_date;
    private String paid_date;
    
    public Bill() {
    	// Construtor vazio (obrigatório para o mapeamento do MongoDB)
    }
    
    public ObjectId getId() {
        return id;
    }
    
    public void setId(ObjectId id) {
        this.id = id;
    }

	public String getBill_code() {
		return bill_code;
	}

	public void setBill_code(String bill_code) {
		this.bill_code = bill_code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getExpiration_date() {
		return expiration_date;
	}

	public void setExpiration_date(String expiration_date) {
		this.expiration_date = expiration_date;
	}

	public String getPaid_date() {
		return paid_date;
	}

	public void setPaid_date(String paid_date) {
		this.paid_date = paid_date;
	}
}
