//package tool;
//
//import java.io.IOException;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import bean.User;
//
//public class AuthorizationFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//        HttpSession session = req.getSession();
//        User user = (User) session.getAttribute("user");
//
//        if (user == null) {
//            res.sendRedirect("login.jsp");
//            return;
//        }
//
//        String uri = req.getRequestURI();
//        if (uri.contains("teacher") && !"teacher".equals(user.getRole())) {
//            res.sendRedirect("unauthorized.jsp");
//            return;
//        } else if (uri.contains("student") && !"student".equals(user.getRole())) {
//            res.sendRedirect("unauthorized.jsp");
//            return;
//        }
//
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    public void destroy() {
//    }
//}
