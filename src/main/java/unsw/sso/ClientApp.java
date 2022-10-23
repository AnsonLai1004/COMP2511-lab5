package unsw.sso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import unsw.sso.providers.Provider;

public class ClientApp {
    private Map<String, Boolean> usersExist = new HashMap<>();
    private Map<String, Integer> invalidAttempt = new HashMap<>();

    private final String name;

    public ClientApp(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // TODO: you'll probably want to change a lot of this class
    public void registerProvider(Provider provider) {
        //lockedEmails.addAll(provider.lockedEmailList());
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
    public Integer getInvalidAttempt(String email) {
        if (this.invalidAttempt.containsKey(email)) {
            return invalidAttempt.get(email);
        } else {
            return 0;
        }
    }

    public void incrementInvalidAttempt(String email) {
        if (this.invalidAttempt.containsKey(email)) {
            this.invalidAttempt.put(email, invalidAttempt.get(email) + 1);
        } else {
            this.invalidAttempt.put(email, 1);
        }
    }
    
    public boolean emailIsLocked(String email) {
        if (getInvalidAttempt(email) >= 3) {
            return true;
        } else {
            return false;
        }
    }

    public Map<String, Integer> lockedEmailList() {
        Map<String,Integer> lockedEmails = new HashMap<>();
        for (Entry<String, Integer> entry : invalidAttempt.entrySet()) {
            if (entry.getValue() >= 3) {
                lockedEmails.put(entry.getKey(), entry.getValue());
            }
        }
        return lockedEmails;
    }

    public void addLockedEmails(Map<String,Integer> emails) {
        invalidAttempt.putAll(emails);
    }
}
