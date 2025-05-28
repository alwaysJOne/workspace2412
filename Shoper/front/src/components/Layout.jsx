import React from 'react';
import styled from 'styled-components';
import Header from './Header';

const Layout = ({ children }) => {
  return (
    <>
      <Header />
      <Content>{children}</Content>
      {/* <Footer />   */}
    </>
  );
};

const Content = styled.main`
  min-height: 100vh;
  padding: ${({ theme }) => theme.spacing[8]} 0;
`;

export default Layout;
