package br.inatel.billproject.bill.entities;

import org.bson.types.ObjectId;

public class Audit {
	private ObjectId id;
	private String register_code;
	private String operation;
	private String timestamp;
	
    
    public Audit() {
    	// Construtor vazio (obrigatório para o mapeamento do MongoDB)
    }
    
    public ObjectId getId() {
        return id;
    }
    
    public void setId(ObjectId id) {
        this.id = id;
    }

	public String getRegister_code() {
		return register_code;
	}

	public void setRegister_code(String register_code) {
		this.register_code = register_code;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	
}
