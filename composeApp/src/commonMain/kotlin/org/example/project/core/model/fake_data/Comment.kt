package org.example.project.core.model.fake_data

data class Comments(
       val id: String,
       val comment: String,
       val data: String,
       val authorName: String,
       val authorImageUrl: String,
       val authorId: String,
       val postId: String
)

val sampleComments = listOf(
       Comments(
              id = "1",
              comment = "Mr. Sangam is a software engineer who is more passionate about technology. His ambition towards technology is huge.",
              data = "2021-01-01",
              authorName = "Mr Dip",
              authorImageUrl = "https://services.google.com/fh/files/emails/image6_play_newsletter_march_2021.png",
              authorId = "1",
              postId = "1"
       ),
       Comments(
              id = "2",
              comment = "Flutter developer with a passion for creating elegant and useful mobile applications. Continuous Learner!",
              data = "2021-01-02",
              authorName = "John Cena",
              authorImageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/46/Apple_Store_logo.svg/1200px-Apple_Store_logo.svg.png",
              authorId = "2",
              postId = "1"
       ),
       Comments(
              id = "3",
              comment = "Flutter developer with a passion for creating elegant and useful mobile applications. Continuous Learner!",
              data = "2021-01-03",
              authorName = "Cristiano",
              authorImageUrl = "https://avatars.mds.yandex.net/i?id=c7150b8e82fa228ca81f9b3917ab2a258b46fd1c-5666966-images-thumbs&n=13",
              authorId = "3",
              postId = "1"
       ),
       Comments(
              id = "4",
              comment = "Flutter developer with a passion for creating elegant and useful mobile applications. Continuous Learner!",
              data = "2021-01-04",
              authorName = "L. James",
              authorImageUrl = "https://avatars.mds.yandex.net/i?id=fa8a8a34653df272e320022598064c5f_l-4904464-images-thumbs&n=13",
              authorId = "4",
              postId = "1"
       )
)
