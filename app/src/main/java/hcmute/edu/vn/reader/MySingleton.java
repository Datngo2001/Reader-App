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

// Singleton design partern
public class MySingleton {
    private static MySingleton instance;
    // current context
    private static Context ctx;
    // current user
    private User currentUser;
    // current jwt token
    private String currentToken;

    private MySingleton(){
    }

    private MySingleton(Context context) {
        ctx = context;
        currentUser = getCurrentUser();
        currentToken = getCurrentToken();
    }
    // get current singleton
    public static synchronized MySingleton getInstance() {
        if (instance == null) {
            instance = new MySingleton();
        }
        return instance;
    }
    // get current singleton with context
    public static synchronized MySingleton getInstance(Context context) {
        if (instance == null) {
            instance = new MySingleton(context);
        }
        return instance;
    }

    // get current user
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
                if(!c.getString(4).startsWith("Bearer ")){
                    currentToken = "Bearer " + c.getString(4);
                }else{
                    currentToken = c.getString(4);
                }

            }else{
                currentUser = null;
            }
            c.close();
        }
        return currentUser;
    }

    // get current token
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
                if(!c.getString(0).startsWith("Bearer ")){
                    currentToken = "Bearer " + c.getString(0);
                }else{
                    currentToken = c.getString(0);
                }
            }else{
                currentToken = null;
            }
            c.close();
        }
        return currentToken;
    }

    // set new user and token
    public void setCurrentUserAndToken(User user, String token) {
        if(!token.startsWith("Bearer ")){
            token = "Bearer " + token;
        }
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

    // delete current user
    public void logoutUser(){
        UserDbHelper helper = new UserDbHelper(ctx);
        SQLiteDatabase db = helper.getWritableDatabase();

        String whereClause = "username = '" + this.currentUser.getUsername() + "'";
        db.delete("user", whereClause,null);

        this.currentUser = null;
        this.currentToken = null;
    }
}