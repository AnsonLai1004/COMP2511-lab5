package unsw.sso;

import java.util.ArrayList;
import java.util.List;

import unsw.sso.providers.Hoogle;
import unsw.sso.providers.LinkedOut;
import unsw.sso.providers.Provider;

public class Browser {

    private String currentPage = null;
    private String previousPage = null;
    private ClientApp currentApp = null;
    private List<Token> cache = new ArrayList<Token>();

    public void visit(ClientApp app) {
        this.previousPage = null;
        this.currentPage = "Select a Provider";
        this.currentApp = app;
        for (Token t : cache) {
            if (app.isValidToken(t)) {
                this.currentPage = "Home";
            }
        }
    }

    public String getCurrentPageName() {
        return this.currentPage;
    }

    public void clearCache() {
        // TODO:
        cache.clear();
    }
    

    public void interact(Object using) {
        if (using == null) {
            this.currentPage = this.previousPage;
            return;
        }

        switch (currentPage) {
            case "Select a Provider": {
                // if the currentApp doesn't have hoogle
                // then it has no providers, which just will prevent
                // transition.
                if (using instanceof Hoogle && currentApp.hasProvider((Provider) using)) {
                    this.previousPage = currentPage;
                    this.currentPage = "Hoogle Login";
                } else if (using instanceof LinkedOut && currentApp.hasProvider((Provider) using)) {
                    this.previousPage = currentPage;
                    this.currentPage = "LinkedOut Login";
                    // do nothing...
                }
                break;
            }
            case "Hoogle Login": {
                if (using instanceof Token) {
                    Token token = (Token) using;
                    if (token.getAccessToken() != null) {
                        this.previousPage = currentPage;
                        this.currentPage = "Home";
    
                        this.cache.add(token);
                        this.currentApp.registerUser((Token)token);
                    } else {
                        // If accessToken is null, then the user is not authenticated
                        // Go back to select providers page
                        this.currentPage = "Select a Provider";
                    }
                } else {
                    // do nothing...
                }

                break;
            }
            case "LinkedOut Login": {
                if (using instanceof Token) {
                    Token token = (Token) using;
                    if (token.getAccessToken() != null) {
                        this.previousPage = currentPage;
                        this.currentPage = "Home";
    
                        this.cache.add(token);
                        this.currentApp.registerUser((Token)token);
                    } else {
                        // If accessToken is null, then the user is not authenticated
                        // Go back to select providers page
                        this.currentPage = "Select a Provider";
                    }
                } else {
                    // do nothing...
                }

                break;
            }
            case "Home": {
                // no need to do anything
                break;
            }
        }
    }
}
