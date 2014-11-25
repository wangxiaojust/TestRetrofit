package rui.testretrofit;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by xiaoxiao on 14-11-21.
 */
public class GetUserInfoActivity extends Activity {

    private HashMap<String,String> session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.get_info);

        session = (HashMap<String,String>) this.getIntent().getBundleExtra("session").getSerializable("sessionid");
        // 读取session的基本信息，并显示相应的控件
        String session_info = session.get("info_userinfo");

        String session_level = session.get("info_level");

        String session_id = session.get("info_sessionid");

        // 显示相应的内容到控件

        System.out.println("session_info--------" + session_info);

        TextView get_info = (TextView) findViewById(R.id.get_info);

        get_info.setText(session_info);

        TextView get_level = (TextView) findViewById(R.id.get_level);

        get_level.setText(session_level);

        TextView get_sessionid = (TextView) findViewById(R.id.get_sessionid);

        get_sessionid.setText(session_id);

    }
}
