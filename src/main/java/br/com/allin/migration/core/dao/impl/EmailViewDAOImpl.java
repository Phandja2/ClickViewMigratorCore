package br.com.allin.migration.core.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;

import br.com.allin.migration.core.config.cassandra.ConnectionFactory;
import br.com.allin.migration.core.dao.EmailViewDAO;
import br.com.allin.migration.core.entity.EmailView;

/**
 * DAOImpl class for Employee to perform CRUD operation.
 * 
 * @author Patrick Handja
 */

@Component("ViewDAO")
public class EmailViewDAOImpl implements EmailViewDAO {

	private static final Logger log = LoggerFactory.getLogger(EmailViewDAOImpl.class);

	private static String tableName = "emailview";

	@Override
	public void insertEmailView(EmailView emailView) {
		Session session = null;

		try {
			session = ConnectionFactory.openSession();

			Insert insert = QueryBuilder.insertInto(tableName).value("id", emailView.getViewId())
					.value("email_id", emailView.getEmailId()).value("campaign_id", emailView.getCampaignId())
					.value("opened_at", emailView.getOpenedAt()).value("city_id", emailView.getCityId())
					.value("state_id", emailView.getStateId()).value("country_id", emailView.getCountryId())
					.value("mobile_id", emailView.getMobileId());

			session.execute(insert.toString());
			session.close();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public EmailView getEmailView(int id) {
		Session session = null;

		try {
			session = ConnectionFactory.openSession();
			EmailView emailView = new EmailView();

			Statement select = QueryBuilder.select().all().from(tableName).where(QueryBuilder.eq("id", id));

			ResultSet result = session.execute(select);
			Row row = result.one();

			emailView.setViewId(row.getInt("id"));
			emailView.setEmailId(row.getInt("email_id"));
			emailView.setOpenedAt(row.getTimestamp("opened_at"));
			emailView.setCampaignId(row.getInt("campaign_id"));
			emailView.setCityId(row.getInt("city_id"));
			emailView.setStateId(row.getInt("state_id"));
			emailView.setCountryId(row.getInt("country_id"));
			emailView.setMobileId(row.getInt("mobile_id"));

			session.close();

			return emailView;

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return null;
	}

	@Override
	public EmailView updateEmailView(EmailView emailView) {
		Session session = null;
		
		try {
			session = ConnectionFactory.openSession();
			
			Statement update = QueryBuilder.update(tableName).with(QueryBuilder.set("height", 180))
			        .and(QueryBuilder.set("width", 300)).where(QueryBuilder.eq("id", 5145924587302797538L));
			session.execute(update);
			
			session.close();
		} catch (Exception e) {
			// TODO: handle exception
		}  finally {
			if (session != null) {
				session.close();
			}
		}

		return null;
	}

	@Override
	public void deleteEmailView(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<EmailView> getAllEmailViews() {

		return null;
	}

}
