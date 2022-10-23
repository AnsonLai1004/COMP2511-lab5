package unsw.sso.providers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import unsw.sso.ClientApp;
import unsw.sso.state.Page;

public abstract class Provider {
    public List<ClientApp> apps = new ArrayList<ClientApp>();
    
    public void registerProvider(ClientApp app) {
        Map<String, Integer> lockedEmails = new HashMap<>();
        for (ClientApp a : apps) {
            lockedEmails.putAll(a.lockedEmailList());
        }
        app.addLockedEmails(lockedEmails);
        apps.add(app);
    };

    public boolean hasProvider(ClientApp app) {
        if (apps.contains(app)) return true;
        return false;
    };

    public abstract Page getLoginPage();

    public abstract String getProviderString();
    //..........................
   
/* 
    public List<String> lockedEmailList() {
        List<String> lockedEmails = new ArrayList<String>();
        for (Entry<String, Integer> entry : invalidAttempt.entrySet()) {
            if (entry.getValue() >= 3) {
                lockedEmails.add(entry.getKey());
            }
        }
        return lockedEmails;
    }
*/
}
