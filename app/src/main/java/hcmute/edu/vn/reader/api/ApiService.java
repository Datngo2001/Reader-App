package hcmute.edu.vn.reader.api;

import android.icu.text.DateFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import hcmute.edu.vn.reader.MySingleton;
import hcmute.edu.vn.reader.dtos.CreateBooksRegisterDto;
import hcmute.edu.vn.reader.dtos.LoginDto;
import hcmute.edu.vn.reader.dtos.UpdateProfileDto;
import hcmute.edu.vn.reader.model.BaseResponse;
import hcmute.edu.vn.reader.model.BookTitle;
import hcmute.edu.vn.reader.model.BorrowRegister;
import hcmute.edu.vn.reader.model.User;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    // gson converter: convert json to java object
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    // create retrofit object
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://ute-lib-api.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    // check user login state
    @GET("me")
    Call<BaseResponse<User>> me(@Header("Authorization") String token);

    // login user
    @POST("login")
    @Headers({
            "Content-Type: application/json"
    })
    Call<BaseResponse<User>> login(@Body LoginDto data);

    // create account
    @POST("signup")
    @Headers({
            "Content-Type: application/json"
    })
    Call<BaseResponse<User>> signup(@Body LoginDto data);

    // logout user
    @POST("logout")
    Call<BaseResponse<User>> logout();

    // get reader card
    @GET("users/borrow/status")
    Call<BaseResponse<User>> status(@Header("Authorization") String token);

    // edit reader profile
    @PUT("users/profile")
    @Headers({
            "Content-Type: application/json"
    })
    Call<BaseResponse<User>> updateProfile(@Header("Authorization") String token,@Body UpdateProfileDto data);

    // search book
    @GET("booktitle/search")
    Call<BaseResponse<List<BookTitle>>> searchBookTitle(@Header("Authorization") String token, @Query("title") String title, @Query("page") int page, @Query("limit") int limit);

    // get book detail
    @GET("booktitle/{id}")
    Call<BaseResponse<BookTitle>> getBookTitleById(@Header("Authorization") String token, @Path("id") int id);

    // create borrow register
    @POST("borrowregister")
    @Headers({
            "Content-Type: application/json"
    })
    Call<BaseResponse<BorrowRegister>> registerBooks(@Header("Authorization") String token, @Body CreateBooksRegisterDto data);
}
