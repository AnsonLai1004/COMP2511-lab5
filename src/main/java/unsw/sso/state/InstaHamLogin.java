package unsw.sso.state;

import unsw.sso.Browser;
import unsw.sso.Token;
import unsw.sso.providers.Provider;

public class InstaHamLogin extends Page{

    @Override
    public String getPageName() {
        return "InstaHam Login";
    }

    @Override
    public void goToLoginPage(Provider provider, Browser browser) {
        return;
    }

    @Override
    public void verifyToken(Token token, Browser browser) {
        super.verifyToken(token, browser);
    }
    
}
