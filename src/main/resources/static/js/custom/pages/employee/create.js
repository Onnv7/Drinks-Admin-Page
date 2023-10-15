function validatePassword(event) {
    var password = document.getElementById("password").value;
    var repassword = document.getElementById("re-password").value;
    if (password.length < 6) {
        alert("Passwords must be at least 6 characters");
        password.focus();
        event.preventDefault();
        return;
    }
    if (password !== repassword) {
        alert("Password and re-entered password do not match.");
        event.preventDefault();
    }
}

flatpickr("#birthDate", {
    enableTime: true,
    dateFormat: "Y-m-dTH:i:S.uZ"
});
