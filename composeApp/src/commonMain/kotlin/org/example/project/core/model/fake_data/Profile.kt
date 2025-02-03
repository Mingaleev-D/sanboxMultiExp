package org.example.project.core.model.fake_data

data class Profile(
       val id: Int,
       val name: String,
       val bio: String,
       val profileImgUrl: String,
       val followersCount: Int,
       val followingCount: Int,
       val isOwnProfile: Boolean = false,
       val isFollowing: Boolean = false
)

val sampleProfile = listOf(
       Profile(
              id = 252,
              name = "John Doe",
              bio = "Software Engineer | Kotlin Enthusiast | Coffee Lover",
              profileImgUrl = "https://techblog.codersangam.com/storage/profile-photos/bltZVoetIsLpQamn2eAYBSbIOFpFafV0tDUoghZ8.png",
              followersCount = 150,
              followingCount = 100,
              isOwnProfile = true
       ),
       Profile(
              id = 251,
              name = "Jane Smith",
              bio = "Mobile Developer | Android & iOS | Travel Addict",
              profileImgUrl = "https://techblog.codersangam.com/storage/profile-photos/GEHIzTBeUdBQv82VWrNVq3z80CPEVdHy7d7CJfTE.jpg",
              followersCount = 200,
              followingCount = 150,
              isFollowing = true
       ),
       Profile(
              id = 250,
              name = "Peter Jones",
              bio = "Web Developer | React & Node.js | Music Fan",
              profileImgUrl = "https://techblog.codersangam.com/storage/profile-photos/bltZVoetIsLpQamn2eAYBSbIOFpFafV0tDUoghZ8.png",
              followersCount = 100,
              followingCount = 50
       ),
       Profile(
              id = 4,
              name = "Alice Johnson",
              bio = "Data Scientist | Python & Machine Learning | Bookworm",
              profileImgUrl = "https://techblog.codersangam.com/storage/profile-photos/bltZVoetIsLpQamn2eAYBSbIOFpFafV0tDUoghZ8.png",
              followersCount = 250,
              followingCount = 200,
              isFollowing = true
       ),
       Profile(
              id = 249,
              name = "Bob Williams",
              bio = "UI/UX Designer | Figma & Sketch | Art Lover",
              profileImgUrl = "https://techblog.codersangam.com/storage/profile-photos/bltZVoetIsLpQamn2eAYBSbIOFpFafV0tDUoghZ8.png",
              followersCount = 180,
              followingCount = 120
       )
)
