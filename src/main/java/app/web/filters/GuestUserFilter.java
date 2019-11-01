package app.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({
        "/faces/view/admin/*",
        "/faces/view/receipts/*",
        "/faces/view/packages/*",
})
public class GuestUserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (req.getSession().getAttribute("username") == null) {
            resp.sendRedirect("/faces/view/login.xhtml");
        } else {
            chain.doFilter(req, resp);
        }
    }
}
