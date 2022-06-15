package hcmute.edu.vn.reader.api;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import hcmute.edu.vn.reader.MySingleton;
import hcmute.edu.vn.reader.dtos.UserDto;
import hcmute.edu.vn.reader.interfaces.VolleyResponseListener;

public class AuthService {
    Context context;


    public AuthService(Context context) {
        this.context = context;
    }

    public void login(String _username, String _password, VolleyResponseListener volleyResponseListener){
        Object param = new Object(){
            public String username = _username;
            public String password = _password;
        };
        GsonRequest request = new GsonRequest("login", JSONObject.class,null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        volleyResponseListener.onResponse(response);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        volleyResponseListener.onError(error.getMessage());
                    }
                });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}
