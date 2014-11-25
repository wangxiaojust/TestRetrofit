

package rui.testretrofit;


public final class Constants {
    private Constants() {}

    public static final class Auth {
        private Auth() {}


        public static final String BOOTSTRAP_ACCOUNT_TYPE = "rui";


        public static final String BOOTSTRAP_ACCOUNT_NAME = "androidTest";


        public static final String BOOTSTRAP_PROVIDER_AUTHORITY = "rui.sync";


        public static final String AUTHTOKEN_TYPE = BOOTSTRAP_ACCOUNT_TYPE;
    }


    public static final class Http {
        private Http() {}



        public static final String URL_BASE = "http://192.168.1.41:8082";


        //请求登录接口
        public static final String URL_AUTH_FRAG = "/EntityToJson";
        public static final String URL_AUTH = URL_BASE + URL_AUTH_FRAG;

        public static final String URL_USERS_FRAG =  "/1/users";
        public static final String URL_USERS = URL_BASE + URL_USERS_FRAG;


        public static final String URL_NEWS_FRAG = "/1/classes/News";
        public static final String URL_NEWS = URL_BASE + URL_NEWS_FRAG;



        public static final String URL_CHECKINS_FRAG = "/1/classes/Locations";
        public static final String URL_CHECKINS = URL_BASE + URL_CHECKINS_FRAG;


        public static final String PARAM_USERNAME = "username";
        public static final String PARAM_PASSWORD = "password";


        public static final String PARSE_APP_ID = "zHb2bVia6kgilYRWWdmTiEJooYA17NnkBSUVsr4H";
        public static final String PARSE_REST_API_KEY = "N2kCY1T3t3Jfhf9zpJ5MCURn3b25UpACILhnf5u9";
        public static final String HEADER_PARSE_REST_API_KEY = "X-Parse-REST-API-Key";
        public static final String HEADER_PARSE_APP_ID = "X-Parse-Application-Id";
        public static final String CONTENT_TYPE_JSON = "application/json";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String SESSION_TOKEN = "sessionToken";

    }


    public static final class Extra {
        private Extra() {}

        public static final String NEWS_ITEM = "news_item";

        public static final String USER = "user";

    }

    public static final class Intent {
        private Intent() {}

        public static final String INTENT_PREFIX = "rui.";

    }

    public static class Notification {
        private Notification() {
        }

        public static final int TIMER_NOTIFICATION_ID = 1000; // Why 1000? Why not? :)
    }

}


