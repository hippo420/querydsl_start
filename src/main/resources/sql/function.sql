ELIMITER $$

-- 나이(age)에 따라 등급 반환
CREATE FUNCTION get_grade(p_age INT)
    RETURNS VARCHAR(2)
    DETERMINISTIC
BEGIN
    DECLARE v_grade VARCHAR(2);
    IF p_age >= 40 THEN
        SET v_grade = 'A';
    ELSEIF p_age >= 30 THEN
        SET v_grade = 'B';
ELSE
        SET v_grade = 'C';
END IF;
RETURN v_grade;
END$$

-- 세금(10%) 공제 후 급여 반환
CREATE FUNCTION calc_taxed_salary(p_salary DECIMAL(15,2))
    RETURNS DECIMAL(15,2)
    DETERMINISTIC
BEGIN
RETURN p_salary * 0.9;
END$$

DELIMITER ;