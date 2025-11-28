import { useTodos } from '../context/TodoContext';
import { ROUTES, CATEGORIES, CATEGORY_NAMES } from '../routes/routePaths';
import {
  HomeContainer,
  Title,
  StatsGrid,
  StatCard,
  StatNumber,
  StatLabel,
  CategorySection,
  SectionTitle,
  CategoryList,
  CategoryItem,
  CategoryName,
  CategoryCount
} from './Home.styled';

const Home = () => {
  const { getStats } = useTodos();
  const stats = getStats();

  const categories = [
    { name: CATEGORY_NAMES[CATEGORIES.WORK], value: CATEGORIES.WORK, count: stats.byCategory.work },
    { name: CATEGORY_NAMES[CATEGORIES.PERSONAL], value: CATEGORIES.PERSONAL, count: stats.byCategory.personal },
    { name: CATEGORY_NAMES[CATEGORIES.STUDY], value: CATEGORIES.STUDY, count: stats.byCategory.study },
  ];

  return (
    <HomeContainer>
      <Title>Dashboard</Title>

      <StatsGrid>
        <StatCard>
          <StatNumber>{stats.total}</StatNumber>
          <StatLabel>전체 할일</StatLabel>
        </StatCard>
        <StatCard>
          <StatNumber>{stats.pending}</StatNumber>
          <StatLabel>미완료</StatLabel>
        </StatCard>
        <StatCard>
          <StatNumber>{stats.completed}</StatNumber>
          <StatLabel>완료</StatLabel>
        </StatCard>
      </StatsGrid>

      <CategorySection>
        <SectionTitle>카테고리별 할일</SectionTitle>
        <CategoryList>
          {categories.map(category => (
            <CategoryItem key={category.value} to={ROUTES.CATEGORY(category.value)}>
              <CategoryName>{category.name}</CategoryName>
              <CategoryCount>{category.count}</CategoryCount>
            </CategoryItem>
          ))}
        </CategoryList>
      </CategorySection>
    </HomeContainer>
  );
};

export default Home;
