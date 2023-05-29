package br.inatel.billproject.impl;

import br.inatel.billproject.api.BillService;
import br.inatel.billproject.api.BillTO;
import br.inatel.billproject.api.ResponseBillList;
import br.inatel.billproject.api.ResponseMessageTO;
import br.inatel.billproject.bill.beans.BillBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.Response;


@RequestScoped
public class BillServiceImpl implements BillService {

    @EJB(lookup="ejb:billproject-ear-1.0/billproject-ejb-1.0/BillBean!br.inatel.billproject.bill.interfaces.BillBean")
    private BillBean billBean;

    @Override
    public String createBill(BillTO bill) {
        ResponseMessageTO response = billBean.create(bill);
        return Response.status(Response.Status.OK).entity(response).build().toString();
    }

    @Override
    public String updateBill(String id, BillTO bill) {
        ResponseMessageTO response = billBean.update(bill);
        return Response.status(Response.Status.OK).entity(response).build().toString();
    }

    @Override
    public String listBills(String searchText, String minExpirationDate, String maxExpirationDate, String minPaidDate, String maxPaidDate) {
        ResponseBillList response = billBean.list(searchText, minExpirationDate, maxExpirationDate, minPaidDate, maxPaidDate);
        return Response.status(Response.Status.OK).entity(response).build().toString();
    }


    @Override
    public String deleteBill(String id) {
        ResponseMessageTO response = billBean.delete(id);
        return Response.status(Response.Status.OK).entity(response).build().toString();
    }
}
