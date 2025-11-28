import { useParams } from 'react-router-dom';
import { useTodos } from '../context/TodoContext';
import { ROUTES, CATEGORIES, CATEGORY_NAMES } from '../routes/routePaths';
import TodoForm from '../components/TodoForm/TodoForm';
import TodoItem from '../components/TodoItem';
import {
  Container,
  Header,
  Title,
  CategoryTabs,
  CategoryTab,
  TodoListContainer,
  EmptyMessage
} from './CategoryPage.styled';

const CategoryPage = () => {
  const { category } = useParams();
  const { getTodosByCategory, addTodo, toggleTodo, deleteTodo } = useTodos();

  const todos = getTodosByCategory(category);

  return (
    <Container>
      <Header>
        <Title>{CATEGORY_NAMES[category] || '카테고리'}</Title>
      </Header>

      <CategoryTabs>
        <CategoryTab
          to={ROUTES.CATEGORY_WORK}
          active={category === CATEGORIES.WORK ? 'true' : undefined}
        >
          {CATEGORY_NAMES[CATEGORIES.WORK]}
        </CategoryTab>
        <CategoryTab
          to={ROUTES.CATEGORY_PERSONAL}
          active={category === CATEGORIES.PERSONAL ? 'true' : undefined}
        >
          {CATEGORY_NAMES[CATEGORIES.PERSONAL]}
        </CategoryTab>
        <CategoryTab
          to={ROUTES.CATEGORY_STUDY}
          active={category === CATEGORIES.STUDY ? 'true' : undefined}
        >
          {CATEGORY_NAMES[CATEGORIES.STUDY]}
        </CategoryTab>
      </CategoryTabs>

      <TodoForm onAdd={(text) => addTodo(text, category)} />

      <TodoListContainer>
        {todos.length === 0 ? (
          <EmptyMessage>이 카테고리에 할일이 없습니다.</EmptyMessage>
        ) : (
          todos.map(todo => (
            <TodoItem
              key={todo.id}
              todo={todo}
              onToggle={toggleTodo}
              onDelete={deleteTodo}
            />
          ))
        )}
      </TodoListContainer>
    </Container>
  );
};

export default CategoryPage;
