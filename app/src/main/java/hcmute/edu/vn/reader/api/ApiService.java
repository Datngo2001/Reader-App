package hcmute.edu.vn.reader.api;

import android.icu.text.DateFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hcmute.edu.vn.reader.dtos.LoginDto;
import hcmute.edu.vn.reader.dtos.UpdateProfileDto;
import hcmute.edu.vn.reader.model.BaseResponse;
import hcmute.edu.vn.reader.model.BookTitle;
import hcmute.edu.vn.reader.model.User;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat(DateFormat.HOUR_OF_DAY0_FIELD)
            .create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://ute-lib-api.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("me")
    Call<BaseResponse<User>> me();

    @POST("login")
    Call<BaseResponse<User>> login(@Body LoginDto data);

    @POST("logout")
    Call<BaseResponse<User>> logout();

    @GET("users/borrow/status")
    Call<BaseResponse<User>> status();

    @PUT("users/profile")
    Call<BaseResponse<User>> updateProfile(@Body UpdateProfileDto data);

    @GET("booktitle/search")
    Call<BaseResponse<BookTitle>> searchBookTitle(@Query("title") String title, @Query("page") int page, @Query("limit") int limit);

    @GET("booktitle/{id}")
    Call<BaseResponse<BookTitle>> getBookTitleById(@Path("id") int id);

}
