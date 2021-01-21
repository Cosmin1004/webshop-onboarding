$(document).on("click", "#send", function () {
    $.post("cart", function (responseText) {
        $('#cartModal').modal('hide');
        let messageDiv = $('#orderSuccess');
        messageDiv.text(responseText);
        messageDiv.show().delay(3000).fadeOut();
    });
});

function openLoginModal() {
    $('#loginModal').modal('show');
}

function showErrorDialog() {
    let error = document.getElementById("hiddenError");
    if (error) {
        $('#loginModal').modal('show');
    }
}

function showCartOrMyOrders() {
    let hiddenCart = document.getElementById("hiddenCart");
    let hiddenMyOrders = document.getElementById("hiddenMyOrders");
    if (hiddenCart) {
        $('#cartModal').modal('show');
    }
    if (hiddenMyOrders) {
        $('#myOrdersModal').modal('show');
    }
}

/*$(document).on("click","#addProductToCart",function(){
    $.post("order",
        {
            productName: $(this).val(),
        },
        function(responseText) {
        $('#orderSuccess').text(responseText);
        $("#orderSuccess").show().delay(2000).fadeOut();
    });
});*/
