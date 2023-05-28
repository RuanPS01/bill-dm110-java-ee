package br.inatel.billproject.bill.beans;

import javax.ejb.Stateless;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

@Stateless
public class MongoConnection {
    private MongoClient client;
    private MongoDatabase database;
    
    public MongoConnection() {
        String connectionString = "mongodb://localhost:27017/seu_banco_de_dados";
        MongoClientURI uri = new MongoClientURI(connectionString);
        client = new MongoClient(uri);
        database = client.getDatabase(uri.getDatabase());
    }
    
    // Métodos para realizar operações no banco de dados...
    
    public void close() {
        client.close();
    }
}
