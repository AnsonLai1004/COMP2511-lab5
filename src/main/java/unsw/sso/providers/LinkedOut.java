package unsw.sso.providers;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import unsw.sso.Token;
import unsw.sso.state.LinkedOutLogin;
import unsw.sso.state.Page;

public class LinkedOut extends Provider{
    private Map<String, String> userMappings = new HashMap<>();

    public void addUser(String email, String password) {
        userMappings.put(email, password);
    }

    public Token generateFormSubmission(String email, String password) {
        if (Objects.equals(userMappings.get(email), password) || password.length() == 0) {
            return new Token(UUID.randomUUID().toString(), email, getClass().getSimpleName());
        } else {
            return new Token(null, email, getClass().getSimpleName());
        }
    }

    @Override
    public Page getLoginPage() {
        return new LinkedOutLogin();
    }

    @Override
    public String getProviderString() {
        return "LinkedOut";
    }
    
}
