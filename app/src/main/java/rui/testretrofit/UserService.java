package rui.testretrofit;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;


public interface UserService {

    @GET(Constants.Http.URL_USERS_FRAG)
    UsersWrapper getUsers();

    /**
     * @param email The users email
     * @param password The users password
     * @return A login response.
     */
    @GET(Constants.Http.URL_AUTH_FRAG)
    User authenticate(@Query(Constants.Http.PARAM_USERNAME) String email,
                      @Query(Constants.Http.PARAM_PASSWORD) String password);
}
