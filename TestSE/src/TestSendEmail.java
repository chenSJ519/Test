import java.util.Properties;

import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.junit.Test;

public class TestSendEmail {

	@Test
	public void sendEmail(){
		Properties prop = new Properties();
		//设置邮件服务器
		prop.put("mail.smtp.host", "smtp.126.com");
		//设置是否开启验证，默认为false
		prop.put("mail.smtp.auth", "true");
		//设置传输协议
		prop.setProperty("mail.transport.protocol", "smtp");
		Session session = Session.getInstance(prop);
		//开启debug模式，可以监控邮件发送的状态
		session.setDebug(true);
		//创建Message对象
		MimeMessage message = new MimeMessage(session);
		try {
			//发件地址
			Address fromAddress = new InternetAddress("chenshengjvcsj@126.com");
			message.setFrom(fromAddress);
			Address toAddress = new InternetAddress("1264830070@qq.com");
			message.setRecipient(MimeMessage.RecipientType.TO, toAddress);
			//设置邮件主题
			message.setSubject("测试");
			//设置邮件正文
			message.setText("这是一封测试邮件");
			//通过session对象获取Transport对象
			Transport transport = session.getTransport();
			//取得连接
			transport.connect("smtp.126.com", "chenshengjvcsj@126.com", "");
			//发送
			transport.sendMessage(message,message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
