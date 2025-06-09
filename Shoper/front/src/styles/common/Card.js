import styled from 'styled-components';
import { media } from '../MediaQueries';

export const Card = styled.div`
  transition:
    transform 0.2 ease,
    box-shadow 0.2 ease;
`;

export const CardImage = styled.div`
  width: 100%;
  height: 160px;
  background-color: ${({ theme }) => theme.colors.gray[200]};
  background-image: ${({ src }) => (src ? `url(${src})` : 'none')};
  background-size: cover; //비율유지하면서 꽉채워줘.
  background-position: center;

  ${media.md`
    height: 200px;
  `}
`;

export const CardContent = styled.div`
  padding: ${({ theme }) => theme.spacing[3]};
  ${media.md`
    padding: ${({ theme }) => theme.spacing[4]};
  `}
`;

export const CardTitle = styled.h3`
  font-size: ${({ theme }) => theme.fontSizes.base};
  font-weight: ${({ theme }) => theme.fontWeights.medium};
  margin-bottom: ${({ theme }) => theme.spacing[2]};
  color: ${({ theme }) => theme.colors.gray[900]};

  ${media.md`
    font-size: ${({ theme }) => theme.fontSizes.lg};
  `}
`;
