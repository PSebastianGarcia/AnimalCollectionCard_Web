package com.mycompany.animalcollectioncard_web.User;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.StringWriter;

@WebServlet(name = "MailServlet", urlPatterns = {"/mail"})
public class MailServlet extends HttpServlet {

    private MailModel mModel = new MailModel();
    
    private final String URI_MAIL_TOKEN_NOTICE = "/WEB-INF/User/mailNoticeTokenAwait.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        TemporalUser user = (TemporalUser) req.getAttribute("user");
        String token = user.getToken();
        String content = getStringWriter(req, resp, token);
        mModel.sendEmail(user, content);
        req.getRequestDispatcher(URI_MAIL_TOKEN_NOTICE).forward(req, resp);
    }

    private String getStringWriter(HttpServletRequest req, HttpServletResponse resp, String token) {
        try (StringWriter stringWriter = new StringWriter();) {
            HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(resp) {
                @Override
                public PrintWriter getWriter() throws IOException {
                    return new PrintWriter(stringWriter);
                }
            };
            String url = "localhost:8080/AnimalCollection_Web/SignIn?token=" + token;
            
            req.setAttribute("confirmationLink", url);
            req.setAttribute("token", token);
            req.getRequestDispatcher("/WEB-INF/User/confirmationMail.jsp").include(req, responseWrapper);
            return stringWriter.toString();
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo generar el contenido del email", ex);
        }
    }

}
