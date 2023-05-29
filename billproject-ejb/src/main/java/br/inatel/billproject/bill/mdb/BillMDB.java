package br.inatel.billproject.bill.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import br.inatel.billproject.bill.dao.AuditDAO;
import br.inatel.billproject.bill.dao.BillDAO;
import br.inatel.billproject.bill.entities.Audit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", 
									propertyValue = "javax.jms.Queue"),

		@ActivationConfigProperty(propertyName = "destination", 
									propertyValue = "java:/jms/queue/billqueue"),

		@ActivationConfigProperty(propertyName = "maxSession", propertyValue = "5") })

public class BillMDB implements MessageListener {

	@Override
	public void onMessage(Message message) {
	    AuditDAO auditDAO  = new AuditDAO();
		
		try {
			
			if (message instanceof TextMessage) {
				TextMessage txtMessage = (TextMessage) message;

				String text = txtMessage.getText();

				System.out.println("Iniciando processamento da mensagem...");
				Thread.sleep(1000);
				System.out.println("Mensagem recebida por BillMDB: " + text);
				System.out.println("Salvando log no banco: ");
		        ObjectMapper mapper = new ObjectMapper();
		        Audit audit;
				try {
					audit = mapper.readValue(text, Audit.class);
					auditDAO.save(audit);
					System.out.println("Finalizando processamento da mensagem...");
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			}
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
