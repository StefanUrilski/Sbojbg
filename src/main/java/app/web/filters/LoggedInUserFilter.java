package app.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({ "/",
        "/faces/view/admin/*",
        "/faces/view/index.xhtml",
        "/faces/view/login.xhtml",
        "/faces/view/register.xhtml"
})
public class LoggedInUserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (req.getSession().getAttribute("username") != null &&
                req.getSession().getAttribute("role").equals("User")) {
            resp.sendRedirect("/faces/view/home.xhtml");
        } else {
            chain.doFilter(req, resp);
        }
    }
}
