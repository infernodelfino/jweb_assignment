function emailOnChange(){
    let email = document.getElementById('input-email').value.trim();
    if (email.length >= 5) {
        return true;
    }

    return false;
}

function passwordOnChange(){
    let password = document.getElementById('input-password').value.trim();
    if (password.length >= 8) {
        return true;
    }

    return false;
}




function validateForm() {
    if (!passwordOnChange() || !emailOnChange()) {
        alert('Email or password is incorrect');
        return false;
    }

    return true;
}

