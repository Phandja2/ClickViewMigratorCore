package br.com.allin.migration.core.entity;

import java.io.Serializable;
import java.util.Date;

public class Click implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer clickId;
	private Integer emailId;
	private Integer linkId;
	private Integer campaignId;
	private Integer totalClick;
	private Date clickedAt;
	private Integer cityId;
	private Integer stateId;
	private Integer countryId;
	private Integer mobileId;

	public Integer getClickId() {
		return clickId;
	}

	public void setClickId(Integer clickId) {
		this.clickId = clickId;
	}

	public Integer getEmailId() {
		return emailId;
	}

	public void setEmailId(Integer emailId) {
		this.emailId = emailId;
	}

	public Integer getLinkId() {
		return linkId;
	}

	public void setLinkId(Integer linkId) {
		this.linkId = linkId;
	}

	public Integer getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Integer campaignId) {
		this.campaignId = campaignId;
	}

	public Integer getTotalClick() {
		return totalClick;
	}

	public void setTotalClick(Integer totalClick) {
		this.totalClick = totalClick;
	}

	public Date getClickedAt() {
		return clickedAt;
	}

	public void setClickedAt(Date clickedAt) {
		this.clickedAt = clickedAt;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getMobileId() {
		return mobileId;
	}

	public void setMobileId(Integer mobileId) {
		this.mobileId = mobileId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
