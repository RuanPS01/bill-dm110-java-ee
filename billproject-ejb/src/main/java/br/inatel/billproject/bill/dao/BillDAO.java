package br.inatel.billproject.bill.dao;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.bson.Document;
import org.bson.types.ObjectId;

import br.inatel.billproject.bill.beans.MongoConnectionBean;
import br.inatel.billproject.bill.entities.Bill;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Stateless
public class BillDAO {
    @EJB
    private MongoConnectionBean mongoDbConnection;

    public Boolean save(Bill bill) {
        try {
            MongoDatabase database = mongoDbConnection.getDatabase();
            MongoCollection<Document> collection = database.getCollection("bill");

            Document billDocument = new Document();
            billDocument.put("bill_code", bill.getBill_code());
            billDocument.put("description", bill.getDescription());
            billDocument.put("value", bill.getValue());
            billDocument.put("expiration_date", bill.getExpiration_date());
            billDocument.put("paid_date", bill.getPaid_date());

            collection.insertOne(billDocument);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean edit(Bill bill) {
        try {
            MongoDatabase database = mongoDbConnection.getDatabase();
            MongoCollection<Document> collection = database.getCollection("bill");

            Document filter = new Document("_id", bill.getId());
            Document update = new Document("$set", new Document("bill_code", bill.getBill_code())
                    .append("description", bill.getDescription())
                    .append("value", bill.getValue())
                    .append("expiration_date", bill.getExpiration_date())
                    .append("paid_date", bill.getPaid_date()));

            collection.updateOne(filter, update);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Bill> find(String searchText, String minExpirationDate, String maxExpirationDate, String minPaidDate, String maxPaidDate) {
        try {
            MongoDatabase database = mongoDbConnection.getDatabase();
            MongoCollection<Document> collection = database.getCollection("bill");

            Document query = new Document();

            if (searchText != null && !searchText.isEmpty()) {
                Pattern pattern = Pattern.compile(Pattern.quote(searchText), Pattern.CASE_INSENSITIVE);
                Document textFilter = new Document();
                textFilter.append("$regex", pattern);
                query.append("$or", Arrays.asList(
                        new Document("bill_code", textFilter),
                        new Document("description", textFilter),
                        new Document("value", textFilter)
                ));
            }

            if (minExpirationDate != null && !minExpirationDate.isEmpty()) {
                Document expirationFilter = new Document();
                expirationFilter.append("$gt", minExpirationDate);
                query.append("expiration_date", expirationFilter);
            }

            if (maxExpirationDate != null && !maxExpirationDate.isEmpty()) {
                Document expirationFilter = new Document();
                expirationFilter.append("$lt", maxExpirationDate);
                query.append("expiration_date", expirationFilter);
            }

            if (minPaidDate != null && !minPaidDate.isEmpty()) {
                Document paidFilter = new Document();
                paidFilter.append("$gt", minPaidDate);
                query.append("paid_date", paidFilter);
            }

            if (maxPaidDate != null && !maxPaidDate.isEmpty()) {
                Document paidFilter = new Document();
                paidFilter.append("$lt", maxPaidDate);
                query.append("paid_date", paidFilter);
            }

            FindIterable<Document> result = collection.find(query);

            List<Bill> bills = new ArrayList<>();
            for (Document billDocument : result) {
                Bill bill = new Bill();
                bill.setId(billDocument.getObjectId("_id"));
                bill.setBill_code(billDocument.getString("bill_code"));
                bill.setDescription(billDocument.getString("description"));
                bill.setValue(billDocument.getDouble("value"));
                bill.setExpiration_date(billDocument.getString("expiration_date"));
                bill.setPaid_date(billDocument.getString("paid_date"));
                bills.add(bill);
            }
            return bills;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    public Bill findOne(ObjectId id) {
        try {
            MongoDatabase database = mongoDbConnection.getDatabase();
            MongoCollection<Document> collection = database.getCollection("bill");

            Document query = new Document("_id", id);
            Document billDocument = collection.find(query).first();

            if (billDocument != null) {
                Bill bill = new Bill();
                bill.setId(billDocument.getObjectId("_id"));
                bill.setBill_code(billDocument.getString("bill_code"));
                bill.setDescription(billDocument.getString("description"));
                bill.setValue(billDocument.getDouble("value"));
                bill.setExpiration_date(billDocument.getString("expiration_date"));
                bill.setPaid_date(billDocument.getString("paid_date"));
                return bill;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean deleteOne(ObjectId id) {
        try {
            MongoDatabase database = mongoDbConnection.getDatabase();
            MongoCollection<Document> collection = database.getCollection("bill");

            collection.deleteOne(Filters.eq("_id", id));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
