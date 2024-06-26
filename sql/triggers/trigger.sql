CREATE OR REPLACE TRIGGER row_del
AFTER INSERT OR UPDATE OR DELETE
ON countries
FOR EACH ROW
DECLARE
vMsg VARCHAR2(50) := 'You have successfully fired a trigger!';
BEGIN
IF INSERTING THEN
dbms_output.put_line(vMsg || 'On Insert');
ELSIF UPDATING THEN
dbms_output.put_line(vMsg || 'On Update');
ELSIF DELETING THEN
dbms_output.put_line(vMsg || 'On Delete');
END IF;
END row_del;
