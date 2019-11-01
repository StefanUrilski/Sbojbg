package app.web.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class UserLogoutBean extends BaseBean {

    public void logout() {
        session().invalidate();

        redirect("/index");
    }
}
