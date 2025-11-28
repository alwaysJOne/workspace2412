import { Routes, Route } from 'react-router-dom';
import Layout from '../components/Layout/Layout';
import Home from '../pages/Home';
import TodoListPage from '../pages/TodoListPage';
import TodoDetail from '../pages/TodoDetail';
import CategoryPage from '../pages/CategoryPage';
import NotFound from '../pages/NotFound';
import { ROUTES } from './routePaths';

const AppRoutes = () => {
  return (
    <Routes>
      <Route path={ROUTES.HOME} element={<Layout />}>
        <Route index element={<Home />} />
        <Route path="todos" element={<TodoListPage />} />
        <Route path="todos/:id" element={<TodoDetail />} />
        <Route path="category/:category" element={<CategoryPage />} />
        <Route path={ROUTES.NOT_FOUND} element={<NotFound />} />
      </Route>
    </Routes>
  );
};

export default AppRoutes;
