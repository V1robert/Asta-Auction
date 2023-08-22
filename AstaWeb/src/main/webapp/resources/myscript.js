
    function updateBottoneStato() {
        let inputField = document.getElementById('app:nconto');
        var submitButton = document.getElementById('app:faipagamento');
console.log("sono qui")
        if (inputField.value.trim() === "") {
            submitButton.disabled = true;
        } else {
            submitButton.disabled = false;
        }
    }
    
    window.onload = function() {
        updateBottoneStato();
    };