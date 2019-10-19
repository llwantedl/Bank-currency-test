$(window).ready(function () {
    $("#user-logout-button").click(function () {
        document.getElementById("user-logout-form").submit();
    });

    var $manageWalletsButton = $("#user-wallets-button");
    var $userWalletsContainer = $("#user-wallets-container");
    var $transfersContainer = $("#user-wallet-transfers-container");
    var $transferParts = $(".transfer-part");
    var $proceedTransferMoneyButton = $("#proceed-transfer-money");

    var currencyKey = $("meta[name=user-currency-key]").attr("content");
    var contextPath = $("meta[name=context-path]").attr("href");

    function setTransferMoneyButtonEvent() {
        var $transferMoneyButton = $(".user-transfer-money-button");
        $transferMoneyButton.click(function () {
            var walletKey = $(this).data("user-wallet");
            var $fromWalletInput = $("#transfer-from-wallet");
            $fromWalletInput.val(walletKey);
            $transferParts.hide();
            $(".transfer-phase-1").show();

            $proceedTransferMoneyButton.on(function () {
                ajax({
                    url: "",
                    data: {},
                    success: function (data) {

                    },
                    error: function (data) {

                    }
                });
            })
        });
    }

    function setTransferHistoryButtonEvents() {
        var $transfersHistoryButton = $(".user-transfers-history-button");
        $transfersHistoryButton.click(function () {
            var walletKey = $(this).data("user-wallet");

            function loadPage(page) {
                $.ajax({
                    url: "/rest/wallets/get_operation_history",
                    data: {
                        walletKey: walletKey,
                        page: page
                    },
                    success: function (data) {
                        $("#user-wallet-transfers-pagination-controller")
                            .html(pagination(page, data.entitiesCount, gotoPage));

                        $transfersContainer.html("");

                        $(data.entityPage).each(function (e, d) {
                            appendTransferEntry(d);
                        });
                    },
                    error: function (err) {
                        alert("Error while loading wallet transfer history!");
                    }
                });
            }

            var gotoPage = function (event) {
                loadPage(event.data.pageNum);
            };

            loadPage(1);

        });
    }

    $manageWalletsButton.click(function () {
        $("#user-wallets-menu").show();
        $manageWalletsButton.attr("disabled", "");

        $.ajax({
            url: "/rest/wallets/get_remote",
            success: function (data) {
                if (data) {
                    $(data).each(function (e, d) {
                        appendUserWallet(d);
                    });

                    setTransferHistoryButtonEvents();
                    setTransferMoneyButtonEvent();
                }
            },
            error: function (code) {
                alert("Page has errors! Wallets couldn't be loaded. Error: " + code.errorCode);
            }
        });
    });

    function appendTransferEntry(entry) {
        $transfersContainer.append("<div class='card'>" +
            entry.key +
            "</div>");
    }

    function appendUserWallet(wallet) {
        $userWalletsContainer.append("<div class='card'>" +
            "    <div class='card-header' id='wallet-heading-" + wallet.key + "'>" +
            "      <h5 class='mb-0'>" +
            "        <button class='btn btn-link' data-toggle='collapse' data-target='#user-wallet-num-"
            + wallet.key + "' aria-expanded='true' aria-controls='user-wallet-num-" + wallet.key + "'>" +
            wallet.key +
            "        </button>" + wallet.balance + " " + currencyKey +
            "      </h5>" +
            "    </div>" +
            "    <div id='user-wallet-num-" + wallet.key + "' class='collapse show' aria-labelledby='wallet-heading-"
            + wallet.key + "' data-parent='#user-wallets-container'>" +
            "      <div class='card-body'>" +
            "         <div class='form-group'>Balance: " + wallet.balance + " " + currencyKey + "</div>" +
            "         <button type='button' class='btn btn-outline-primary user-transfer-money-button' " +
            "           data-toggle='modal' data-target='#transfer-money-modal-dialog'  data-user-wallet='" + wallet.key + "'>Transfer money</button>" +
            "         <button type='button' class='btn btn-outline-primary user-transfers-history-button' " +
            "           data-toggle='modal' data-target='#transfers-modal-dialog' data-user-wallet='" + wallet.key + "'>Transfer history</button>" +
            "      </div>" +
            "    </div>" +
            "  </div>");
    }
});