package unsw.sso;

import java.util.ArrayList;
import java.util.List;

import unsw.sso.providers.Hoogle;
import unsw.sso.providers.LinkedOut;
import unsw.sso.providers.Provider;
import unsw.sso.state.Home;
import unsw.sso.state.Locked;
import unsw.sso.state.NullPage;
import unsw.sso.state.Page;
import unsw.sso.state.SelectAProvider;

public class Browser {
    private Page currentPage = null;
    private Page previousPage = null;
    private ClientApp currentApp = null;
    private List<Token> cache = new ArrayList<Token>();


    public void visit(ClientApp app) {
        this.previousPage = new NullPage();
        this.currentPage = new SelectAProvider();
        this.currentApp = app;
        for (Token t : cache) {
            if (app.isValidToken(t)) {
                this.currentPage = new Home();
                if (currentApp.emailIsLocked(t.getUserEmail())) {
                    this.currentPage = new Locked();
                    return;
                }
            }
        }
    }

    public String getCurrentPageName() {
        return this.currentPage.getPageName();
    }

    public void clearCache() {
        // TODO:
        cache.clear();
    }
    

    public void interact(Object using) {
        if (using == null) {
            currentPage.prev(this);
        } else if (using instanceof Provider) {
            currentPage.goToLoginPage((Provider) using, this);
        } else if (using instanceof Token) {
            currentPage.verifyToken((Token) using, this);
        }
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Page currentPage) {
        this.currentPage = currentPage;
    }

    public Page getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(Page previousPage) {
        this.previousPage = previousPage;
    }

    public ClientApp getCurrentApp() {
        return currentApp;
    }

    public void setCurrentApp(ClientApp currentApp) {
        this.currentApp = currentApp;
    }

    public List<Token> getCache() {
        return cache;
    }

    public void setCache(List<Token> cache) {
        this.cache = cache;
    }
    public void addCache(Token token) {
        this.cache.add(token);
    }

    
}
