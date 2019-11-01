package app.web.mbeans;

        import javax.faces.context.FacesContext;
        import javax.servlet.http.HttpSession;
        import java.io.IOException;

public abstract class BaseBean {

    private static final FacesContext facesContext = FacesContext.getCurrentInstance();

    protected void redirect(String url)  {
        try {
            facesContext.getExternalContext()
                    .redirect("/views" + url + ".jsf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected HttpSession session() {
        return (HttpSession) facesContext.getExternalContext().getSession(true);
    }
}
