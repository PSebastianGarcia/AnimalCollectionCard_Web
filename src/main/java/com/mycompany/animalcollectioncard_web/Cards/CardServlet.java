package com.mycompany.animalcollectioncard_web.Cards;

import com.mycompany.animalcollectioncard_web.User.PermanentUser;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

@WebServlet(name = "CardServlet", urlPatterns = {"/card"})
public class CardServlet extends HttpServlet {

    private final String URI_LIST = "WEB-INF/User/Common/userPage.jsp";
    private final String URI_EDIT = "WEB-INF/animals/editCard.jsp";
    private final String URI_DELETE = "WEB-INF/animals/deleteCard.jsp";
    private final String URI_DELETE_CARDUSER = "WEB-INF/User/Admin/deleteCardToUser.jsp";
    private final String URI_INSERT_CARDUSER = "WEB-INF/User/Admin/insertCardToUser.jsp";

    CardModel cModel = new CardModel();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        PermanentUser pUser = (PermanentUser) session.getAttribute("pUser");
        int id;
        AnimalCard card;
        String action = req.getParameter("action");
        action = action == null ? "" : action;
        switch (action) {
            case "insert":
                if (!pUser.isAdmin()) {
                    id = Integer.parseInt(req.getParameter("id"));
                    cModel.addUserCard(pUser.getId(), id);
                    cModel.disableDeck(pUser.getId());
                    resp.sendRedirect(getServletContext().getContextPath() + "/card");
                } else {
                    id = Integer.parseInt(req.getParameter("id"));
                    int idUser = Integer.parseInt(req.getParameter("idUser"));
                    req.setAttribute("uID", idUser);
                    req.setAttribute("cID", id);
                    req.getRequestDispatcher(URI_INSERT_CARDUSER).forward(req, resp);
                }
                break;
            case "edit":
                if (pUser.isAdmin()) {
                    id = Integer.parseInt(req.getParameter("id"));
                    card = cModel.getCard(id);
                    req.setAttribute("animal", card.getAnimal());
                    req.getRequestDispatcher(URI_EDIT).forward(req, resp);
                }
                break;
            case "delete":
                if (pUser.isAdmin()) {
                    String detail = req.getParameter("detail");
                    detail = detail == null ? "" : detail;
                    id = Integer.parseInt(req.getParameter("id"));
                    card = cModel.getCard(id);
                    ArrayList<AnimalCard> cards = new ArrayList<>();
                    cards.add(card);
                    switch (detail) {
                        case "card":
                            req.setAttribute("cardList", cards);
                            req.getRequestDispatcher(URI_DELETE).forward(req, resp);
                            break;
                        case "user":
                            int idUser = Integer.parseInt(req.getParameter("idUser"));
                            req.setAttribute("uID", idUser);
                            req.setAttribute("cID", id);
                            req.getRequestDispatcher(URI_DELETE_CARDUSER).forward(req, resp);
                            break;
                    }
                }
                break;
            default:
                String reverseDeckClass = "do_not_work";
                ArrayList<AnimalCard> deck = cModel.getUserDeck(pUser.getId());
                final int EMPTY_CARD = 4;
                if (deck.isEmpty()) {
                    deck.add(cModel.getCard(EMPTY_CARD));
                } else if (deck.get(0).getId() != EMPTY_CARD) {
                    reverseDeckClass = "it_is_work";
                }
                req.setAttribute("work", reverseDeckClass);
                req.setAttribute("deck", deck);
                req.setAttribute("cardList", cModel.getCardsByUser(pUser));
                req.getRequestDispatcher(URI_LIST).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        PermanentUser pUser = (PermanentUser) session.getAttribute("pUser");
        Animal anim;
        int id;
        String action = req.getParameter("action");
        action = action == null ? "" : action;
        String detail = req.getParameter("detail");
        detail = detail == null ? "" : detail;
        switch (action) {
            case "insert":
                if (pUser.isAdmin()) {
                    switch (detail) {
                        case "card":
                            id = Integer.parseInt(req.getParameter("IDAnim"));
                            cModel.addAnimalCard(id);
                            break;
                        case "animal":
                            anim = getAnimalByParameters(req);
                            cModel.addAnimal(anim);
                            break;
                        case "user":
                            id = Integer.parseInt(req.getParameter("id"));
                            int idUser = Integer.parseInt(req.getParameter("idUser"));
                            cModel.addUserCard(idUser, id);
                            break;
                    }
                }
                break;
            case "update":
                if (pUser.isAdmin()) {
                    id = Integer.parseInt(req.getParameter("id"));
                    anim = getAnimalByParameters(req);
                    anim.setId(id);
                    cModel.updateAnimal(anim);
                }
                break;
            case "delete":
                id = Integer.parseInt(req.getParameter("id"));
                if (pUser.isAdmin()) {
                    switch (detail) {
                        case "card":
                            cModel.removeAnimalCard(id);
                            break;
                        case "animal":
                            cModel.removeAnimal(id);
                            break;
                        case "user":
                            int idUser = Integer.parseInt(req.getParameter("idUser"));
                            cModel.removeUserCard(id, idUser);
                            break;
                        default:
                            resp.sendRedirect("/user");
                    }
                } else {

                    cModel.removeUserCard(id, pUser.getId());
                }
                break;
        }
        if (pUser.isAdmin()) {
            resp.sendRedirect(getServletContext().getContextPath() + "/user");
        } else {
            resp.sendRedirect(getServletContext().getContextPath() + "/card");
        }
    }

    private Animal getAnimalByParameters(HttpServletRequest req) {
        String name = req.getParameter("name");
        String genus = req.getParameter("genus");
        String species = req.getParameter("species");
        String foto = req.getParameter("foto");

        return new Animal(name, genus, species, foto);
    }
}
