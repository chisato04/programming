--ACTIVITY1
DECLARE
v_num NUMBER := :sv_user_num;
v_num_flag NUMBER;
v_result VARCHAR(50);

BEGIN
v_num_flag := MOD(v_num, 4);
v_result:=

CASE v_num_flag
WHEN 0 THEN v_num ||'Multiple of 4!'
WHEN 1 THEN v_num ||'Number gives a remainder of 1!'
WHEN 2 THEN v_num ||'Number gives a remainder of 2!'
ELSE v_num || ' Number gives a remainder of 3!'

END;
DBMS_OUTPUT.PUT_LINE(v_result);
DBMS_OUTPUT.PUT_LINE('Done');
END;

--ACTIVITY2
DECLARE
v_num NUMBER := :sv_user_num;

BEGIN
v_num:= MOD(v_num,4);
CASE
WHEN MOD(v_num,4)= 0 THEN
DBMS_OUTPUT.PUT_LINE(v_num||' Multiple of 4');
WHEN MOD(v_num,4)= 1 THEN
DBMS_OUTPUT.PUT_LINE(v_num|| ' number gives a remainder of 1');
WHEN MOD(v_num,4)= 2 THEN
DBMS_OUTPUT.PUT_LINE(v_num|| ' number gives a remainder of 1');
WHEN MOD(v_num,4)= 3 THEN
DBMS_OUTPUT.PUT_LINE(v_num|| ' number gives a remainder of 1');
ELSE
DBMS_OUTPUT.PUT_LINE(v_num|| ' number is a whole number');
END CASE;
DBMS_OUTPUT.PUT_LINE('DONE');
END;

--ACTIVITY3
DECLARE
 v_letter CHAR(50) := :sv_letter;

BEGIN
IF(v_letter >= 'A' AND v_letter <='Z')

THEN
DBMS_OUTPUT.PUT_LINE('This is a capital letter: '||v_letter);
ELSIF(v_letter >= 'a' AND v_letter <='z')
THEN
DBMS_OUTPUT.PUT_LINE('This is a small letter: '||v_letter);
IF v_letter BETWEEN '0' and '9' THEN
DBMS_OUTPUT.PUT_LINE('This is a number: '|| v_letter);
ELSE
DBMS_OUTPUT.PUT_LINE('This is not a number: '|| v_letter);
END IF;
END IF;
END;

--ACTIVITY4
DECLARE
d_number_01 NUMBER := ROUND(:d_number_01,2);
s_message VARCHAR2(15);
BEGIN
IF d_number_01>=0 AND d_number_01<13 THEN
s_message :='Child';
ELSIF d_number_01>=13 AND d_number_01<20 THEN
s_message :='Teenager';
ELSIF d_number_01>=20 AND d_number_01<40 THEN
s_message :='Adult';
ELSIF d_number_01>=40 AND d_number_01<60 THEN
s_message :='Middle age';
ELSIF d_number_01 >= 60 THEN
s_message :='Senior Citizen';
ELSE

--ACTIVITY5
DECLARE
v_date DATE:= TO_DATE(:sv_user_date,'DD//Mon/yyyy');
v_day VARCHAR2(15);
v_time VARCHAR2(15);
v_name VARCHAR2(30):= :Student_Name;
v_no VARCHAR2(30):= :Student_Number;
v_prel VARCHAR(30):= :Prelim;
v_mid VARCHAR2(30):= :Midterm;
v_fin VARCHAR2(30):= :Finals;
v_at VARCHAR2(30):= :Attendance;
v_clas VARCHAR2(30):= :Class_Standing;
v_ave NUMBER:=((v_prel)+(v_mid)+(v_fin)+(v_at)+(v_clas))/5;
BEGIN
v_day:=RTRIM(TO_CHAR(v_date,'DAY'));
DBMS_OUTPUT.PUT_LINE(v_date||' - is the first '||v_day||' of the year');
v_time:=TO_CHAR(SYSDATE,'HH24:MI;SS');
DBMS_OUTPUT.PUT_LINE('Current time is: '||v_time);
IF v_day IN('SATURDAY','SUNDAY') THEN
DBMS_OUTPUT.PUT_LINE(v_date||' - falls during weekends');
ELSE
DBMS_OUTPUT.PUT_LINE(v_date||' - falls during weekdays');
END IF;
DBMS_OUTPUT.PUT_LINE('Student Name:'||v_name);
DBMS_OUTPUT.PUT_LINE('Student Number:'||v_no);
DBMS_OUTPUT.PUT_LINE('Prelim:'||v_prel);
DBMS_OUTPUT.PUT_LINE('Midterm:'||v_mid);
DBMS_OUTPUT.PUT_LINE('Finals:'||v_fin);
DBMS_OUTPUT.PUT_LINE('Attendance:'||v_at);
DBMS_OUTPUT.PUT_LINE('Class Standing:'||v_clas);
DBMS_OUTPUT.PUT_LINE('average:'||v_ave);
IF(v_ave >= 75) THEN
DBMS_OUTPUT.PUT_LINE('REMARKS: PASSED');
ELSE
DBMS_OUTPUT.PUT_LINE('REMARKS: Failed');
END IF;
END;