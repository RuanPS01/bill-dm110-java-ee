package br.inatel.billproject.api;

import java.util.List;

public class ResponseBillList {
    private List<BillTO> bills;

    public List<BillTO> getBills() {
        return bills;
    }

    public void setBills(List<BillTO> bills) {
        this.bills = bills;
    }
}