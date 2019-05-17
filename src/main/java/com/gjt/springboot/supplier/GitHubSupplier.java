package com.gjt.springboot.supplier;

import com.alibaba.fastjson.JSON;
import com.gjt.springboot.vo.AccessTokenVo;
import com.gjt.springboot.vo.GitHubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GitHubSupplier {
    public String getAccessToken(AccessTokenVo accessTokenVo){
    MediaType mediaType = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
        //System.out.println(accessTokenVo.getCode()+"==="+accessTokenVo.getClient_id());
        //System.out.println(JSON.toJSONString(accessTokenVo));
    RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenVo));
    Request request = new Request.Builder()
            .url("https://github.com/login/oauth/access_token")
            .post(body)
            .build();
    try (Response response = client.newCall(request).execute()){
        //System.out.println(response.body() != null ? response.body().string() : null);
        String message = response.body().string();
        //System.out.println(message);
        String[] split = message.split("&");
        String[] split1 = split[0].split("=");
        String token = split1[1];
        return token;
    } catch (Exception e) {
            e.printStackTrace();
        }
     return null;
    }

    public GitHubUser getUser(String access_token){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+access_token)
                .build();
        Response response ;
        try {
            response = client.newCall(request).execute();
            String string= response.body().string();
            GitHubUser gitHubUser = JSON.parseObject(string, GitHubUser.class);
            return gitHubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
       return null;
    }

}
