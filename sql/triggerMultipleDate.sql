--ACT4

CREATE OR REPLACE TRIGGER "RADz"
BEFORE DELETE
ON countries
FOR EACH ROW
DECLARE
day
VARCHAR(20);
BEGIN
day := RTRIM(TO_CHAR(SYSDATE,'DAY'));

IF_DAY = 'MONDAY' OR DAY 'TUESDAY OR DAY = 'WEDNESDAY' OR (TO_CHAR(SYSDATE, 'HH24:MI') NOT BETWEEN '5:00' AND '19:00')
THEN
raise_application_error (-20105, 'You can only do data manipulation on Employees during business hours, Thursdays to Sundays');
END IF;
END "RADz"; 