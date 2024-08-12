document.getElementById('shortenForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const longUrl = document.getElementById('longUrlInput').value;

    fetch('http://localhost:8080/shorten', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ longUrl: longUrl })
    })
    .then(response => response.text())
    .then(shortUrl => {
        document.getElementById('shortUrlOutput').value = shortUrl;
        document.getElementById('resultContainer').classList.remove('hidden');
    })
    .catch(error => {
        console.error('Error:', error);
    });
});

function copyToClipboard() {
    const shortUrlInput = document.getElementById('shortUrlOutput');
    shortUrlInput.select();
    document.execCommand('copy');
    alert('Shortened URL copied to clipboard!');
}
