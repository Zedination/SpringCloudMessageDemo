importScripts("https://www.gstatic.com/firebasejs/7.16.1/firebase-app.js");
importScripts(
    "https://www.gstatic.com/firebasejs/7.16.1/firebase-messaging.js",
);
// For an optimal experience using Cloud Messaging, also add the Firebase SDK for Analytics.
importScripts(
    "https://www.gstatic.com/firebasejs/7.16.1/firebase-analytics.js",
);

// Initialize the Firebase app in the service worker by passing in the
// messagingSenderId.
firebase.initializeApp({
    messagingSenderId: "1000396610409",
    apiKey: "AIzaSyAy-40GYcqLt2T6eSkiqvcwwX9gY8RtciM",
    projectId: "qldt-firebase",
    appId: "1:1000396610409:web:5e775583757c533d2ffbf8",
});

// Retrieve an instance of Firebase Messaging so that it can handle background
// messages.
const messaging = firebase.messaging();
self.addEventListener('notificationclick', function(event){
    event.notification.close();
    event.waitUntil(
            clients.openWindow("https://demo-thongbao.herokuapp.com"));
});
messaging.setBackgroundMessageHandler(function(payload) {
    console.log(
        "[firebase-messaging-sw.js] Received background message ",
        payload,
    );
    // Customize notification here
    const notificationTitle = "Background Message Title";
    const notificationOptions = {
        body: payload.data.content,
        icon: "/itwonders-web-logo.png",
    };
    return self.registration.showNotification(
        notificationTitle,
        notificationOptions,
    );
});