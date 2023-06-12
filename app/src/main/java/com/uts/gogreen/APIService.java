package com.uts.gogreen;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIService {
    @FormUrlEncoded
    @POST("getALlPost")
    Call<ValueData<List<Post>>>getPost(@Field("key") String key);

    @FormUrlEncoded
    @POST("loginUser")
    Call<ValueData<User>> login(@Field("username")String username,
                                @Field("password")String password);

    @FormUrlEncoded
    @POST("RegisterUSer")
    Call<ValueNoData> register(@Field("key") String key,
                               @Field("username") String username,
                               @Field("password") String password);

    @FormUrlEncoded
    @POST("insertPost")
    Call<ValueNoData> addPost(@Field("key") String key,
                              @Field("username") String username,
                              @Field("namapohon") String namapohon,
                              @Field("alamat") String alamat);
    @FormUrlEncoded
    @POST("addPost")
    Call<ValueNoData> addPost(@Field("key") String key,
                              @Field("content")String content,
                              @Field("user_id")String userId);
    @FormUrlEncoded
    @PUT("updatePost")
    Call<ValueNoData> updatePost(
                                 @Field("id")String id,
                                 @Field("namapohon")String namapohon,
                                 @Field("alamat")String alamat);

    @DELETE("post/{id}")
    Call<ValueNoData> deletePost(@Field("key") String key,
                                 @Path("id") String id);
}
