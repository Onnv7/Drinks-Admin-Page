$(document).ready(function () {
    $(".sidebar-item").on("click", function () {
        console.log("object", $(".sidebar-item"), $(this))
        $(".sidebar-item").removeClass("sidebar-item-active");
        $(this).addClass("sidebar-item-active");
    });
});