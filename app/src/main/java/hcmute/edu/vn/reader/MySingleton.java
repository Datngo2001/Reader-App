package hcmute.edu.vn.reader;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import hcmute.edu.vn.reader.data.UserDbHelper;
import hcmute.edu.vn.reader.model.User;

public class MySingleton {
    private static MySingleton instance;
//    private ImageLoader imageLoader;
    private static Context ctx;
    private User currentUser;
    private String currentToken;

    private MySingleton(){
    }
    private MySingleton(Context context) {
        ctx = context;
        currentUser = getCurrentUser();
        currentToken = getCurrentToken();

//        imageLoader = new ImageLoader(requestQueue,
//                new ImageLoader.ImageCache() {
//                    private final LruCache<String, Bitmap>
//                            cache = new LruCache<String, Bitmap>(20);
//
//                    @Override
//                    public Bitmap getBitmap(String url) {
//                        return cache.get(url);
//                    }
//
//                    @Override
//                    public void putBitmap(String url, Bitmap bitmap) {
//                        cache.put(url, bitmap);
//                    }
//                });
    }
    public static synchronized MySingleton getInstance() {
        if (instance == null) {
            instance = new MySingleton();
        }
        return instance;
    }

    public static synchronized MySingleton getInstance(Context context) {
        if (instance == null) {
            instance = new MySingleton(context);
        }
        return instance;
    }

    public User getCurrentUser() {
        if (currentUser == null) {
            UserDbHelper helper = new UserDbHelper(ctx);
            SQLiteDatabase db = helper.getReadableDatabase();
            String projections[] = {"username", "email", "fname", "lname", "token"};
            Cursor c = db.query("user", projections, null, null, null,null, null);
            if (c == null){
                currentUser = null;
            }else if(c.getCount() > 0){
                c.moveToPosition(0);
                currentUser = new User();
                currentUser.setUsername(c.getString(0));
                currentUser.setEmail(c.getString(1));
                currentUser.setFname(c.getString(2));
                currentUser.setLname(c.getString(3));
                currentToken = c.getString(4);
            }else{
                currentUser = null;
            }
            c.close();
        }
        return currentUser;
    }

    public String getCurrentToken(){
        if (currentToken == null) {
            UserDbHelper helper = new UserDbHelper(ctx);
            SQLiteDatabase db = helper.getReadableDatabase();
            String projections[] = {"token"};
            Cursor c = db.query("user", projections, null, null, null,null, null);
            if (c == null){
                currentToken = null;
            }else if(c.getCount() > 0){
                c.moveToPosition(0);
                currentToken = c.getString(0);
            }else{
                currentToken = null;
            }
            c.close();
        }
        return currentToken;
    }

    public void setCurrentUserAndToken(User user, String token) {
        UserDbHelper helper = new UserDbHelper(ctx);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("email", user.getEmail());
        values.put("fname", user.getFname());
        values.put("lname", user.getLname());
        values.put("token", token);

        if(this.currentUser == null){
            db.insert("user", null, values);
        }else{
            String whereClause = "username = '" + this.currentUser.getUsername() + "'";
            db.update("user", values, whereClause,null);
        }

        this.currentUser = user;
        this.currentToken = token;
    }
}