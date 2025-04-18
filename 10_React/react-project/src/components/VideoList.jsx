import React from 'react'
import VideoCard from './VideoCard'
import styled from 'styled-components'

const Container = styled.div`
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  gap: 16px;
`

const VideoList = ({videos}) => {
  return (
    <Container>
        {videos.map((v, index) => <VideoCard key={index} video={v}/>)}
    </Container>
  )
}

export default VideoList