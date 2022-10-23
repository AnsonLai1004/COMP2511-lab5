package unsw.sso.state;

import unsw.sso.Browser;
import unsw.sso.Token;
import unsw.sso.providers.Provider;

public class NullPage extends Page{

    @Override
    public String getPageName() {
        return null;
    }

    @Override
    public void goToLoginPage(final Provider provider, final Browser browser) {
        return;
    }

    @Override
    public void verifyToken(final Token token, final Browser browser) {
        return;
    }
    
}
