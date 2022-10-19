package unsw.sso;

import java.util.HashMap;
import java.util.Map;

import unsw.sso.providers.Provider;

public class ClientApp {
    private Map<String, Boolean> usersExist = new HashMap<>();
    private final String name;

    public ClientApp(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // TODO: you'll probably want to change a lot of this class
    public void registerProvider(Provider provider) {
        provider.registerProvider(this);
    }

    public void registerUser(Token token) {
        // only hoogle is supported right now!  So we presume hoogle on user
        usersExist.put(token.getUserEmail(), true);
    }

    public boolean hasUserForProvider(String email, Provider provider) {
        return provider.hasProvider(this) && this.usersExist.getOrDefault(email, false);
    }

    public boolean hasHoogleUser(String email) {
        return usersExist.getOrDefault(email, false);
    }

    public boolean isValidToken(Token token) {
        if (token.getAccessToken() == null) return false;
        return usersExist.containsKey(token.getUserEmail());
    }

    public boolean hasProvider(Provider provider) {
        return provider.hasProvider(this);
    }

    
}
