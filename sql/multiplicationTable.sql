DECLARE
I number;
J number;

BEGIN
FOR I IN 1..25 LOOP
FOR J IN 1..25 LOOP
DBMS_OUTPUT.PUT(I*J || ' '||' ');
END LOOP;
DBMS_OUTPUT.PUT_LINE('');
END LOOP;
END;