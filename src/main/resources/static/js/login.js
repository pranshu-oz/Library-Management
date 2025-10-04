// Example: Add shake animation on error

document.addEventListener('DOMContentLoaded', function() {
    var errorAlert = document.querySelector('.alert-danger');
    if (errorAlert) {
        var card = document.querySelector('.login-card');
        if (card) {
            card.classList.add('shake');
            setTimeout(function() {
                card.classList.remove('shake');
            }, 600);
        }
    }
});

// Optional: Add shake animation CSS via JS if not in CSS
var style = document.createElement('style');
style.innerHTML = `
@keyframes shake {
    0% { transform: translateX(0); }
    20% { transform: translateX(-10px); }
    40% { transform: translateX(10px); }
    60% { transform: translateX(-10px); }
    80% { transform: translateX(10px); }
    100% { transform: translateX(0); }
}
.login-card.shake {
    animation: shake 0.6s;
}
`;
document.head.appendChild(style);
