package br.inatel.billproject.bill.beans;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import org.bson.types.ObjectId;
import br.inatel.billproject.api.BillTO;
import br.inatel.billproject.api.ResponseBillList;
import br.inatel.billproject.api.ResponseMessageTO;
import br.inatel.billproject.bill.dao.BillDAO;
import br.inatel.billproject.bill.entities.Audit;
import br.inatel.billproject.bill.entities.Bill;
import br.inatel.billproject.bill.interfaces.BillLocal;



@Stateless
@Local(BillLocal.class)
public class BillBean implements BillLocal {

    @EJB
    private BillDAO billDAO;
    
    @EJB
    private AuditMessageSender auditting;

    @Override
    public ResponseMessageTO create(BillTO billTO) {
        Bill bill = convertToBill(billTO);
        boolean result = billDAO.save(bill);
        
        Audit auditLog = new Audit();
        auditLog.setRegister_code(bill.getBill_code());
        auditLog.setOperation("create");
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String isoString = now.format(formatter);
        auditLog.setTimestamp(isoString);
        auditting.sendTextMessage(auditLog);
        
        if (result) {
            return new ResponseMessageTO("Bill created successfully");
        } else {
            return new ResponseMessageTO("Failed to create bill");
        }
    }

    @Override
    public ResponseMessageTO update(BillTO billTO) {
        Bill bill = convertToBill(billTO);
        boolean result = billDAO.edit(bill);

        Audit auditLog = new Audit();
        auditLog.setRegister_code(bill.getBill_code());
        auditLog.setOperation("update");
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String isoString = now.format(formatter);
        auditLog.setTimestamp(isoString);
        auditting.sendTextMessage(auditLog);
        
        if (result) {
            return new ResponseMessageTO("Bill updated successfully");
        } else {
            return new ResponseMessageTO("Failed to update bill");
        }
    }

    @Override
    public ResponseBillList list(String searchText, String minExpirationDate, String maxExpirationDate, String minPaidDate, String maxPaidDate) {
        List<Bill> bills = billDAO.find(searchText, minExpirationDate, maxExpirationDate, minPaidDate, maxPaidDate);
        List<BillTO> billTOs = convertToBillTOList(bills);

        ResponseBillList response = new ResponseBillList();
        response.setBills(billTOs);

        return response;
    }
    
    @Override
    public BillTO findOne(String id) {
        ObjectId objectId = new ObjectId(id);
        Bill bill = billDAO.findOne(objectId);

        if (bill != null) {
            return convertToBillTO(bill);
        } else {
            return null;
        }
    }

    @Override
    public ResponseMessageTO delete(String id) {
        ObjectId objectId = new ObjectId(id);
        boolean result = billDAO.deleteOne(objectId);

        if (result) {
            return new ResponseMessageTO("Bill deleted successfully");
        } else {
            return new ResponseMessageTO("Failed to delete bill");
        }
    }

    private Bill convertToBill(BillTO billTO) {
        Bill bill = new Bill();
        if (billTO.getId() != null && !billTO.getId().isEmpty()) {
            bill.setId(new ObjectId(billTO.getId()));
        }
        bill.setBill_code(billTO.getBill_code());
        bill.setDescription(billTO.getDescription());
        bill.setValue(billTO.getValue());
        bill.setExpiration_date(billTO.getExpiration_date());
        bill.setPaid_date(billTO.getPaid_date());
        return bill;
    }
    
    private BillTO convertToBillTO(Bill bill) {
        BillTO billTO = new BillTO();
        billTO.setId(bill.getId().toString());
        billTO.setBill_code(bill.getBill_code());
        billTO.setDescription(bill.getDescription());
        billTO.setValue(bill.getValue());
        billTO.setExpiration_date(bill.getExpiration_date());
        billTO.setPaid_date(bill.getPaid_date());
        return billTO;
    }
    
    private List<BillTO> convertToBillTOList(List<Bill> bills) {
        List<BillTO> billTOs = new ArrayList<>();
        for (Bill bill : bills) {
            BillTO billTO = convertToBillTO(bill);
            billTOs.add(billTO);
        }
        return billTOs;
    }
}
