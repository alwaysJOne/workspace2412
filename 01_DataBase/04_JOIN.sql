/*
    <JOIN>
    두개 이상의 테이블에서 데이터를 조회하고자 할 때 사용되는 구문
    조회 결과는 하나의 결과(RESULT SET)를 반환한다.
    
    관계형 데이터베이스에서는 최소한의 데이터를 각각의 테이블에 담고 있음
    (무작정 다 조회해 오는 것이 아니라 각 테이블간 연결고리(외래키)를 통해 데이터를 매칭시켜 조회해야한다.)

    JOIN은 크게 "오라클 전용구문" "ANSI 구문"
    
    [용어정리]
        오라클 전용구문        |       ANSI구문
    -------------------------------------------------------
          등가조인            |        내부조인
         (EQUAL JOIN)        |      (INNER JOIN)
    ----------------------------------------------------------
          포괄조인            |        외부조인
    ---------------------------------------------------------
        자체조인/비등가조인    |         
*/

--전체 사원들의 사번, 사원명, 부서코드, 부서명
SELECT EMP_ID, EMP_NAME, DEPT_CODE
FROM EMPLOYEE;

SELECT DEPT_ID, DEPT_TITLE
FROM DEPARTMENT;

SELECT EMP_ID, EMP_NAME, DEPT_CODE, DEPT_TITLE
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE = DEPT_ID;

/*
    1. 등가조인(EQUAL JOIN) / 내부조인(INNER JOIN)
    연결시키는 컬럼의 값이 일치하는 행들만 조회(일치하는 값이 없는 행은 조회 제외)
*/
-->오라클 전용구문
-- FROM절에 조회하고자하는 테이블을 나열(,구분)
-- WHERE절에 매칭시킬 컬럼에대한 조건을 제시

--1. 연결할 두 컬럼명이 다른 경우(EMPLOYEE : DEPT_CODE / DEPARTMENT : DEPT_ID)
--전체 사원들의 사번, 사원명, 부서코드, 부서명
SELECT *
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE = DEPT_ID;

--2. 연결할 두 컬럼명이 동일한 경우
--전체 사언들의 사번, 사원명, 직급코드, 직급명
SELECT EMP_ID, EMP_NAME, J.JOB_CODE, JOB_NAME
FROM EMPLOYEE E, JOB J
WHERE E.JOB_CODE = J.JOB_CODE;

--추가적인 조건제시
--직급이 대리인 사원의 사번, 사원명, 직급명, 급여조회
SELECT EMP_ID, EMP_NAME, JOB_NAME, SALARY
FROM EMPLOYEE E, JOB J
WHERE E.JOB_CODE = J.JOB_CODE
  AND JOB_NAME = '대리';
  
  
-->ANSI구문
--FROM절에 기준이되는 테이블 하나만 기술한다.
--JOIN절에 같이 조인하고자하는 테이블 기술 + 매칭시킬 컬럼에대한 조건
--[INNER] JOIN USING / JOIN ON

--1. 연결할 두 컬럼명이 다른 경우(EMPLOYEE : DEPT_CODE / DEPARTMENT : DEPT_ID)
--전체 사원들의 사번, 사원명, 부서코드, 부서명
SELECT EMP_ID, EMP_NAME, DEPT_CODE, DEPT_TITLE
FROM EMPLOYEE
JOIN DEPARTMENT ON(DEPT_CODE = DEPT_ID);

--2. 연결할 두 컬럼명이 동일한 경우
--전체 사언들의 사번, 사원명, 직급코드, 직급명
SELECT EMP_ID, EMP_NAME, JOB_CODE, JOB_NAME
FROM EMPLOYEE
JOIN JOB USING(JOB_CODE);

--추가적인 조건제시
--직급이 대리인 사원의 사번, 사원명, 직급명, 급여조회
SELECT EMP_ID, EMP_NAME, JOB_NAME, SALARY
FROM EMPLOYEE
INNER JOIN JOB USING(JOB_CODE)
WHERE JOB_NAME = '대리';

--========================= 실습 ===========================
--1. 부서가 인사관리부인 사원들의 사번, 이름, 보너스 조회
SELECT EMP_ID, EMP_NAME, BONUS
FROM EMPLOYEE
JOIN DEPARTMENT ON(DEPT_CODE = DEPT_ID)
WHERE DEPT_TITLE = '인사관리부';

--2. DEPARTMENT와 LOCATION테이블을 참고하여 전체 부서의
--      부서코드, 부서명, 지역코드, 지역명 조회
SELECT DEPT_ID, DEPT_TITLE, NATIONAL_CODE, LOCAL_NAME
FROM DEPARTMENT
JOIN LOCATION ON(LOCAL_CODE = LOCATION_ID);

--3. 보너스를 받는 사원들의 사번, 사원명, 보너스, 부서명 조회
SELECT EMP_ID, EMP_NAME, BONUS, DEPT_TITLE
FROM EMPLOYEE
JOIN DEPARTMENT ON(DEPT_CODE = DEPT_ID)
WHERE BONUS IS NOT NULL;

--4. 부서가 총무부가 아닌 사원들의 사원명, 급여조회
SELECT EMP_NAME, SALARY
FROM EMPLOYEE
JOIN DEPARTMENT ON(DEPT_CODE = DEPT_ID)
WHERE DEPT_TITLE != '총무부';


------------------------------------------------------------------
/*
    2. 포괄조인 / 외부조인(OUTER JOIN)
    두 테이블간의 JOIN시 일치하지 않는 행도 포함시켜 조회가능
    단, 반드시 LEFT/RIGHT를 지정해야됩니다.(어떤테이블이 기준이냐)
*/

--외부조인과 비교할만한 내부조인
-- 사원명, 부서명, 급여, 연봉
-- 부서배치를 받지않은 2명의 사원정보가 누락
SELECT EMP_NAME, DEPT_TITLE, SALARY, SALARY * 12
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID);

--1) LEFT [OUTER] JOIN : 두 테이블 중 왼편에 기술된 테이블을 기준으로 JOIN
SELECT EMP_NAME, DEPT_TITLE, SALARY, SALARY * 12
FROM EMPLOYEE
LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID);

--오라클구문
SELECT EMP_NAME, DEPT_TITLE, SALARY, SALARY * 12
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE = DEPT_ID(+);

--2) RIGHT [OUTER] JOIN : 두 테이블 중 오른편에 기술된 테이블을 기준으로 JOIN
SELECT EMP_NAME, DEPT_TITLE, SALARY, SALARY * 12
FROM EMPLOYEE
RIGHT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID);

--오라클구문
SELECT EMP_NAME, DEPT_TITLE, SALARY, SALARY * 12
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE(+) = DEPT_ID;

-- FULL [OUTER] JOIN : 두 테이블이 가진 모든 행을 조회할 수 있다.
SELECT EMP_NAME, DEPT_TITLE, SALARY, SALARY * 12
FROM EMPLOYEE
FULL JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID);

/*
    3. 비등가 조인
    매칭시킬 컬럼에 대한 조건 작성시 '='를 사용하지 않는 조인문
    ANSI구문 사용시 JOIN ON만 사용가능
    
    범위(수치적 범위, 기간적 범위)기반조인을 구현할 때 주로 사용한다.
*/

--사원명, 급여, 급여레벨 조회
--ANSI
SELECT EMP_NAME, SALARY, SAL_LEVEL
FROM EMPLOYEE
JOIN SAL_GRADE ON (SALARY >= MIN_SAL AND SALARY <= MAX_SAL);

--오라클
SELECT EMP_NAME, SALARY, SAL_LEVEL
FROM EMPLOYEE, SAL_GRADE
WHERE SALARY BETWEEN MIN_SAL AND MAX_SAL;
--=------------------------------------------------------------------------
/*
    4.자체조인(SELF JOIN)
    같은 테이블을 다시한번 조인하는 경우
    - 하나의 테이블에서 서로 다른 두개의 행을 비교해야하는 경우 사용
    - 동일한 테이블을 조인하는 것이기 때문에 별칭을 사용해서 구분해야한다.
*/

--전체 사원의 사원사번, 사원명, 사원부서코드  --> EMPLOYEE E
--          , 사수사번, 사수의사원명, 사수의부서코드 --> EMPLOYEE M
--ANSI
SELECT E.EMP_ID, E.EMP_NAME, E.DEPT_CODE,
        M.EMP_ID, M.EMP_NAME, M.DEPT_CODE
FROM EMPLOYEE E
LEFT JOIN EMPLOYEE M ON (E.MANAGER_ID = M.EMP_ID);

--오라클
SELECT E.EMP_ID, E.EMP_NAME, E.DEPT_CODE,
        M.EMP_ID, M.EMP_NAME, M.DEPT_CODE
FROM EMPLOYEE E, EMPLOYEE M
WHERE E.MANAGER_ID = M.EMP_ID(+);

---------------------------------------------------------------
/*
    <다중조인>
    2개이상의 테이블을 가지고 JOIN할 수 있다.
*/
--사번, 사원명, 부서명, 직급명
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME
FROM EMPLOYEE
JOIN DEPARTMENT ON(DEPT_CODE = DEPT_ID)
JOIN JOB USING(JOB_CODE);

--오라클
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME
FROM EMPLOYEE E, DEPARTMENT D, JOB J
WHERE E.DEPT_CODE = D.DEPT_ID
  AND E.JOB_CODE = J.JOB_CODE;

--사번, 사원명, 부서명, 지역명 조회
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, LOCAL_NAME
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN LOCATION ON (LOCATION_ID = LOCAL_CODE);

-- 사번, 사원명, 부서명, 직급명, 지역명, 국가명, 급여등급 조회
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME, 
        LOCAL_NAME, NATIONAL_NAME, SAL_LEVEL
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN LOCATION ON (LOCATION_ID = LOCAL_CODE)
JOIN JOB USING(JOB_CODE)
JOIN NATIONAL USING(NATIONAL_CODE)
JOIN SAL_GRADE ON (SALARY BETWEEN MIN_SAL AND MAX_SAL);

------------------------------------------------------------------------------------------
/*
    내추럴조인(NATURAL JOIN)
    두 테이블 간에 공통된 컬럼이름을 자동으로 기준을 정해서 조인하는 방식
    ON이나 USING 조건을 지정하지 않아도 두 테이블간 공통된 컬럼을 기반으로 조인\
    
    동일한 컬럼이 있을 경우에만 정상적인 조인이 가능하며 명시적이지 않고 조인조건을 정확하게
    기술하기 어렵다.
*/

SELECT *
FROM EMPLOYEE
NATURAL JOIN JOB;



