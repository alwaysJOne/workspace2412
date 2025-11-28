import styled from 'styled-components';
import { Link } from 'react-router-dom';

export const HomeContainer = styled.div`
  max-width: 800px;
  margin: 0 auto;
`;

export const Title = styled.h1`
  font-size: 48px;
  font-weight: bold;
  margin-bottom: 24px;
  color: #333;
`;

export const StatsGrid = styled.div`
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 20px;
  margin-bottom: 40px;
`;

export const StatCard = styled.div`
  background: #ffffff;
  padding: 24px;
  border-radius: 12px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
`;

export const StatNumber = styled.div`
  font-size: 36px;
  font-weight: bold;
  color: #4b7fcc;
  margin-bottom: 8px;
`;

export const StatLabel = styled.div`
  font-size: 14px;
  color: #666;
`;

export const CategorySection = styled.div`
  background: #ffffff;
  padding: 24px;
  border-radius: 12px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  margin-bottom: 24px;
`;

export const SectionTitle = styled.h2`
  font-size: 24px;
  margin-bottom: 16px;
  color: #333;
`;

export const CategoryList = styled.div`
  display: flex;
  flex-direction: column;
  gap: 12px;
`;

export const CategoryItem = styled(Link)`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px 16px;
  background: #f8f9fa;
  border-radius: 8px;
  text-decoration: none;
  color: #333;
  transition: all 0.2s;

  &:hover {
    background: #e9ecef;
    transform: translateX(4px);
  }
`;

export const CategoryName = styled.span`
  font-size: 16px;
  font-weight: 500;
`;

export const CategoryCount = styled.span`
  background: #4b7fcc;
  color: white;
  padding: 4px 12px;
  border-radius: 12px;
  font-size: 14px;
`;
