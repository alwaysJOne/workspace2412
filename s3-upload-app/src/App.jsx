import { useState } from 'react';
import { v4 as uuidv4 } from 'uuid';
import './App.css';
import axios from 'axios';

function App() {
  const [file, setFile] = useState(null);
  const [downloadFileInfo, setDownloadFileInfo] = useState(null);
  const [status, setStatus] = useState('');

  const uploadFile = async (path) => {
    try{
      setStatus('업로드 중...');

      const extension = file.type.split("/").pop();
      const uniqueName = `${uuidv4()}.${extension}`;
      //encodeURIComponent() -> url에 포함될 수 없는 특수문자나 퍼센트같은 것들을 안전하게 encodinf해줌
      const encodingFileName = encodeURIComponent(uniqueName);

      //lambda api 호출 -> presigned url요청
      const response = await axios.get('https://151h0kahe6.execute-api.ap-northeast-3.amazonaws.com/default/getPresignedUrl',{
        params: {
          filename: path + encodingFileName,
          contentType: file.type,
        }
      })

      console.log(response.data);
      const { statusCode, body } = response.data;
      if(statusCode === 200){
        const bodyData = JSON.parse(body);
        const presignedUrl = bodyData.url;

        await axios.put(presignedUrl, file, {
          headers: {
            "Content-Type": file.type,
          }
        });
        

        setDownloadFileInfo({
          filename: encodingFileName,
          contentType: file.type,
          originalName: file.name,
        })
        setStatus('업로드 완료!');
      } else {
        console.error('presigned url 요청 실패', response.data);
        setStatus('업로드 실패');
      } 
    } catch (err){
      console.error(err);
      setStatus('업로드 실패');
    }
  }

  const downloadFile = async () => {

    try{
      const response = await axios.get('https://151h0kahe6.execute-api.ap-northeast-3.amazonaws.com/default/getPresignedUrl',
        {
          params:{
            filename: downloadFileInfo.filename,
            originalName: downloadFileInfo.originalName,
            mode: 'get',
          }
        }
      );

      const { statusCode, body } = response.data;

      if(statusCode === 200){
          const bodyData = JSON.parse(body);
          const presignedUrl = bodyData.url;

          window.open(presignedUrl, "_blank");
          setStatus('다운로드 완료');
      } else {
        console.error('다운로드 URL요청 실패:', response.data);
        setStatus('다운로드 실패');
      }
    } catch(err){
      console.error(err);
      setStatus('다운로드 실패');
    }
  }

  const handleFileChange = (ev) => {
    const selectedFile = ev.target.files[0];
    setFile(selectedFile);
  }

  return (
    <div className='app-container'>
      <h1>S3 파일 업로드/다운로드</h1>
      <div className='upload-section'>
        <h2>파일 업로드</h2>
        <input type="file" onChange={handleFileChange}/>
        <button onClick={() => uploadFile("")} disabled={!file}>
          업로드
        </button>
        <button onClick={()=> uploadFile("user-profile/")} disabled={!file}>
          프로필 업로드
        </button>
      </div>

      <div className='download-section'>
        <h2>파일 다운로드</h2>
        <input 
          type='text'
          value={downloadFileInfo?.originalName}
          disabled={true}
          placeholder='다운로드할 파일명 입력'
        />
        <button onClick={downloadFile} disabled={!downloadFileInfo}>
          다운로드
        </button>
      </div>
      {
        status !== '' &&
        <div className='status'>
          <p>{status}</p> 
          <img src={`https://dwxo8vkl18znf.cloudfront.net/user-profile/${downloadFileInfo?.filename}`}/>
        </div>
      }
    </div>
  )
}

export default App
