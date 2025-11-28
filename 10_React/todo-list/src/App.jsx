import { TodoProvider } from './context/TodoContext';
import AppRoutes from './routes/routes';
import './App.css';

function App() {
  return (
    <TodoProvider>
      <AppRoutes />
    </TodoProvider>
  );
}

export default App;
