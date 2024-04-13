--ACTIVITY1
DECLARE
v_first_name varchar2(100);
v_last_name varchar2(100);
v_salary varchar2(100);
v_hire_date varchar2(100);

CURSOR cur_emp IS
SELECT FIRST_NAME, LAST_NAME, SALARY, HIRE_DATE from Employees WHERE LAST_NAME LIKE 'C%' AND HIRE_DATE >=
'10-Nov-95' AND SALARY >= 5000;

BEGIN
OPEN cur_emp;
WHILE cur_emp%ISOPEN
LOOP
FETCH cur_emp INTO v_first_name, v_last_name, v_salary, v_hire_date;
EXIT WHEN cur_emp%NOTFOUND;

dbms_output.put_line('First name: ' || v_first_name);
dbms_output.put_line('Last name: ' || v_last_name);
dbms_output.put_line('Hire date: ' || v_hire_date);
dbms_output.put_line('Salary is: ' || v_salary);

END LOOP;

EXCEPTION
WHEN NO_DATA_FOUND THEN
dbms_output.put_line('Data not found.');
END;

--ACTIVITY2
OPEN cur_emp;
FOR counter IN 1..3
LOOP

--ACTIVITY3
BEGIN
OPEN cur_emp;
LOOP
FETCH cur_emp INTO v_first_name, v_last_name, v_salary, v_hire_date;
EXIT WHEN cur_emp%NOTFOUND;

--ACTIVITY4
DECLARE
v_manager_id varchar2(35);
v_job_id varchar2(35);
v_last_name varchar2(35);
v_salary varchar2(35);
v_hire_date varchar2(35);
v_employee_id varchar2(35);
v_department_id varchar2(35);
total number(5);
total1 number(10);
CURSOR cur_emp IS
SELECT salary, employee_id, last_name, hire_date, job_id, department_id, manager_id FROM employees
WHERE manager_id >= 103 AND department_id > 30 AND job_id LIKE 'ST_CLERK';

BEGIN
SELECT count(*) as total into total FROM employees
WHERE manager_id >= 103 AND department_id > 30 AND job_id LIKE 'ST_CLERK';
SELECT AVG(salary) as total1 into total1 FROM employees
WHERE manager_id >= 103 AND department_id > 30 AND job_id LIKE 'ST_CLERK';
OPEN cur_emp;
FOR i in 1..20
LOOP

FETCH cur_emp INTO v_salary, v_employee_id, v_last_name, v_hire_date, v_job_id, v_department_id,
v_manager_id;
EXIT WHEN cur_emp%NOTFOUND;
DBMS_OUTPUT.PUT_LINE('Record: ' || TO_CHAR(cur_emp%ROWCOUNT));
DBMS_OUTPUT.PUT_LINE('Employee ID: ' || v_employee_id);
DBMS_OUTPUT.PUT_LINE('Last Name: ' || v_last_name);
DBMS_OUTPUT.PUT_LINE('Salary: ' || v_salary);
DBMS_OUTPUT.PUT_LINE('Hire Date: ' || v_hire_date);
DBMS_OUTPUT.PUT_LINE('Job ID: ' || v_job_id);
DBMS_OUTPUT.PUT_LINE('Department ID: ' || v_department_id);
DBMS_OUTPUT.PUT_LINE('----------------------------------------------------');
END LOOP;
DBMS_OUTPUT.PUT_LINE('Total records returned: ' || total);
DBMS_OUTPUT.PUT_LINE('Average Salary: ' || total1);
EXCEPTION
WHEN NO_DATA_FOUND THEN
DBMS_OUTPUT.PUT_LINE('DATA NOT FOUND');
END;

--ACTIVITY5
DECLARE
CURSOR c_employees IS
SELECT salary, employee_id, FIRST_NAME FROM Employees WHERE SALARY < 3500;
vr_salary number;
BEGIN
FOR salary_rec in c_employees
LOOP
vr_salary := salary_rec.Salary+(salary_rec.Salary*0.05);

UPDATE employees set salary = vr_salary WHERE employee_id = salary_rec.employee_id;
dbms_output.put_line('---------------------------');
dbms_output.put_line('Record No ' ||TO_CHAR(c_employees%ROWCOUNT));
dbms_output.put_line('');
dbms_output.put_line('Employee ID: ' || salary_rec.employee_id);
dbms_output.put_line('Old Salary: PHP' || salary_rec.salary);
dbms_output.put_line('New Salary: PHP' || vr_salary);
dbms_output.put_line('---------------------------');
END LOOP;
COMMIT;
END;

--fixed