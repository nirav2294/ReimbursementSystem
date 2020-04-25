$.get("Navbar.html", function (data) {
    $("#manager-placeholder").replaceWith(data);
});

$.get("footer.html", function (data) {
    $("#footer-placeholder").replaceWith(data);
});



