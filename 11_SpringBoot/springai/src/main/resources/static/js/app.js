const API_BASE = '/api/ai';

async function runTest() {
    const button = document.querySelector('button');
    const resultDiv = document.getElementById('testResult');
    
    button.disabled = true;
    button.textContent = '실행 중...';
    resultDiv.style.display = 'none';
    
    try {
        const response = await fetch(`${API_BASE}/test`);
        const data = await response.json();
        
        if (data.status === 'success') {
            displayQuote(data.aiResponse);
        } else {
            displayError('API 응답 오류: ' + (data.error || '알 수 없는 오류'));
        }
    } catch (error) {
        displayError('네트워크 오류: ' + error.message);
    } finally {
        button.disabled = false;
        button.textContent = '테스트 실행';
    }
}

function displayQuote(aiResponse) {
    const element = document.getElementById('testResult');
    element.style.display = 'block';
    element.className = 'result success';
    
    // AI 응답에서 명언만 추출하여 깔끔하게 표시
    const formattedQuote = formatQuote(aiResponse);
    element.innerHTML = formattedQuote;
}

function formatQuote(aiResponse) {
    // AI 응답을 HTML로 포맷팅
    let formatted = aiResponse
        .replace(/\*\*(.*?)\*\*/g, '<strong>$1</strong>')  // **텍스트**를 <strong>텍스트</strong>로 변환
        .replace(/\n\n/g, '</p><p>')  // 이중 줄바꿈을 단락으로 변환
        .replace(/\n/g, '<br>')       // 단일 줄바꿈을 <br>로 변환
        .replace(/\d+\.\s*/g, '<strong>$&</strong>'); // 번호를 굵게 표시
    
    // 첫 번째와 마지막 <p> 태그 추가
    formatted = '<p>' + formatted + '</p>';
    
    return formatted;
}

function displayError(error) {
    const element = document.getElementById('testResult');
    element.style.display = 'block';
    element.className = 'result error';
    element.textContent = error;
}

async function generateImage() {
    const prompt = document.getElementById('imagePrompt').value.trim();
    if (!prompt) {
        alert('이미지 설명을 입력해주세요.');
        return;
    }

    const button = document.querySelector('button[onclick="generateImage()"]');
    const resultDiv = document.getElementById('imageResult');
    
    button.disabled = true;
    button.textContent = '생성 중...';
    resultDiv.style.display = 'none';
    
    try {
        const response = await fetch(`${API_BASE}/generate-image`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ prompt: prompt })
        });
        const data = await response.json();
        
        if (data.status === 'success') {
            displayImageResult(data);
        } else {
            displayImageError('API 응답 오류: ' + (data.error || '알 수 없는 오류'));
        }
    } catch (error) {
        displayImageError('네트워크 오류: ' + error.message);
    } finally {
        button.disabled = false;
        button.textContent = '이미지 생성';
    }
}

function displayImageResult(data) {
    const element = document.getElementById('imageResult');
    element.style.display = 'block';
    element.className = 'result success';
    
    element.innerHTML = `
        <h3>생성된 이미지:</h3>
        <div style="text-align: center; margin-top: 15px;">
            <img src="${data.imageUrl}" alt="AI 생성 이미지" style="max-width: 100%; height: auto; border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.1);">
        </div>
        <p style="margin-top: 15px; text-align: center;">
            <a href="${data.imageUrl}" target="_blank" style="color: #007bff; text-decoration: none;">🔗 새 탭에서 보기</a>
        </p>
    `;
}

function displayImageError(error) {
    const element = document.getElementById('imageResult');
    element.style.display = 'block';
    element.className = 'result error';
    element.textContent = error;
}

// Enter 키로 이미지 생성
document.addEventListener('DOMContentLoaded', function() {
    const imagePromptInput = document.getElementById('imagePrompt');
    if (imagePromptInput) {
        imagePromptInput.addEventListener('keypress', function(e) {
            if (e.key === 'Enter') {
                generateImage();
            }
        });
    }
    
    // Enter 키로 챗봇
    const chatMessageInput = document.getElementById('chatMessage');
    if (chatMessageInput) {
        chatMessageInput.addEventListener('keypress', function(e) {
            if (e.key === 'Enter') {
                chat('json'); // 기본적으로 JSON 응답
            }
        });
    }
    
    // Enter 키로 영화 리스트
    const directorNameInput = document.getElementById('directorName');
    if (directorNameInput) {
        directorNameInput.addEventListener('keypress', function(e) {
            if (e.key === 'Enter') {
                generateMovieList();
            }
        });
    }
    
    // Enter 키로 PromptTemplate
    const templateNameInput = document.getElementById('templateName');
    if (templateNameInput) {
        templateNameInput.addEventListener('keypress', function(e) {
            if (e.key === 'Enter') {
                promptTemplate();
            }
        });
    }
    
    // Enter 키로 Function Calling
    const functionQuestionInput = document.getElementById('functionQuestion');
    if (functionQuestionInput) {
        functionQuestionInput.addEventListener('keypress', function(e) {
            if (e.key === 'Enter') {
                functionCalling('ko'); // 기본적으로 한국어
            }
        });
    }
});

// 다양한 출력 변환 챗봇
async function chat(type) {
    const message = document.getElementById('chatMessage').value.trim();
    if (!message) {
        alert('질문을 입력해주세요.');
        return;
    }

    const resultDiv = document.getElementById('chatResult');
    resultDiv.style.display = 'none';
    
    // 다양한 출력 변환 챗봇 버튼만 비활성화
    const outputButtons = document.querySelectorAll('#outputTypeButtons button');
    outputButtons.forEach(btn => {
        btn.disabled = true;
        btn.textContent = '처리 중...';
    });
    
    try {
        let endpoint;
        switch(type) {
            case 'json': endpoint = '/chat-json'; break;
            case 'list': endpoint = '/chat-list'; break;
            case 'map': endpoint = '/chat-map'; break;
            default: endpoint = '/chat-json';
        }
        
        const response = await fetch(`${API_BASE}${endpoint}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ message: message })
        });
        const data = await response.json();
        
        if (data.status === 'success') {
            displayChatResult(data, type);
        } else {
            displayChatError('API 응답 오류: ' + (data.error || '알 수 없는 오류'));
        }
    } catch (error) {
        displayChatError('네트워크 오류: ' + error.message);
    } finally {
        // 다양한 출력 변환 챗봇 버튼만 복원
        outputButtons.forEach((btn, index) => {
            btn.disabled = false;
            const labels = ['JSON 응답', '리스트 변환', '맵 변환'];
            btn.textContent = labels[index];
        });
    }
}

function displayChatResult(data, type) {
    const element = document.getElementById('chatResult');
    element.style.display = 'block';
    element.className = 'result success';
    
    let resultHtml = `<h3>${type.toUpperCase()} 응답:</h3>`;
    resultHtml += `<p><strong>질문:</strong> ${data.message}</p>`;
    resultHtml += `<p><strong>응답 타입:</strong> ${data.type}</p>`;
    resultHtml += `<div style="margin-top: 15px;"><strong>결과:</strong></div>`;
    
    switch(type) {
        case 'json':
            resultHtml += `<pre>${JSON.stringify(data.result, null, 2)}</pre>`;
            break;
        case 'list':
            resultHtml += `<ul>${data.result.map(item => `<li>${item}</li>`).join('')}</ul>`;
            break;
        case 'map':
            resultHtml += `<ul>${Object.entries(data.result).map(([key, value]) => `<li><strong>${key}:</strong> ${value}</li>`).join('')}</ul>`;
            break;
    }
    
    element.innerHTML = resultHtml;
}

function displayChatError(error) {
    const element = document.getElementById('chatResult');
    element.style.display = 'block';
    element.className = 'result error';
    element.textContent = error;
}

// 영화 리스트 생성
async function generateMovieList() {
    const directorName = document.getElementById('directorName').value.trim();
    if (!directorName) {
        alert('감독 이름을 입력해주세요.');
        return;
    }

    const button = document.querySelector('button[onclick="generateMovieList()"]');
    const resultDiv = document.getElementById('movieResult');
    
    button.disabled = true;
    button.textContent = '생성 중...';
    resultDiv.style.display = 'none';
    
    try {
        const response = await fetch(`${API_BASE}/chat-movie`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ directorName: directorName })
        });
        const data = await response.json();
        
        if (data.status === 'success') {
            displayMovieResult(data);
        } else {
            displayMovieError('API 응답 오류: ' + (data.error || '알 수 없는 오류'));
        }
    } catch (error) {
        displayMovieError('네트워크 오류: ' + error.message);
    } finally {
        button.disabled = false;
        button.textContent = '영화 리스트 생성';
    }
}

function displayMovieResult(data) {
    const element = document.getElementById('movieResult');
    element.style.display = 'block';
    element.className = 'result success';
    
    let resultHtml = `<h3>영화 리스트:</h3>`;
    resultHtml += `<p><strong>감독:</strong> ${data.directorName}</p>`;
    resultHtml += `<p><strong>응답 타입:</strong> ${data.type}</p>`;
    resultHtml += `<div style="margin-top: 15px;"><strong>영화 목록:</strong></div>`;
    
    if (data.result && data.result.length > 0) {
        resultHtml += `<ul>${data.result.map(movie => `<li><strong>${movie.title}</strong> (${movie.year})</li>`).join('')}</ul>`;
    } else {
        resultHtml += `<p>해당 감독의 영화를 찾을 수 없습니다.</p>`;
    }
    
    element.innerHTML = resultHtml;
}

function displayMovieError(error) {
    const element = document.getElementById('movieResult');
    element.style.display = 'block';
    element.className = 'result error';
    element.textContent = error;
}

// PromptTemplate 예제
async function promptTemplate() {
    const name = document.getElementById('templateName').value.trim();
    if (!name) {
        alert('이름을 입력해주세요.');
        return;
    }

    const button = document.querySelector('button[onclick="promptTemplate()"]');
    const resultDiv = document.getElementById('templateResult');
    
    button.disabled = true;
    button.textContent = '실행 중...';
    resultDiv.style.display = 'none';
    
    try {
        const response = await fetch(`${API_BASE}/prompt-template`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ name: name })
        });
        const data = await response.json();
        
        if (data.status === 'success') {
            displayTemplateResult(data);
        } else {
            displayTemplateError('API 응답 오류: ' + (data.error || '알 수 없는 오류'));
        }
    } catch (error) {
        displayTemplateError('네트워크 오류: ' + error.message);
    } finally {
        button.disabled = false;
        button.textContent = '템플릿 실행';
    }
}

function displayTemplateResult(data) {
    const element = document.getElementById('templateResult');
    element.style.display = 'block';
    element.className = 'result success';
    
    let resultHtml = `<div style="margin-top: 15px;"><strong>결과:</strong></div>`;
    resultHtml += `<p>${formatQuote(data.result)}</p>`;
    
    element.innerHTML = resultHtml;
}

function displayTemplateError(error) {
    const element = document.getElementById('templateResult');
    element.style.display = 'block';
    element.className = 'result error';
    element.textContent = error;
}

// Function Calling 예제
async function functionCalling(language) {
    const question = document.getElementById('functionQuestion').value.trim();
    if (!question) {
        alert('질문을 입력해주세요.');
        return;
    }

    const resultDiv = document.getElementById('functionResult');
    resultDiv.style.display = 'none';
    
    // Function Calling 버튼만 비활성화
    const functionButtons = document.querySelectorAll('#functionButtons button');
    functionButtons.forEach(btn => {
        btn.disabled = true;
        btn.textContent = '처리 중...';
    });
    
    try {
        const endpoint = language === 'ko' ? '/function-calling-ko' : '/function-calling-en';
        const response = await fetch(`${API_BASE}${endpoint}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ question: question })
        });
        const data = await response.json();
        
        if (data.status === 'success') {
            displayFunctionResult(data, language);
        } else {
            displayFunctionError('API 응답 오류: ' + (data.error || '알 수 없는 오류'));
        }
    } catch (error) {
        displayFunctionError('네트워크 오류: ' + error.message);
    } finally {
        // Function Calling 버튼만 복원
        const labels = ['한국어', 'English'];
        functionButtons.forEach((btn, index) => {
            btn.disabled = false;
            btn.textContent = labels[index];
        });
    }
}

function displayFunctionResult(data, language) {
    const element = document.getElementById('functionResult');
    element.style.display = 'block';
    element.className = 'result success';
    
    let resultHtml = `<h3>Function Calling 결과 (${language === 'ko' ? '한국어' : 'English'}):</h3>`;
    resultHtml += `<p>${formatQuote(data.result)}</p>`;
    element.innerHTML = resultHtml;
}

function displayFunctionError(error) {
    const element = document.getElementById('functionResult');
    element.style.display = 'block';
    element.className = 'result error';
    element.textContent = error;
} 

// RAG 문서 기반 챗봇 질문
async function askRag() {
    const question = document.getElementById('ragQuestion').value.trim();
    const resultDiv = document.getElementById('ragAnswerResult');
    resultDiv.style.display = 'none';

    if (!question) {
        alert('질문을 입력해주세요.');
        return;
    }

    try {
        const response = await fetch('/api/rag/ask', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ question })
        });
        const data = await response.json();
        resultDiv.style.display = 'block';
        resultDiv.className = data.status === 'success' ? 'result success' : 'result error';
        if (data.status === 'success') {
            resultDiv.innerHTML = `<strong>AI 답변:</strong><br>${data.answer}`;
        } else {
            resultDiv.textContent = data.error;
        }
    } catch (error) {
        resultDiv.style.display = 'block';
        resultDiv.className = 'result error';
        resultDiv.textContent = '질문 처리 중 오류 발생: ' + error.message;
    }
} 

// SQL 자연어 질의 테스트
async function runSqlQuery() {
    const question = document.getElementById('sqlQuestion').value.trim();
    if (!question) {
        alert('질문을 입력해주세요.');
        return;
    }
    const button = document.querySelector('button[onclick="runSqlQuery()"]');
    const resultDiv = document.getElementById('sqlResult');
    button.disabled = true;
    button.textContent = '실행 중...';
    resultDiv.style.display = 'none';
    try {
        const response = await fetch(`${API_BASE}/sql?question=${encodeURIComponent(question)}`);
        const data = await response.json();
        displaySqlResult(data, question);
    } catch (error) {
        resultDiv.style.display = 'block';
        resultDiv.className = 'result error';
        resultDiv.textContent = '네트워크 오류: ' + error.message;
    } finally {
        button.disabled = false;
        button.textContent = 'SQL 질의 실행';
    }
}

function displaySqlResult(data, question) {
    const element = document.getElementById('sqlResult');
    element.style.display = 'block';
    if (data.query && data.query.startsWith('error:')) {
        element.className = 'result error';
        element.textContent = data.query;
        return;
    }
    element.className = 'result success';
    let html = `<h3>SQL 쿼리:</h3><pre>${data.query || ''}</pre>`;
    if (data.result && data.result.length > 0) {
        html += '<h3>결과:</h3>';
        html += '<table border="1" style="border-collapse:collapse; width:100%;">';
        html += '<thead><tr>';
        Object.keys(data.result[0]).forEach(key => {
            html += `<th>${key}</th>`;
        });
        html += '</tr></thead><tbody>';
        data.result.forEach(row => {
            html += '<tr>';
            Object.values(row).forEach(val => {
                html += `<td>${val}</td>`;
            });
            html += '</tr>';
        });
        html += '</tbody></table>';
    } else {
        html += '<p>결과가 없습니다.</p>';
    }
    element.innerHTML = html;
}

// Enter 키로 SQL 질의 실행
if (document.getElementById('sqlQuestion')) {
    document.getElementById('sqlQuestion').addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            runSqlQuery();
        }
    });
} 