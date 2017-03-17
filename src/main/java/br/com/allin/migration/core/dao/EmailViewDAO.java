package br.com.allin.migration.core.dao;

import java.util.List;

import br.com.allin.migration.core.entity.EmailView;

public interface EmailViewDAO {
	/**
	 * Used to Create the View Information
	 * 
	 * @param emailView
	 */
	public void insertEmailView(EmailView emailView);

	/**
	 * Getting the View Information using Id
	 * 
	 * @param id
	 */
	public EmailView getEmailView(int id);

	/**
	 * Used to Update the View Information
	 * 
	 * @param emailView
	 */

	public EmailView updateEmailView(EmailView emailView);

	/**
	 * Deleting the View Information using Id
	 * 
	 * @param id
	 */
	public void deleteEmailView(int id);

	/**
	 * Getting the All Views information
	 */
	public List<EmailView> getAllEmailViews();

}
