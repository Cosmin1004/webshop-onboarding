function openLoginModal() {
    $('#loginModal').modal('show');
}

function showErrorDialog() {
    let error = document.getElementById("hiddenError");
    if (error) {
        $('#loginModal').modal('show');
    }
}

function showCart() {
    let hiddenCart = document.getElementById("hiddenCart");
    if (hiddenCart) {
        $('#cartModal').modal('show');
    }
}