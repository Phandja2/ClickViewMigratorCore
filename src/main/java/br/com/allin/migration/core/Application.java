package br.com.allin.migration.core;

import br.com.allin.migration.core.dao.ClickDAO;
import br.com.allin.migration.core.dao.impl.ClickDAOImpl;
import br.com.allin.migration.core.entity.Click;

public class Application {
	public static void main(String[] args) {
		/*EmailView v = new EmailView();
		EmailViewDAO vInter = new EmailViewDAOImpl();*/
				
		Click c = new Click();
		ClickDAO cInter = new ClickDAOImpl();
		
		System.out.println(cInter.getClick(12).getEmailId());
		
		
	}

}
