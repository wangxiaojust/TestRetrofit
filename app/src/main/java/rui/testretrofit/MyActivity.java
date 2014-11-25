package rui.testretrofit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import retrofit.RetrofitError;
import util.SafeAsyncTask;

/**
 * Created by xiaoxiao on 14-11-20.
 */
public class MyActivity extends Activity {

    private Button testSession,testLogin;
    @Inject
    BootstrapService bootstrapService;
    private SafeAsyncTask<Boolean> authenticationTask;
    public static final String PARAM_PASSWORD = "password";

    /**
     * PARAM_USERNAME
     */
    public static final String PARAM_USERNAME = "username";
    private String token;

    private HashMap<String, String> session = new HashMap<String, String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my);
        findView();
        addListener();
    }

    private void findView(){
        testSession = (Button) findViewById(R.id.testSession);
        testLogin = (Button) findViewById(R.id.testLogin);
    }
    private void addListener(){

        /**
         * 测试登录
         */
        testLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                authenticationTask = new SafeAsyncTask<Boolean>() {
                    public Boolean call() throws Exception {
                        String email = "123";
                        String password = "321";

                        final String query = String.format("%s=%s&%s=%s",
                                PARAM_USERNAME, email, PARAM_PASSWORD, password);

                        User loginResponse = bootstrapService.authenticate(email, password);
                        token = loginResponse.getSessionToken();

                        return true;
                    }

                };
                authenticationTask.execute();

               // User loginResponse = bootstrapService.authenticate(email, password);
            }
        });

        /**
         * 测试session
         */
        testSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = "123";
                String password = "321";
                //调用接口
                //User loginResponse = bootstrapService.authenticate(email, password);

                if (checkUser()) {

                    Toast.makeText(v.getContext(), "用户登录成功！", Toast.LENGTH_SHORT)
                            .show();

                    Context context = v.getContext();

                    Intent intent = new Intent(context,LoginSuccessActivity.class);

                    // 传递session参数,在用户登录成功后为session初始化赋值,即传递HashMap的值

                    Bundle map = new Bundle();

                    map.putSerializable("sessionid", session);

                    intent.putExtra("session", map);

                    context.startActivity(intent); // 跳转到成功页面

                }else{
                    Toast.makeText(v.getContext(), "用户验证失败！", Toast.LENGTH_SHORT)
                            .show();
                }



            }

        });
    }

    private boolean checkUser() {

        String username = "123";

        String pass = "321";

        DefaultHttpClient mHttpClient = new DefaultHttpClient();

        HttpPost mPost = new HttpPost("http://192.168.1.148:8082/JsonToEntity");

        // 传递用户名和密码相当于

        // http://10.0.2.2/web/php/login.php?username=''&password=''

        List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();

        pairs.add(new BasicNameValuePair("username", username));

        pairs.add(new BasicNameValuePair("password", pass));

        try {

            mPost.setEntity(new UrlEncodedFormEntity(pairs, HTTP.UTF_8));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            HttpResponse response = mHttpClient.execute(mPost);
            int res = response.getStatusLine().getStatusCode();
            if (res == 200) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String info = EntityUtils.toString(entity);
                    System.out.println("info-----------" + info);
                    // 以下主要是对服务器端返回的数据进行解析
                    JSONObject jsonObject = null;
                    // flag为登录成功与否的标记,从服务器端返回的数据

                    String flag = "";

                    String name = "";

                    String userid = "";

                    String sessionid = "";

                    try {

                        jsonObject = new JSONObject(info);

                        flag = jsonObject.getString("flag");

                        name = jsonObject.getString("name");

                        userid = jsonObject.getString("userid");

                        sessionid = jsonObject.getString("sessionid");

                    } catch (JSONException e) {

                        // TODO Auto-generated catch block

                        e.printStackTrace();

                    }

                    // 根据服务器端返回的标记,判断服务端端验证是否成功

                    if (flag.equals("success")) {

                        // 为session传递相应的值,用于在session过程中记录相关用户信息

                        session.put("s_userid", userid);

                        session.put("s_username", name);

                        session.put("s_sessionid", sessionid);

                        return true;

                    }

                    else {

                        return false;

                    }

                }
                else {
                    return false;

                }
            }

        } catch (ClientProtocolException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return false;

    }

}
