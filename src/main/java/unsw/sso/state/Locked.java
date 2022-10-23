package unsw.sso.state;

import unsw.sso.Browser;
import unsw.sso.Token;
import unsw.sso.providers.Provider;

public class Locked extends Page{

    @Override
    public String getPageName() {
        return "Locked";
    }

    @Override
    public void prev(Browser browser) {
        browser.setCurrentPage(new SelectAProvider());
    }

    @Override
    public void goToLoginPage(Provider provider, Browser browser) {
        return;
    }

    @Override
    public void verifyToken(Token token, Browser browser) {
        return;
    }
    
}
