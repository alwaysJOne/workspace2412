--집합연산
--여러개의 select결과물을 하나의 쿼리로 만드는 연산자
--여러가지의 조건이 있을 때 그에 해당하는 여러개의 결과값을 결합시키고 싶을 때 사용

--UNION, INTERSECT, UNION ALL, MINUS


--UNION : 여러개의 쿼리를 하나의 결과로 합치는 연산자
--중복영역은 제외하고 하나로 합친다.

--부서코드가 'D2'인 사원 또는 'D6'인 사원의 이름, 부서코드 조회
SELECT DEPT_CODE, EMP_NAME
FROM EMPLOYEE
WHERE DEPT_CODE = 'D2' 
UNION
SELECT DEPT_CODE, EMP_NAME
FROM EMPLOYEE
WHERE DEPT_CODE = 'D6';

--INTERSECT : 여러개의 SELECT한 결과에서 공통 부분만 결과로 추출(교집합) -> AND

SELECT DEPT_CODE, EMP_NAME, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D6'
INTERSECT
SELECT DEPT_CODE, EMP_NAME, SALARY
FROM EMPLOYEE
WHERE SALARY > 3000000;

--UNION ALL: 여러개의 쿼리를 하나의 결과로 합치는 연산자
--중복영역을 포함해서 하나로 합친다.

SELECT DEPT_CODE, EMP_NAME, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5'
UNION ALL
SELECT DEPT_CODE, EMP_NAME, SALARY
FROM EMPLOYEE
WHERE SALARY > 3000000;

--MINUS : 처음 결과값에 두번째 쿼리의 결과값을 제외한 나머지 결과값(차집합)
SELECT DEPT_CODE, EMP_NAME, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5'
MINUS
SELECT DEPT_CODE, EMP_NAME, SALARY
FROM EMPLOYEE
WHERE SALARY > 3000000;

--집합연산자는 컬럼타입(문자, 숫자, 날짜)만 일치하면 연산수행이 가능하다.
SELECT EMP_NAME, SALARY
FROM EMPLOYEE
WHERE EMP_ID = 200
UNION
SELECT PHONE, 1000000
FROM EMPLOYEE
WHERE EMP_ID = 201
UNION
SELECT '홍길똥', 400000
FROM DUAL;


-------------------------------------------------------------------------\
-- 그룹함수
--1. ROLLUP
--ROLLUP은 계층적인 그룹화를 수행하며, GOROUP BY로 묶인 컬럼에 따라서 단계적을로 그룹을 집계함.
--일반적으로 요약데이터를 추출하는데 사용

-- GROUP BY ROLLUP(A) : 전체합계, 컬럼A에 따른 집계
-- GROUP BY ROLLUP(A, B) : 전체합계, 컬럼A에 따른 집계, 컬럼 (A, B)에 따른 집계 

--부서별, 전체 급여 합
SELECT DEPT_CODE, SUM(SALARY)
FROM EMPLOYEE
GROUP BY ROLLUP(DEPT_CODE);

--부서별, 부서와 직급별, 전체급여합
SELECT DEPT_CODE, JOB_CODE, SUM(SALARY)
FROM EMPLOYEE
GROUP BY ROLLUP(DEPT_CODE, JOB_CODE);


-- GROUP BY ROLLUP(A, B, C) : 전체합, 컬럼 A에따른 집계, 컬럼(A,B)에 대한집계, 컬럼(A,B,C)에 한 집계
-- GROUP BY ROLLUP(A,(B, C)) : 전체합, 컬럼 A에따른 집계, 컬럼(A,(B,C))에 한 집계
-- GROUP BY A, ROLLUP(B) : A그룹별, 전체합, B에따른 집계 

--2. CUBE
--조합가능한 모든 값에 대한 다차원 집계
-- GROUP BY CUBE(A) : 전체합계, 컬럼A대한 집계
-- GROUP BY CUBE(A, B) : 전체합계, 컬럼A대한 집계, 컬럼B에대한 집계, 컬럼 (A, B)에 대한 집계

SELECT DEPT_CODE, JOB_CODE, SUM(SALARY)
FROM EMPLOYEE
GROUP BY CUBE(DEPT_CODE, JOB_CODE);


-----------------------------------------------------------------
--1. 순위관련함수(RANK, DENSE_RANK, ROW_NUMBR)
--SQL에서 특정 값을 기준을로 순위를 매기는 함수들

--RANK() : 동순위 -> 건너뜀
--DENSE_RANK() : 동순위 -> 연속적을로 순위를 부여
--ROW_NUMBER() : 동순위 -> 무시하고 무조건 연속적으로

-- RANK() OVER(정렬기준)
--EX) 공동1위가 2명이면 다음은 3순위
SELECT RANK() OVER(ORDER BY SALARY DESC) 순위, EMP_NAME, SALARY
FROM EMPLOYEE;

-- DENSE_RANK() OVER(정렬기준)
--EX) 공동1위가 2명이어도 다음순위는 2위
SELECT DENSE_RANK() OVER(ORDER BY SALARY DESC) 순위, EMP_NAME, SALARY
FROM EMPLOYEE;

-- ROW_NUMBER() OVER(정렬기준)
--EX) 공동순위없이 무조건 연속적을로
SELECT ROW_NUMBER() OVER(ORDER BY SALARY DESC) 순위, EMP_NAME, SALARY
FROM EMPLOYEE;

--------------------------------------------------------------------------
--2. 비율관련함수(RATIO_TO_REPORT, PERCENT_RANK, CUME_DIST)
--RATIO_TO_REPORT() --전체대비 비율

--각 사원의 급여가 전체급여에서 차지하는 비율
SELECT EMP_ID, EMP_NAME, SALARY,
       RATIO_TO_REPORT(SALARY) OVER() AS SALARY_RATIO
  FROM EMPLOYEE;
  
--PERCENT_RANK() -- 가장먼저 조회된 값부터 늦게 조회된 값까지 결과값이 아닌 행의 순서별 백분율ㄹ
--순위-1/전체행개수-1
SELECT EMP_ID, EMP_NAME, SALARY,
       PERCENT_RANK() OVER (ORDER BY SALARY DESC) AS SALARY_RATIO
  FROM EMPLOYEE;
  
--CUME_DIST() --누적백분율
-- 해당 값 이하의 행 개수 / 전체 행 개수
SELECT EMP_ID, EMP_NAME, SALARY,
       CUME_DIST() OVER (ORDER BY SALARY DESC) AS SALARY_RATIO
  FROM EMPLOYEE;
  
--NTILE() - 데이터를 N개의 그룹으로 균등하게 분할하는 함수
SELECT EMP_ID, EMP_NAME, SALARY,
       NTILE(4) OVER (ORDER BY SALARY DESC) AS SALARY_RATIO
  FROM EMPLOYEE;
  
/*
    계층형 쿼리
-- 내부조인관계(동일 테이블에 계층적을로 상위와 하위데이터가 포함된 데이터를 말함-> 사수, 부사수)

CONNECT BY : 부모-자식관계를 정의하는 조건
START WITH: 계층 구조의 시작점을 지정
LEVEL: 계층의 깊이를 나타내는 가상컬럼
PRIOR: 바로 이전(부모) 행의 값을 참조할 때 사용
*/

SELECT LEVEL, EMP_ID, EMP_NAME, MANAGER_ID
FROM EMPLOYEE
START WITH MANAGER_ID IS NULL --최상위 관리자(ROOT NODE)
CONNECT BY PRIOR EMP_ID = MANAGER_ID
ORDER BY EMP_ID;


--CONNECT_BY_ROOT 컬럼명을 사용함면 가장 최상위 부모값을 가져올 수 있음
SELECT CONNECT_BY_ROOT EMP_ID AS ROOT_EMP_ID, --관리자 id
       CONNECT_BY_ROOT EMP_NAME AS ROOT_EMP_NAME,
       EMP_ID, EMP_NAME, MANAGER_ID
FROM EMPLOYEE
START WITH MANAGER_ID IS NULL --최상위 관리자(ROOT NODE)
CONNECT BY PRIOR EMP_ID = MANAGER_ID
ORDER BY EMP_ID;

--SYS_CONNECT_BY_PATH(컬럼명, '구분자')를 사용하면 부모부터 현재 행까지 경로를 문자열로 표현가능
SELECT EMP_ID, EMP_NAME,
       SYS_CONNECT_BY_PATH(EMP_NAME, '-') AS EMP_PATH
FROM EMPLOYEE
START WITH MANAGER_ID IS NULL --최상위 관리자(ROOT NODE)
CONNECT BY PRIOR EMP_ID = MANAGER_ID
ORDER BY EMP_ID;




