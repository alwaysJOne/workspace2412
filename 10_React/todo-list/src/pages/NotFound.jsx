import { Container, ErrorCode, Title, Message, HomeButton } from './NotFound.styled';
import { ROUTES } from '../routes/routePaths';

const NotFound = () => {
  return (
    <Container>
      <ErrorCode>404</ErrorCode>
      <Title>페이지를 찾을 수 없습니다</Title>
      <Message>
        요청하신 페이지가 존재하지 않거나 이동되었을 수 있습니다.
      </Message>
      <HomeButton to={ROUTES.HOME}>홈으로 돌아가기</HomeButton>
    </Container>
  );
};

export default NotFound;
