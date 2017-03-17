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
import br.com.allin.migration.core.dao.ClickDAO;
import br.com.allin.migration.core.entity.Click;

/**
 * DAOImpl class for Click to perform CRUD operation.
 * 
 * @author Patrick Handja
 */

@Component("ClickDAO")
public class ClickDAOImpl implements ClickDAO {

	private static final Logger log = LoggerFactory.getLogger(ClickDAOImpl.class);
	private static String tableName = "click";

	@Override
	public void insertClick(Click click) {
		Session session = null;

		try {
			session = ConnectionFactory.openSession();

			Insert insert = QueryBuilder.insertInto(tableName).value("click_id", click.getClickId())
					.value("email_id", click.getEmailId()).value("link_id", click.getLinkId())
					.value("campaign_id", click.getCampaignId()).value("total_click", click.getTotalClick())
					.value("clicked_at", click.getClickedAt()).value("city_id", click.getCityId())
					.value("state_id", click.getStateId()).value("country_id", click.getCountryId())
					.value("mobile_id", click.getMobileId());

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
	public Click getClick(int id) {
		Session session = null;

		try {
			session = ConnectionFactory.openSession();
			Click click = new Click();

			Statement select = QueryBuilder.select().all().from(tableName).where(QueryBuilder.eq("click_id", id));

			ResultSet result = session.execute(select);
			Row row = result.one();

			click.setClickId(row.getInt("click_id"));
			click.setEmailId(row.getInt("email_id"));
			click.setClickedAt(row.getTimestamp("clicked_at"));
			click.setCampaignId(row.getInt("campaign_id"));
			click.setCityId(row.getInt("city_id"));
			click.setStateId(row.getInt("state_id"));
			click.setCountryId(row.getInt("country_id"));
			click.setLinkId(row.getInt("link_id"));
			click.setTotalClick(row.getInt("total_click"));
			click.setMobileId(row.getInt("mobile_id"));

			session.close();

			return click;

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
	public Click updateClick(Click click) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteClick(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Click> getAllClicks() {
		// TODO Auto-generated method stub
		return null;
	}

}
