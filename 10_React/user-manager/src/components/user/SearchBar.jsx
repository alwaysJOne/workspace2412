import styled from 'styled-components';
import { SearchInput } from '../common/Input';

const Container = styled.div`
  position: relative;
  margin-bottom: 32px;
  width: 100%;
`;

const Icon = styled.span`
  position: absolute;
  left: 16px;
  top: 50%;
  transform: translateY(-50%);
  color: #95a5a6;
`;

function SearchBar({ value, onChange }) {
  return (
    <Container>
      <Icon>🔍</Icon>
      <SearchInput
        type="text"
        placeholder="사용자 검색..."
        value={value}
        onChange={onChange}
      />
    </Container>
  );
}

export default SearchBar; 