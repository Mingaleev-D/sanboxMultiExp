package org.example.project.core.model.fake_data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostsDTO(
       @SerialName("all_posts")
       val allPosts: List<AllPostDTO> = listOf(),
       @SerialName("popular_posts")
       val popularPosts: List<PopularPostItemDTO> = listOf(),
       @SerialName("status")
       val status: Int = 0
)

@Serializable
data class PopularPostItemDTO(
       @SerialName("about")
       val about: String = "",
       @SerialName("body")
       val body: String = "",
       @SerialName("created_at")
       val createdAt: String = "",
       @SerialName("email")
       val email: String = "",
       @SerialName("featuredimage")
       val featuredimage: String = "",
       @SerialName("id")
       val id: Int = 0,
       @SerialName("name")
       val name: String = "",
       @SerialName("profile_photo_path")
       val profilePhotoPath: String = "",
       @SerialName("profile_photo_url")
       val profilePhotoUrl: String = "",
       @SerialName("slug")
       val slug: String = "",
       @SerialName("status")
       val status: Int = 0,
       @SerialName("title")
       val title: String = "",
       @SerialName("updated_at")
       val updatedAt: String = "",
       @SerialName("user_id")
       val userId: Int = 0,
       @SerialName("views")
       val views: Int = 0,
       @SerialName("like")
       val like: Int? = null
)

@Serializable
data class AllPostDTO(
       @SerialName("about")
       val about: String = "",
       @SerialName("body")
       val body: String = "",
       @SerialName("created_at")
       val createdAt: String? = "",
       @SerialName("email")
       val email: String = "",
       @SerialName("featuredimage")
       val featuredimage: String? = "",
       @SerialName("id")
       val id: Int = 0,
       @SerialName("name")
       val name: String? = "",
       @SerialName("profile_photo_path")
       val profilePhotoPath: String = "",
       @SerialName("profile_photo_url")
       val profilePhotoUrl: String? = "",
       @SerialName("slug")
       val slug: String = "",
       @SerialName("status")
       val status: Int = 0,
       @SerialName("title")
       val title: String = "",
       @SerialName("updated_at")
       val updatedAt: String = "",
       @SerialName("user_id")
       val userId: Int = 0,
       @SerialName("views")
       val views: Int = 0,
       @SerialName("like")
       val like: Int? = null
)

val samplePosts = listOf(
       AllPostDTO(
              id = 252,
              name = "Sangam Singh",
              email = "admin@admin.com",
              profilePhotoPath = "profile-photos/bltZVoetIsLpQamn2eAYBSbIOFpFafV0tDUoghZ8.png",
              about = "Mr. Sangam is a software engineer...", // ... (rest of the string)
              createdAt = "2025-01-17T05:09:43.000000Z",
              updatedAt = "2025-01-28T04:29:35.000000Z",
              userId = 1,
              title = "The Life of a Programmer: Beyond the Code",
              slug = "the-life-of-a-programmer",
              featuredimage = "https://techblog.codersangam.com/storage/featured-image/GIxh2yPveomDC9OnAntoHbkEsJgBZGv0jWtmZnm8.png",
              body = "The term \\\"programmer\\\" often conjures...", // ... (rest of the string)
              status = 2,
              views = 7,
              profilePhotoUrl = "https://techblog.codersangam.com/storage/profile-photos/bltZVoetIsLpQamn2eAYBSbIOFpFafV0tDUoghZ8.png"
       ),
       AllPostDTO(
              id = 251,
              name = "Ganesh Pandit",
              email = "gan3shpandit@gmail.com",
              profilePhotoPath = "profile-photos/GEHIzTBeUdBQv82VWrNVq3z80CPEVdHy7d7CJfTE.jpg",
              about = "Flutter developer with a passion...", // ...
              createdAt = "2025-01-15T13:57:56.000000Z",
              updatedAt = "2025-02-01T19:03:28.000000Z",
              userId = 170,
              title = "Exploring the World of Geometry: The Beauty of Shapes and Spaces",
              slug = "Exploring-the-World-of-Geometry:-The-Beauty-of-Shapes-and-Spaces",
              featuredimage = "https://techblog.codersangam.com/storage/featured-image/jgCxFbkvCw7Z1PQva92wZWMA2mLF3tgrQ84B809i.jpg",
              body = "<p>Geometry, a branch of mathematics,...", // ...
              status = 1,
              views = 14,
              profilePhotoUrl = "https://techblog.codersangam.com/storage/profile-photos/GEHIzTBeUdBQv82VWrNVq3z80CPEVdHy7d7CJfTE.jpg"
       ),
       AllPostDTO(
              id = 250,
              name = "Ganesh Pandit",
              email = "gan3shpandit@gmail.com",
              profilePhotoPath = "profile-photos/GEHIzTBeUdBQv82VWrNVq3z80CPEVdHy7d7CJfTE.jpg",
              about = "Flutter developer with a passion...", // ...
              createdAt = "2025-01-15T13:55:31.000000Z",
              updatedAt = "2025-01-28T03:31:15.000000Z",
              userId = 170,
              title = "Earth Networks: Monitoring the Pulse of Our Planet",
              slug = "earth-networks:-monitoring-the-pulse-of-our-planet",
              featuredimage = "https://techblog.codersangam.com/storage/featured-image/AHNvoBUf54HHbH3txNfk4orRKUE0oaUN01XiVW6e.jpg",
              body = "<p>In an era where climate change,...", // ...
              status = 1,
              views = 8,
              profilePhotoUrl = "https://techblog.codersangam.com/storage/profile-photos/GEHIzTBeUdBQv82VWrNVq3z80CPEVdHy7d7CJfTE.jpg"
       ),
       AllPostDTO(
              id = 249,
              name = "Ganesh Pandit",
              email = "gan3shpandit@gmail.com",
              profilePhotoPath = "profile-photos/GEHIzTBeUdBQv82VWrNVq3z80CPEVdHy7d7CJfTE.jpg",
              about = "Flutter developer with a passion...", // ...
              createdAt = "2025-01-15T13:53:32.000000Z",
              updatedAt = "2025-01-26T12:25:10.000000Z",
              userId = 170,
              title = "The Journey of a Software Developer: Crafting Solutions in the Digital Age",
              slug = "the-journey-of-a-software-developer:-crafting-solutions-in-the-digital-age",
              featuredimage = "https://techblog.codersangam.com/storage/featured-image/Gpqq9cF5Al597Tghnk1hmdeYzNYf4hzWCdFgE8Ax.jpg",
              body = "<p>In today’s world, where technology...", // ...
              status = 1,
              views = 7,
              profilePhotoUrl = "https://techblog.codersangam.com/storage/profile-photos/GEHIzTBeUdBQv82VWrNVq3z80CPEVdHy7d7CJfTE.jpg"
       )
)
