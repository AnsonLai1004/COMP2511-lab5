package unsw.sso.state;

import unsw.sso.Browser;
import unsw.sso.Token;
import unsw.sso.providers.Hoogle;
import unsw.sso.providers.Provider;

public class SelectAProvider extends Page{

    @Override
    public String getPageName() {
        return "Select a Provider";
    }

    @Override
    public void goToLoginPage(Provider provider, Browser browser) {
        super.goToLoginPage(provider, browser);
    }

    @Override
    public void prev(Browser browser) {
        super.prev(browser);
    }

    @Override
    public void verifyToken(Token token, Browser browser) {
        return;
    }



}
