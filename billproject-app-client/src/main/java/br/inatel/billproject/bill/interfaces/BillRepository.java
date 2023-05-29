package br.inatel.billproject.bill.interfaces;

import javax.ejb.Local;

import br.inatel.billproject.api.BillTO;
import br.inatel.billproject.api.ResponseBillList;
import br.inatel.billproject.api.ResponseMessageTO;

@Local
public interface BillRepository {
    ResponseMessageTO create(BillTO billTO);
    ResponseMessageTO update(BillTO billTO);
    ResponseBillList list(String searchText, String minExpirationDate, String maxExpirationDate, String minPaidDate, String maxPaidDate);
    BillTO findOne(String id);
    ResponseMessageTO delete(String id);
}
