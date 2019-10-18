$(window).ready(function () {

    var currencySelect = $("#user-currency-select");

    $.ajax({
        url: "/rest/data/currencies",
        success: function (data) {
            if (data) {
                $(data).each(function (e, d) {
                    currencySelect.append("<option value='" + d.key + "'>" + d.key + "</option>");
                });
            }
        },
        error: function (code) {
            alert("Page has errors! Currencies couldn't be loaded. Error: " + code.errorCode);
        }
    });
});