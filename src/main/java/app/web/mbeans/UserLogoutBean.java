package app.web.mbeans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class UserLogoutBean extends BaseBean {

    public void logout() {
        getSession().invalidate();

        redirect("/faces/view/index.xhtml");
    }
}
