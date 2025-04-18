import React from 'react'
import styled from 'styled-components';

const Container = styled.div`
    width: 320px;
    background: #fff;
`
const Thumbnail = styled.img`
    width: 100%;
    height: 180px;
`
const ChannelInfo = styled.div`
  display: flex;
  align-items: center;
  align-items: flex-start;
`

const ChannelLogo = styled.img`
    width: 36px;
    height: 36px;
    border-radius: 50px;
    margin-right: 12px;
`

const ChannelDetails = styled.div`
  flex-grow: 1;
  text-align: left;
`

const Title = styled.h3`
  font-size: 16px;
  font-weight: 500;
  margin: 0 0 4px 0;
  line-height: 1.4;
`
const ChannelName = styled.p`
  font-size: 14px;
  color: #606060;
  margin: 0 0 4px 0;
`

const ViewInfo = styled.p`
  font-size: 14px;
  color: #606060;
  margin-top: 4px;
  font-weight: 400;
`


const VideoCard = ({video}) => {
  return (
    <Container>
        <Thumbnail
            src={video.sumbnail}
            alt={video.title}
        />
        {/* 채널로고, 타이틀, 채널명, 조회 */}
        <ChannelInfo>
          <ChannelLogo 
            src={video.logo} 
            alt={video.channelName} 
          />
          <ChannelDetails>
            <Title>{video.title}</Title>
            <ChannelName>{video.channelName}</ChannelName>
            <ViewInfo>{video.views} 조회수 ● {video.date}</ViewInfo>
          </ChannelDetails>
        </ChannelInfo>
    </Container>
  )
}

export default VideoCard