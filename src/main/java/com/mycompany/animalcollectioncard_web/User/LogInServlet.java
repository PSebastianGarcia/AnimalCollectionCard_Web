package com.mycompany.animalcollectioncard_web.User;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LogInServlet", urlPatterns = {"/logIn"})
public class LogInServlet extends HttpServlet {

    UserModel uModel = new UserModel();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");

        HttpSession session = req.getSession();
        if (action.equals("logOut")) {
            session.removeAttribute("user");
            session.invalidate();
            resp.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PermanentUser pUser = uModel.getUserByLogIn(req.getParameter("uName"), req.getParameter("uPass"));
        if (pUser == null) {
            resp.sendRedirect("logIn.jsp?alert=true");
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("pUser", pUser);
            resp.sendRedirect(req.getContextPath() + "/user");
        }
    }
}
