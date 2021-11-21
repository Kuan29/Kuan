package com.example.stepcounter;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ValidateRequest extends StringRequest {
    //서버 url 설정(php파일 연동)
    final static  private String URL="http://10.0.2.2/UserValidate.php";
    private final Map<String, String> map;

    public ValidateRequest(String userID, Response.Listener<String> listener){
        super(Request.Method.POST, URL, listener,null);

        map = new HashMap<>();
        map.put("userID", userID);
    }

    @Override
    protected Map<String, String> getParams() {
        return map;
    }
}
