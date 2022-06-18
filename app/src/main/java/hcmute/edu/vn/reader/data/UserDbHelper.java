package hcmute.edu.vn.reader.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UserDbHelper extends SQLiteOpenHelper {
    public UserDbHelper(@Nullable Context context) {
        super(context, "user.db", null, 2);
    }

    // create table sqlite
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table user (username text, email text, fname text, lname text, token text)");
        sqLiteDatabase.execSQL("create table cart (id int, title text, author text, image text, description text)");
    }

    // reset table sqlite
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists user");
        sqLiteDatabase.execSQL("drop table if exists cart");
        onCreate(sqLiteDatabase);
    }
}
