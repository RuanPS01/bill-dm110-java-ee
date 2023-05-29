package br.inatel.billproject.bill.dao;

import javax.ejb.Stateless;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

@Stateless
public class MongoConnection {
    private MongoClient client;
    private MongoDatabase database;
    
    public MongoConnection() {
        String connectionString = "mongodb+srv://ruanps:lGcKkaZtqJ12zSlf@cluster0.dd3eiaw.mongodb.net/bill-project";
        MongoClientURI uri = new MongoClientURI(connectionString);
        client = new MongoClient(uri);
        setDatabase(client.getDatabase(uri.getDatabase()));
    }
    
    public void close() {
        client.close();
    }

	public MongoDatabase getDatabase() {
		return database;
	}

	public void setDatabase(MongoDatabase database) {
		this.database = database;
	}
}
