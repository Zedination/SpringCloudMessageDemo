// Your web app's Firebase configuration
var firebaseConfig = {
    apiKey: "AIzaSyAy-40GYcqLt2T6eSkiqvcwwX9gY8RtciM",
    authDomain: "qldt-firebase.firebaseapp.com",
    databaseURL: "https://qldt-firebase.firebaseio.com",
    projectId: "qldt-firebase",
    storageBucket: "qldt-firebase.appspot.com",
    messagingSenderId: "1000396610409",
    appId: "1:1000396610409:web:5e775583757c533d2ffbf8",
    measurementId: "G-VXRDNBY87K",
    messagingSenderId: '1000396610409'
};
// Initialize Firebase
firebase.initializeApp(firebaseConfig);
firebase.analytics();
const messaging = firebase.messaging();
messaging.usePublicVapidKey('BJyn6dSihlZ1LrYCazdbMQE2xlXO4oduHJ5lYbu6t8fdwTuWOMVi98XnVti9HkSVvsbpMcwruvkD90MX5BYuJQc');
messaging
    .requestPermission()
    .then(function () {
        console.log("Notification permission granted.");

        // get the token in the form of promise
        return messaging.getToken()
    })
    .then(function (token) {
        console.log(token);
        $('#myToken').html(token);
        $('#tokenFCM').val(token);
    })
    .catch(function (err) {
        console.log("Unable to get permission to notify.", err);
    });
messaging.onMessage((payload) => {
    console.log('Message received. ', payload);
    $('.toast .toast-body').text(payload.data.content);
    $('.toast').toast('show');
});
$('input[type=checkbox]').click(() => {
    let size = $('input[type=checkbox]:checked').length;
    if (size == 1) {
        $('#myToken').show();
    } else {
        $('#myToken').hide();
    }
});
$('#sendData').click(() => {
    if ($('#content').val() != "") {
        let obj = new Object();
        obj.fcmtoken = $('#tokenFCM').val();
        obj.content = $('#content').val();
        $.ajax({
            type: "post",
            url: "/test-notification",
            data: obj,
            success: function (response) {
                
            }
        });
    }
});