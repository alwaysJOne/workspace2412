import styled from 'styled-components';
import { media } from '../MediaQueries';

export const Title = styled.h1`
  font-size: ${({ theme }) => theme.fontSizes['2xl']};
  font-weight: ${({ theme }) => theme.fontWeights.bold};
  color: ${({ theme }) => theme.colors.gray[900]};

  ${media.md`
       font-size: ${({ theme }) => theme.fontSizes['3xl']};
    `}

  ${media.lg`
       font-size: ${({ theme }) => theme.fontSizes['4xl']};
    `}
`;
