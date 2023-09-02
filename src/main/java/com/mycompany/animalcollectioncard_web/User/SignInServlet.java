package com.mycompany.animalcollectioncard_web.User;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "SignInServlet", urlPatterns = {"/signIn"})
public class SignInServlet extends HttpServlet {

    UserModel uModel = new UserModel();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        PermanentUser pUser = (PermanentUser) session.getAttribute("pUser");
        User user = getUser(req);
        String userName = user.getName();
        if (uModel.isUser(userName)) {
            user.setName(null);
            user.setPassword(null);
            req.setAttribute("user", user);
            if (pUser.isAdmin()) {
                resp.sendRedirect("/user");
            }
            req.getRequestDispatcher("signIn.jsp").forward(req, resp);
        }

        uModel.addTemporalUser(user);
        TemporalUser tUser = uModel.getTemporalUserByUserName(userName);

        if (req.getParameter("id") != null) {
            String token = tUser.getToken();
            resp.sendRedirect(req.getContextPath() + "/user?token=" + token + "&action=move&eCard=" + req.getParameter("eCard"));
        } else {
            req.setAttribute("user", tUser);
            req.getRequestDispatcher("/mail").forward(req, resp);
        }
    }

    private User getUser(HttpServletRequest req) {
        String name = (req.getParameter("uName"));
        String pass = (req.getParameter("uPass"));
        String fName = (req.getParameter("fName"));
        String lName = (req.getParameter("lName"));
        String email = (req.getParameter("email"));
        return new User(name, pass, fName, lName, email);
    }
}
