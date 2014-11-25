
package rui.testretrofit;

import java.util.List;

import retrofit.RestAdapter;

/**
 * Bootstrap API service
 */
public class BootstrapService {

    private RestAdapter restAdapter;


    public BootstrapService() {
    }


    public BootstrapService(RestAdapter restAdapter) {
        this.restAdapter = restAdapter;
    }

    private UserService getUserService() {
        return getRestAdapter().create(UserService.class);
    }

    private RestAdapter getRestAdapter() {
        return restAdapter;
    }

    public List<User> getUsers() {
        return getUserService().getUsers().getResults();
    }



    public User authenticate(String email, String password) {
        return getUserService().authenticate(email, password);
    }
}