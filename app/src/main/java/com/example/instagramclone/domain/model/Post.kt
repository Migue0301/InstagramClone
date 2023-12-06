package com.example.instagramclone.domain.model

data class Posts(
    var postId : String = "",
    var postImage : String = "",
    var postDescription : String = "",
    var time : Long? = null,
    var userId : String = "",
    var userImage : String = "",
    var userName : String = ""
)
