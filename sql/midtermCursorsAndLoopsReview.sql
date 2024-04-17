DECLARE
CURSOR YIname_emp IS
SELECT employee_id, last_name, first_name, salary
FROM employees
WHERE ROWNUM <= 10 FOR UPDATE OF salary;
v_current_time VARCHAR2(20);
v_current_day VARCHAR2(20);
BEGIN
v_current_time := TO_CHAR(SYSDATE, 'HH24:MI:SS');
v_current_day := TO_CHAR(SYSDATE, 'Day');  
DBMS_OUTPUT.PUT_LINE('Current time is: ' || v_current_time);
DBMS_OUTPUT.PUT_LINE('Current day is: ' || RTRIM(v_current_day));
DBMS_OUTPUT.PUT_LINE('***__________***__________***');
FOR rec IN YIname_emp LOOP
IF rec.salary < 30000 THEN
UPDATE employees SET salary = salary * 1.05 WHERE CURRENT OF YIname_emp;
ELSE
UPDATE employees SET salary = salary * 1.02 WHERE CURRENT OF YIname_emp;
END IF; 
DBMS_OUTPUT.PUT_LINE(' ');
DBMS_OUTPUT.PUT_LINE('Employees ID: ' || rec.employee_id);
DBMS_OUTPUT.PUT_LINE('Last name is: ' || rec.last_name);
DBMS_OUTPUT.PUT_LINE('First name is: ' || rec.first_name);
DBMS_OUTPUT.PUT_LINE('Old salary: ' || rec.salary);
DBMS_OUTPUT.PUT_LINE('New Salary: ' || CASE WHEN rec.salary < 30000 THEN rec.salary * 1.05 ELSE rec.salary * 1.02 END);
DBMS_OUTPUT.PUT_LINE('');
DBMS_OUTPUT.PUT_LINE('sir RAD...');
DBMS_OUTPUT.PUT_LINE('***__________***__________***');
END LOOP;  
DBMS_OUTPUT.PUT_LINE('You have successfully updated your database!');
END;

rollback;