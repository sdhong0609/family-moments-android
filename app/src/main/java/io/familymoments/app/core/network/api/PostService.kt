package io.familymoments.app.core.network.api

import io.familymoments.app.feature.addpost.model.AddPostResponse
import io.familymoments.app.feature.album.model.GetAlbumDetailResponse
import io.familymoments.app.feature.album.model.GetAlbumResponse
import io.familymoments.app.feature.calendar.model.GetPostsByMonthResponse
import io.familymoments.app.feature.home.model.GetPostsResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import io.familymoments.app.feature.postdetail.model.response.GetPostByIndexResponse
import io.familymoments.app.feature.postdetail.model.response.GetPostLovesByIndexResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface PostService {
    @GET("/posts")
    suspend fun getPosts(@Query("familyId") familyId: Long): Response<GetPostsResponse>

    @GET("/posts")
    suspend fun loadMorePosts(
        @Query("familyId") familyId: Long,
        @Query("postId") postId: Long
    ): Response<GetPostsResponse>

    @GET("/posts/album")
    suspend fun getAlbum(@Query("familyId") familyId: Long): Response<GetAlbumResponse>

    @GET("/posts/album")
    suspend fun loadMoreAlbum(
        @Query("familyId") familyId: Long,
        @Query("postId") postId: Long
    ): Response<GetAlbumResponse>

    @GET("/posts/album/{postId}")
    suspend fun getAlbumDetail(@Path("postId") postId: Long): Response<GetAlbumDetailResponse>

    @GET("/posts/calendar")
    suspend fun getPostsByMonth(
        @Query("familyId") familyId: Long,
        @Query("year") year: Int,
        @Query("month") month: Int
    ): Response<GetPostsByMonthResponse>

    @GET("/posts/calendar")
    suspend fun getPostsByDay(
        @Query("familyId") familyId: Long,
        @Query("year") year: Int,
        @Query("month") month: Int,
        @Query("day") day: Int
    ): Response<GetPostsResponse>

    @GET("/posts/calendar")
    suspend fun loadMorePostsByDay(
        @Query("familyId") familyId: Long,
        @Query("year") year: Int,
        @Query("month") month: Int,
        @Query("day") day: Int,
        @Query("postId") postId: Long
    ): Response<GetPostsResponse>

    @Multipart
    @POST("/posts")
    suspend fun addPost(
        @Query("familyId") familyId: Long,
        @Part("postInfo") postInfo: RequestBody,
        @Part images: List<MultipartBody.Part>?
    ): Response<AddPostResponse>

    @GET("/posts/{index}")
    suspend fun getPostByIndex(
        @Path("index") index: Int
    ):Response<GetPostByIndexResponse>

    @GET("/posts/{index}/post-loves")
    suspend fun getPostLovesByIndex(
        @Path("index") index:Int
    ):Response<GetPostLovesByIndexResponse>
}
