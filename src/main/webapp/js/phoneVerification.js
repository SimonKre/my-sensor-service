$(document).ready(function () {

    var requestVerificationCodeUrl = "http://localhost:8080/request-phone-verification/";
    var phoneVerificationUrl = "http://localhost:8080/verify-phone/";
    var requestVerificationBtn = $("#request-verification-btn");
    var verifyGroup = $("#verify-group");
    var verificationStatus = $("#verification-status");
    var verifyBtn = verifyGroup.find("#verify-btn");
    var phoneNumber;
    requestVerificationBtn.on("click", requestPhoneVerification);
    verifyBtn.on("click", verifyPhone);

    function requestPhoneVerification() {
        var number = $("#phone-number").val();
        console.log(number);
        phoneNumber = {
            "phoneNumber": number
        }
        sendRequest(requestVerificationCodeUrl, "POST", phoneNumber, handleRequestPhoneVerificationResponse);

        event.preventDefault();
        return false;
    }

    function handleRequestPhoneVerificationResponse(data, element) {
        phoneNumber = data;
        verifyGroup.slideDown();
    }

    function verifyPhone() {
        phoneNumber.verificationCode = $("#verification-code").val();
        sendRequest(phoneVerificationUrl, "POST", phoneNumber, handlePhoneVerificationResponse, verificationStatus);
        event.preventDefault();
        return false;
    }

    function handlePhoneVerificationResponse(data, element) {
        phoneNumber = data;
        if (phoneNumber.verificationStatus == 0) {
            element.find("h4").text("Weryfikacja udana!");
        } else {
            element.find("h4").text("Coś poszło nie tak...");
        }
        element.slideDown();
    }

    function sendRequest(url, methodType, data, successHandler, element) {
        $.ajax({
            url: url,
            type: methodType,
            data: data === undefined ? "" : JSON.stringify(data),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
        }).done(function (data) {
            successHandler(data, element);
        }).fail(function (xhr, status, errorThrown) {
            console.log("Error", xhr, status, errorThrown);
        })
    }

});