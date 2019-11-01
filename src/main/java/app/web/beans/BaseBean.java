package app.web.beans;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public abstract class BaseBean {

    private FacesContext facesContext = FacesContext.getCurrentInstance();

    protected void redirect(String url) {
        try {
            facesContext.getExternalContext()
                    .redirect(String.format("/views%s.xhtml", url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected HttpSession session() {
        return (HttpSession) facesContext.getExternalContext().getSession(false);
    }
}
