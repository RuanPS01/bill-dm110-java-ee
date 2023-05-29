package br.inatel.billproject.bill.beans;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.json.JSONObject;

import br.inatel.billproject.bill.entities.Audit;

@Stateless
public class AuditMessageSender {

	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(lookup = "java:/jms/queue/billqueue")
	private Queue queue;

	public void sendTextMessage(Audit audit) {
		try {
			Connection conn = connectionFactory.createConnection();
			Session session = conn.createSession();
			MessageProducer msgProducer = session.createProducer(queue);
			TextMessage txtMsg = session.createTextMessage(toJsonString(audit));
			msgProducer.send(txtMsg);
		} catch (JMSException e) {
			System.out.println("Erro enviando mensagem: " + toJsonString(audit));
			throw new RuntimeException(e);
		}
	}
	
	public String toJsonString(Audit audit) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", audit.getId().toString());
        jsonObject.put("register_code", audit.getRegister_code());
        jsonObject.put("operation", audit.getOperation());
        jsonObject.put("timestamp", audit.getTimestamp());

        return jsonObject.toString();
    }
}
