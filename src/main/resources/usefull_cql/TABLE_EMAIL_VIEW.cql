CREATE TABLE IF NOT EXISTS %TABLE_NAME% (
	id int, 
  	email_id int,
	campaign_id int,
	opened_at timestamp,
	city_id int,
  	state_id int,
  	country_id int,
 	mobile_id int, 
  
  PRIMARY KEY (id, email_id, opened_at)
) WITH CLUSTERING ORDER BY (email_id DESC, opened_at ASC)
   AND comment = 'This entity will store informations about all email opened.'
    AND gc_grace_seconds = 10000;