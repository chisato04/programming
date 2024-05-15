DECLARE

first_name varchar2(25);
last_name varchar2(25);
manager_id number;
CURSOR cur_chief IS
SELECT first_name, last_name, department_name FROM employees e
INNER JOIN departments d ON d.manager_id = e.employee_id;
r_chief cur_chief%ROWTYPE;

BEGIN
OPEN cur_chief;
LOOP
FETCH cur_chief INTO r_chief;
EXIT WHEN cur_chief%NOTFOUND;

DBMS_OUTPUT.PUT_LINE(r_chief.department_name || ' - ' ||r_chief.first_name || ',' || r_chief.last_name);
END LOOP;
CLOSE cur_chief;
END;

