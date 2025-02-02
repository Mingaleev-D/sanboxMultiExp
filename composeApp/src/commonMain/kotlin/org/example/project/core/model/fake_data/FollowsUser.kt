package org.example.project.core.model.fake_data

data class FollowsUser(
       val id: Int,
       val name: String,
       val profileUrl: String,
       val isFollowing: Boolean = false
)

val sampleUsers = listOf(
       FollowsUser(
              id = 1,
              name = "Mr Dip",
              profileUrl = "https://services.google.com/fh/files/emails/image6_play_newsletter_march_2021.png"
       ),
       FollowsUser(
              id = 2,
              name = "John Cena",
              profileUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/46/Apple_Store_logo.svg/1200px-Apple_Store_logo.svg.png"
       ),
       FollowsUser(
              id = 3,
              name = "Cristiano",
              profileUrl = "https://avatars.mds.yandex.net/i?id=c7150b8e82fa228ca81f9b3917ab2a258b46fd1c-5666966-images-thumbs&n=13"
       ),
       FollowsUser(
              id = 4,
              name = "L. James",
              profileUrl = "https://avatars.mds.yandex.net/i?id=fa8a8a34653df272e320022598064c5f_l-4904464-images-thumbs&n=13"
       )
)
