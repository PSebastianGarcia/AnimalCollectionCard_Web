package com.mycompany.animalcollectioncard_web.User;

import com.mycompany.animalcollectioncard_web.Cards.CardModel;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@WebServlet(name = "UserServlet", urlPatterns = {"/user"})
public class UserServlet extends HttpServlet {

    private UserModel uModel = new UserModel();
    private CardModel cModel = new CardModel();

    private final String URI_LIST = "WEB-INF/User/Admin/adminPage.jsp";
    private final String URI_EDIT = "WEB-INF/User/Admin/editUser.jsp";
    private final String URI_DELETE = "WEB-INF/User/Admin/deleteUser.jsp";
    private final String URI_CONFIRMATION_MAIL = "WEB-INF/User/mailConfirmed.jsp";
    private final String URI_TIMEDOUT_TOKEN = "WEB-INF/User/timedOutTokenNotice.jsp";
    private final String URI_DECK_EVENT = "WEB-INF/User/Admin/deckEventConfirmation.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        action = action == null ? "" : action;
        String detail = req.getParameter("detail");
        detail = detail == null ? "" : detail;
        User user;
        PermanentUser targetPUser;
        TemporalUser targetTUser;
        int id;
        HttpSession session = req.getSession();
        PermanentUser pUser = (PermanentUser) session.getAttribute("pUser");

        switch (action) {
            case "move":
                user = uModel.getUserByToken(req.getParameter("token"));
                if (user == null) {
                    req.getRequestDispatcher(URI_TIMEDOUT_TOKEN).forward(req, resp);
                } else {
                    int idSignUser = uModel.moveUser(user);
                    int[] edgeCards = cModel.getAnimalCardIdEdges();
                    Set<Integer> cardsPool = cModel.getDeckCardsPool();
                    int[] cards = cModel.generateDeck(edgeCards, cardsPool);
                    cModel.setUserDeck(idSignUser, cards);
                    boolean isAdmin = false;

                    if (pUser != null && pUser.isAdmin()) {
                        isAdmin = true;
                        boolean initCard = Boolean.parseBoolean(req.getParameter("eCard"));

                        if (initCard) {
                            final int EMPTY_CARD_ID = 4;
                            cModel.addUserCard(idSignUser, EMPTY_CARD_ID);
                        }
                    }
                    req.setAttribute("admin", isAdmin);
                    req.getRequestDispatcher(URI_CONFIRMATION_MAIL).forward(req, resp);
                }
                break;
            case "edit":
                id = Integer.parseInt(req.getParameter("id"));
                if (pUser.isAdmin()) {
                    targetPUser = uModel.getPermanentUser(id);
                    req.setAttribute("user", targetPUser);
                    req.getRequestDispatcher(URI_EDIT).forward(req, resp);
                } else {
                    resp.sendRedirect(getServletContext().getContextPath() + "/user");
                }
                break;
            case "delete":
                id = Integer.parseInt(req.getParameter("id"));
                if (pUser.isAdmin()) {
                    if (detail.equals("temporal")) {
                        targetTUser = uModel.getTemporalUser(id);
                        req.setAttribute("user", targetTUser);
                        req.setAttribute("type", "tempUser");
                    } else {
                        targetPUser = uModel.getPermanentUser(id);
                        req.setAttribute("user", targetPUser);
                        req.setAttribute("type", "permUser");
                    }
                    req.getRequestDispatcher(URI_DELETE).forward(req, resp);
                } else {
                    resp.sendRedirect(getServletContext().getContextPath() + "/user");
                }
                break;
            case "deckEvent":
                if (pUser.isAdmin()) {
                    req.getRequestDispatcher(URI_DECK_EVENT).forward(req, resp);
                }
                break;
            default:
                if (pUser.isAdmin()) {
                    req.setAttribute("idAdmin", pUser.getId());
                    CardModel cModel = new CardModel();
                    req.setAttribute("tempUserList", uModel.getTemporalUserList());
                    req.setAttribute("userList", uModel.getPermanentUserList());
                    req.setAttribute("cardList", cModel.getCardList());
                    List<UserCards> cardsByUserList = uModel.getCardsByUser();
                    if (cardsByUserList.isEmpty()) {
                        req.setAttribute("cardsByUser", null);
                    } else {
                        req.setAttribute("cardsByUser", cardsByUserList);
                    }
                    req.getRequestDispatcher(URI_LIST).forward(req, resp);
                } else {
                    resp.sendRedirect(getServletContext().getContextPath() + "/card");
                }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        action = action == null ? "" : action;
        String detail = req.getParameter("detail");
        detail = detail == null ? "" : detail;
        int id;

        switch (action) {
            case "update":
                id = Integer.parseInt(req.getParameter("id"));
                PermanentUser targetUser = getUserByParameters(req);
                if (uModel.isUser(targetUser.getName())) {
                    targetUser.setName(null);
                    if (uModel.isMailRecorded(targetUser.getEmail())) {
                        targetUser.setEmail(null);
                    }
                    req.setAttribute("user", targetUser);
                    req.getRequestDispatcher("editUser.jsp").forward(req, resp);
                }
                targetUser.setId(id);
                uModel.updateUser(targetUser);
                break;
            case "delete":
                id = Integer.parseInt(req.getParameter("id"));
                if (detail.equals("temporal")) {
                    uModel.removeTemporalUser(id);
                } else {
                    uModel.removeUserFromPool(id);
                    cModel.removeUserFromCardByUser(id);
                    uModel.removePermanentUser(id);
                }

                break;
            case "deckEvent":
                int[] edgeCards = cModel.getAnimalCardIdEdges();
                Set<Integer> cardsPool = cModel.getDeckCardsPool();
                Set<Integer> idUsers = uModel.getNoAdminUserList();
                if (!idUsers.isEmpty()) {
                    cModel.updateIdUserInDeckByUserTable(idUsers);

                    for (int uId : idUsers) {
                        int[] cards = cModel.generateDeck(edgeCards, cardsPool);
                        cModel.setUserDeck(uId, cards);
                    }
                }
                break;
        }
        resp.sendRedirect(getServletContext().getContextPath() + "/user");
    }

    private PermanentUser getUserByParameters(HttpServletRequest req) {
        PermanentUser user = new PermanentUser();
        user.setName(req.getParameter("name"));
        user.setPassword(req.getParameter("password"));
        user.setFirstName(req.getParameter("fName"));
        user.setLastName(req.getParameter("lName"));
        user.setEmail(req.getParameter("email"));
        String admin = req.getParameter("uAdmin");
        if (admin.equals("true") || admin.equals("false")) {
            user.setAdmin(Boolean.parseBoolean("uAdmin"));
        } else {
            user.setAdmin(false);
        }
        return user;
    }
}
