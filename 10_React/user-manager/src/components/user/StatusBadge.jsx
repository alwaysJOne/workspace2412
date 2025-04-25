import styled from 'styled-components';

export const StatusBadge = styled.span`
  display: inline-block;
  padding: 4px 12px;
  border-radius: 20px;
  font-size: 14px;
  font-weight: 500;
  background-color: ${props => props.isOnline ? '#2ecc71' : '#eb4e4e'};
  color: white;
`; 