--ACT3

CREATE OR REPLACE TRIGGER "EMP_TRIGGER"
BEFORE INSERT OR UPDATE OR DELETE ON countries
BEGIN
IF TO_CHAR(SYSDATE, 'DY') IN ('Sat', 'Sun') OR
TO_CHAR(SYSDATE, 'HH24:MI') NOT BETWEEN '07:00' AND '17:00' THEN
RAISE_APPLICATION_ERROR(-20105, 'You can only do data manipulation on Employees table during business hours');
END IF;
END;