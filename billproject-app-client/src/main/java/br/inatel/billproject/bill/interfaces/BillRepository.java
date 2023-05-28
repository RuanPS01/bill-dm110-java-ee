package br.inatel.billproject.bill.interfaces;

import br.inatel.billproject.api.BillTO;
import br.inatel.billproject.api.ResponseBillList;
import br.inatel.billproject.api.ResponseMessageTO;

public interface BillRepository {
	ResponseMessageTO create(BillTO bill);
	ResponseMessageTO update(BillTO bill);
	ResponseBillList list();
	ResponseMessageTO delete(String id);
}
