package br.inatel.billproject.bill.dao;

import javax.ejb.Stateless;

import org.bson.Document;
import br.inatel.billproject.bill.entities.Audit;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Stateless
public class AuditDAO {
    private MongoConnection mongoDbConnection = new MongoConnection();

    public Boolean save(Audit audit) {
        try {
            MongoDatabase database = mongoDbConnection.getDatabase();
            MongoCollection<Document> collection = database.getCollection("auditing");

            Document autitDocument = new Document();
            autitDocument.put("register_code", audit.getRegister_code());
            autitDocument.put("operation", audit.getOperation());
            autitDocument.put("timestamp", audit.getTimestamp());

            collection.insertOne(autitDocument);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
