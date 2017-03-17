CREATE TABLE IF NOT EXISTS %TABLE_NAME% (
    click_id int, 
    email_id int,
    link_id int,
    campaign_id int,
    total_click int,
    clicked_at timestamp,
    city_id int,
    state_id int,
    country_id int,
    mobile_id int, 
  
    PRIMARY KEY (click_id, email_id, clicked_at)
) WITH CLUSTERING ORDER BY (email_id DESC, clicked_at ASC)
   AND comment = 'This entity will store informations about all click actions.'
    AND gc_grace_seconds = 10000;