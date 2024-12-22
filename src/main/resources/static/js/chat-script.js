document.addEventListener('DOMContentLoaded', function() {
    const chatForm = document.getElementById('chatForm');
    const submitButton = document.getElementById('submitButton');
    const messageInput = document.getElementById('message');
    const loadingElement = document.getElementById('loading');
    const responseElement = document.getElementById('response');
    let eventSource;

    chatForm.addEventListener('submit', function(e) {
        e.preventDefault();
        if (submitButton.classList.contains('disabled')) return;

        const message = messageInput.value;
        disableForm();
        loadingElement.style.display = 'block';
        responseElement.innerHTML = '';

        if (eventSource) {
            eventSource.close();
        }

        eventSource = new EventSource('/api/chat?message=' + encodeURIComponent(message));

        let responseBuffer = '';

        eventSource.onmessage = function(event) {
            const [type, ...dataParts] = event.data.split(':');
            const data = dataParts.join(':'); // 콜론이 포함된 데이터 처리
            if (type === 'response' && data.trim() !== '') {
                loadingElement.style.display = 'none';
                responseBuffer += data;
                responseElement.innerHTML = responseBuffer;
                responseElement.scrollTop = responseElement.scrollHeight;
            }
        };

        eventSource.onerror = function(error) {
            console.error('Error:', error);
            loadingElement.style.display = 'none';
            if (!responseBuffer.trim()) {
                responseElement.innerHTML += '<br>오류가 발생했습니다. 다시 시도해 주세요.';
            }
            eventSource.close();
            enableForm();
        };

        eventSource.addEventListener('complete', function(event) {
            eventSource.close();
            enableForm();
        });
    });

    function disableForm() {
        submitButton.classList.add('disabled');
        messageInput.disabled = true;
    }

    function enableForm() {
        submitButton.classList.remove('disabled');
        messageInput.disabled = false;
    }
});
