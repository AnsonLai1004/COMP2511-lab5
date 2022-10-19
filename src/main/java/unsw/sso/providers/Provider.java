package unsw.sso.providers;


import java.util.ArrayList;
import java.util.List;

import unsw.sso.ClientApp;

public abstract class Provider {
    public List<ClientApp> apps = new ArrayList<ClientApp>();
    
    public void registerProvider(ClientApp app) {
        apps.add(app);
    };

    public boolean hasProvider(ClientApp app) {
        if (apps.contains(app)) return true;
        return false;
    };
}
