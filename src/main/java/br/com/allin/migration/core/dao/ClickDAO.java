package br.com.allin.migration.core.dao;

import java.util.List;

import br.com.allin.migration.core.entity.Click;

public interface ClickDAO {
	
	/**
	 * Used to Create the Click Information
	 * 
	 * @param click
	 */
	public void insertClick(Click click);

	/**
	 * Getting the Click Information using Id
	 * 
	 * @param id
	 */
	public Click getClick(int id);

	/**
	 * Used to Update the Click Information
	 * 
	 * @param click
	 */

	public Click updateClick(Click click);

	/**
	 * Deleting the Click Information using Id
	 * 
	 * @param id
	 */
	public void deleteClick(int id);

	/**
	 * Getting the All Clicks information
	 */
	public List<Click> getAllClicks();
	
}
