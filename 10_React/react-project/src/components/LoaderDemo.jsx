import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import { BounceLoader } from 'react-spinners';

const LoaderDemo = () => {
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    //요청 후 2초뒤에 데이터 도착
    const timer = setTimeout(() => setLoading(false), 3000);

    return () => clearTimeout(timer);
  }, []);

  return (
    <Wrapper>
      {loading ? (
        <SpinnerWrapper>
          <BounceLoader size={100} color="#6a68ec" />
          <p>불러오는 중...</p>
        </SpinnerWrapper>
      ) : (
        <Content>
          <h2>데이터 로딩 완료!</h2>
          <p>이제 본문을 확인하세요 :)</p>
        </Content>
      )}
    </Wrapper>
  );
};

export default LoaderDemo;

const Wrapper = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 300px;
  flex-direction: column;
`;

const Content = styled.div`
  text-align: center;
`;

const SpinnerWrapper = styled.div`
  display: flex;
  align-items: center;
  flex-direction: column;
  color: black;
`;
