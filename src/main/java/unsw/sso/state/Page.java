package unsw.sso.state;

import unsw.sso.Browser;
import unsw.sso.ClientApp;
import unsw.sso.Token;
import unsw.sso.providers.Provider;

public abstract class Page {
    public void goToLoginPage(Provider provider, Browser browser) {
        if (!provider.hasProvider(browser.getCurrentApp())) return;
        browser.setPreviousPage(browser.getCurrentPage());
        browser.setCurrentPage(provider.getLoginPage());
    };

    public void verifyToken(Token token, Browser browser) {
        if (browser.getCurrentApp().emailIsLocked(token.getUserEmail())) {
            browser.setCurrentPage(new Locked());
            return;
        }
        if (token.getAccessToken() != null) {
            browser.setPreviousPage(browser.getCurrentPage());
            browser.setCurrentPage(new Home());
            browser.addCache(token);
        } else {
            invalidAttempt(browser, browser.getCurrentApp(), token.getUserEmail());
        }
        browser.getCurrentApp().registerUser(token);
    };

    public void prev(Browser browser) {
        browser.setCurrentPage(browser.getPreviousPage());
    };

    public void invalidAttempt(Browser browser, ClientApp app, String email) {
        app.incrementInvalidAttempt(email);
        if (app.emailIsLocked(email)) {
            browser.setCurrentPage(new Locked());
        } else {
            browser.setCurrentPage(new SelectAProvider());
        }
    }

    public abstract String getPageName();
}
