/*
    <DCL : 데이터 제어문>
    계정에게 시스템권한 또는 객체접근 권한을 부여하거나 회수하는 구문
    
    >시스템권한 : DB에 접근하는 권한, 객체를 생성할 수 있는권한.
    >객체접근권한: 특정객체를 조작할 수 있는 권한
    
    CREATE USER 계정명 IDENTIFIED BY 비밀번호;
    GRANT 권한(RESOURCE, CONNECT) TO 계정;
    
    REVOKE 권한 TO 계정;
*/
SELECT * FROM ROLE_SYS_PRIVS;


/*
    <TCL : 트랜잭션 제어문>
    
    *트랜잭션
    -데이터베이스의 논리적 연산단위
    -데이터의 변경사항(DML)등을 하나의 트랜잭션에 묶어서 처리한다.
    DML문 한개를 수행할 때 트랜잭션이 존재하지 않는다면 트랜잭션을 만들어서 묶음.
                                  존재한다면 헤딩 트랜잭션에 묶어서 처리.
    COMMIT하기 전까지 변경사항들을 하나의 트랜잭션에 담는다.
    -트랜잭션에 대상이 되는 SQL : INSERT, UPDATE, DELETE
    
    COMMIT(트랜잭션 종료 처리 후 확정)
    ROLLBACK(트랜잭션 취소)
    SAVEPOINT(임시저장)

    COMMIT : 한 트랜잭션에 담겨있는 변경사항들을 실제 DB에 반영하겠다.
    ROLLBACK : 한 트랜잭션에 담겨있는 변겨사항들을 삭제(취소)한 후 마지막 COMMIT시점으로 돌아감.
    SAVEPOINT 포인트명 : 현 시점에 해당 포인트명으로 임시저장을 해두겠다.
*/
--오토커밋여부 확인
SHOW AUTOCOMMIT; 

--AUTOCOMMIT 수동설정 / ON-> 자동(DML문 실행마다 무조건 COMMIT)
SET AUTOCOMMIT OFF; 

DROP TABLE EMP_01;

CREATE TABLE EMP_01
AS (SELECT EMP_ID, EMP_NAME, DEPT_TITLE
      FROM EMPLOYEE
      JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID));
      
SELECT * FROM EMP_01;

--사번이 200, 201번인 사람 삭제
DELETE FROM EMP_01
WHERE EMP_ID IN(200, 201);

ROLLBACK;

SELECT * FROM EMP_01;

--사번이 200, 201번인 사람 삭제
DELETE FROM EMP_01
WHERE EMP_ID IN(200, 201);

COMMIT;
ROLLBACK;

SELECT * FROM EMP_01;

--사번이 217, 216번, 214인 사람 삭제
DELETE FROM EMP_01
WHERE EMP_ID IN(217, 216, 214);

SELECT * FROM EMP_01;

SAVEPOINT SP;

INSERT INTO EMP_01
VALUES(801, '김말똥', '기술지원부');

INSERT INTO EMP_01
VALUES(802, '김말순', '창업지원부');

ROLLBACK TO SP;
COMMIT;

SELECT * FROM EMP_01;

INSERT INTO EMP_01
VALUES(802, '김말순', '창업지원부');

INSERT INTO EMP_01
VALUES(801, '김말똥', '기술지원부');

CREATE TABLE TEST(
    TID NUMBER
);

ROLLBACK;
SELECT * FROM EMP_01;


--DDL문(CREATE, ALTER, DROP)을 수행하는 순간 기존 트랜잭션은 무조건 COMMIT됨(실제 DB에 반영됨)
--즉, DDL문 수행전 변경사항들이 있다면 정확하게 픽스하고 해라!
/*
    ACID속성 : 트랜잭션의 일관성과 실뢰성을 보장하기위해 지켜야하는 네가지 핵심 속성
    원자성: 트랜잭션 내의 작업이 모두 완료(COMMIT)되거나, 모두 취소(ROLLBACK)되어야 함.
    일관성: 트랜잭션이 실행되기 전과 후에 데이터베이스의 상태가 항상 일관성을 유지해야힘.
    독립성: 동시에 실행되는 트랜잭션이 서로에게 영향을 미치지 않도록 독립적으로 처리되어야한다.
    지속성: 트랜잭션이 성공적으로 완료된 후에는 시스템 장애가 발생하더라도 영구적으로 저장되어야 함.
*/


