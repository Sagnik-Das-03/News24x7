package com.example.news24

data class News (
    val name: String,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val imageUrl: String,
    val publishedAt: String,
    val content: String
        )


// model Json Object

// https://newsapi.org/v2/top-headlines?country=in&category=technology&apiKey=APIKEY
//    "status": "ok",
//    "totalResults": 70,
//    "articles": [
//    {
//        "source": {
//        "id": null,
//        "name": "Hindustan Times"
//    },
//        "author": "HT Tech",
//        "title": "WhatsApp video calls while using other apps! Special feature for iPhone users coming - HT Tech",
//        "description": "WhatsApp will now allow Picture-in-Picture mode for users doing video calls while using other apps.",
//        "url": "https://tech.hindustantimes.com/tech/news/whatsapp-video-calls-while-using-other-apps-special-feature-for-iphone-users-coming-71670299582860.html",
//        "urlToImage": "https://images.hindustantimes.com/tech/img/2022/12/06/1600x900/dimitri-karastelev-ynJaWgrwSlM-unsplash_1667119581604_1670299669354_1670299669354.jpg",
//        "publishedAt": "2022-12-06T04:09:00Z",
//        "content": "WhatsApp video calls is set to get a big update that will allow users to do easier multitasking. WhatsApp video calls are quite popular in the subcontinent, allowing users to make video calls easily … [+1759 chars]"
//    }