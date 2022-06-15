package hcmute.edu.vn.reader.api;

import android.icu.text.DateFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
    Gson gson = new GsonBuilder()
            .setDateFormat(DateFormat.HOUR_OF_DAY0_FIELD)
            .create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://ute-lib-api.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("me")
    Call<BaseResponse<User>> me(@Header("Authorization") String token);

    @POST("login")
    @Headers({
            "Content-Type: application/json"
    })
    Call<BaseResponse<User>> login(@Body LoginDto data);

    @POST("signup")
    @Headers({
            "Content-Type: application/json"
    })
    Call<BaseResponse<User>> signup(@Body LoginDto data);

    @POST("logout")
    Call<BaseResponse<User>> logout();

    @GET("users/borrow/status")
    Call<BaseResponse<User>> status(@Header("Authorization") String token);

    @PUT("users/profile")
    @Headers({
            "Content-Type: application/json"
    })
    Call<BaseResponse<User>> updateProfile(@Header("Authorization") String token,@Body UpdateProfileDto data);

    @GET("booktitle/search")
    Call<BaseResponse<BookTitle>> searchBookTitle(@Header("Authorization") String token, @Query("title") String title, @Query("page") int page, @Query("limit") int limit);

    @GET("booktitle/{id}")
    Call<BaseResponse<BookTitle>> getBookTitleById(@Header("Authorization") String token, @Path("id") int id);

    @POST("borrowregister")
    @Headers({
            "Content-Type: application/json"
    })
    Call<BaseResponse<BorrowRegister>> registerBooks(@Header("Authorization") String token, @Body CreateBooksRegisterDto data);
}
