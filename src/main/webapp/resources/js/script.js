//send orders
$(document).on("click", "#send", function () {
    $.post("cart", function (responseText) {
        $('#cartModal').modal('hide');
        let messageDiv = $('#orderSuccess');
        messageDiv.css(
            "border-radius","3px 3px 3px 3px");
        messageDiv.css(
            "background-color", "#DFF2BF");
        messageDiv.css(
            "padding", "10px");
        messageDiv.text(responseText);
        messageDiv.show().delay(3000).fadeOut();
        getCartCount();
    });
});

//back top top button
$(document).ready(function(){
    let backToTop = $('#backToTop');
    $(window).scroll(function () {
        if ($(this).scrollTop() > 50) {
            backToTop.fadeIn();
        } else {
            backToTop.fadeOut();
        }
    });
    backToTop.click(function () {
        $('#backToTop').tooltip('hide');
        $('html,body').animate({
            scrollTop: 0
        }, 400);
        return false;
    });
    backToTop.tooltip('show');
});

//get number of items in cart
function getCartCount() {
    $(document).ready(function () {
        $.get("cart-count", function (responseText) {
            let count = $('#ordersCount');
            count.text("(" + responseText + ")");
        });
    });
}

//modal methods
function openLoginModal() {
    $('#loginModal').modal('show');
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

function showErrorDialog() {
    let error = document.getElementById("hiddenError");
    if (error) {
        $('#loginModal').modal('show');
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
