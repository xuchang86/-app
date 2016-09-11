SELECT * FROM 
T_PUBLISH_ACTIVITY t1
LEFT JOIN T_ACTIVITY_PERSON t2 on t1.id = t2.activity_id
LEFT JOIN T_BASE_PERSON t3 on t3.id = t2.person_id
LEFT JOIN T_LOGIN_USER t4 on t3.user_id = t4.id