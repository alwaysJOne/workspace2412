import React, { useEffect } from 'react'
import usePostStore from '../store/postStore';
import { 
    Container, 
    Loading,
    Error, 
    PostCard,
    Title,
    Content,
    ButtonContainer,
} from './styled/PostLIst.styled'
import { Button } from './styled/common';
import styled from 'styled-components';

const ControlButton = styled(Button)`
    margin: 0;
`

const PostList = () => {
    const {posts , error, loading, getPosts} = usePostStore();

    useEffect(() => {
        getPosts();
    },[getPosts]);

    if(loading) return <Loading>로딩중...</Loading> 
    if(error) return <Error>에러발생 : {error}</Error>

    return (
        <Container>
           {posts.map((post) => (
            <PostCard>
                <Title>{post.title}</Title>
                <Content>{post.body}</Content>
                <ButtonContainer>
                    <ControlButton>수정</ControlButton>
                    <ControlButton
                        onClick={() => }
                    >삭제
                    </ControlButton>
                </ButtonContainer>
            </PostCard>
           ))} 
        </Container>
    )
}

export default PostList