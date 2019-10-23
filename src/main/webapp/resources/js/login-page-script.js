$(window).ready(function () {
    if (new URL(location.href).searchParams.has("error")) {
        var $loginErrorContainer = $(".login-error-container");
        $loginErrorContainer.show();
    }
});