package com.mycompany.animalcollectioncard_web.User;

import com.mycompany.animalcollectioncard_web.ConnectionDataBase.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserModel {

    private static final String IS_USER_QUERY = "SELECT 1 FROM permanentuser WHERE name = ? UNION ALL SELECT 1 FROM temporaluser WHERE name = ? LIMIT 1";
    private static final String EXIST_MAIL_QUERY = "SELECT 1 FROM permanentuser WHERE email = ? UNION ALL SELECT 1 FROM temporaluser WHERE email = ? LIMIT 1";

    private static final String GET_PERMANENTUSERS = "SELECT * FROM permanentuser";
    private static final String GET_TEMPORALUSERS = "SELECT * FROM temporaluser";

    private static final String GET_TEMPORALUSER_BY_USER = "SELECT * FROM temporaluser WHERE name = ?";
    private static final String GET_PERMANENTUSER_BY_USER = "SELECT * FROM permanentuser WHERE name = ?";

    private static final String GET_PERMANENTUSER_BY_ID = "SELECT * FROM permanentuser WHERE id = ?";
    private static final String GET_TEMPORALUSER_BY_ID = "SELECT * FROM temporaluser WHERE id = ?";

    private static final String GET_PERMANENTUSER_BY_LOGIN = "SELECT * FROM permanentuser WHERE name = ? AND password = ?";

    private static final String GET_LAST_INSERTED_ID_QUERY = "SELECT LAST_INSERT_ID() AS last_id";

    private static final String ADD_TEMPORALUSER = "INSERT INTO temporaluser (name, password, firstName, lastName, email, token) "
            + "VALUES (?, ?, ?, ?, ?, UUID());";
    private static final String ADD_PERMANENTUSER = "INSERT INTO permanentuser (name, password, firstName, lastName, email, admin) "
            + "VALUES (?, ?, ?, ?, ?, false);";

    private static final String ADD_USER_TO_POOL = "INSERT INTO deckbyuser VALUES (?, 4, 4, 4);";
    private static final String REMOVE_USER_TO_POOL = "DELETE FROM deckbyuser WHERE id_user = ?;";

    private static final String GET_TEMPORALUSER_BY_TOKEN = "SELECT * FROM temporaluser WHERE token = ?";

    private static final String GET_IDUSER_NOADMIN = "SELECT id FROM permanentuser WHERE admin != true";

    private static final String UPDATE_PERMANENTUSER_QUERY = "UPDATE permanentuser SET name=?, password=?, firstName=?, lastName=?, email=?, admin=? WHERE id=?";

    private static final String DELETE_TEMPORALUSER_BY_ID = "DELETE FROM temporaluser WHERE id = ?";
    private static final String DELETE_PERMANENTUSER_BY_ID = "DELETE FROM permanentuser WHERE id = ?";

    private static final String GET_CARDS_AND_USER_ID = "SELECT u.id AS user_id, GROUP_CONCAT(ac.id ORDER BY ac.id) AS id_cards\n"
            + "FROM permanentuser u\n"
            + "LEFT JOIN cardsbyuser cu ON u.id = cu.user_id\n"
            + "LEFT JOIN animalcard ac ON cu.card_id = ac.id\n"
            + "WHERE u.admin = false\n"
            + "GROUP BY u.id;";

    public void addTemporalUser(User user) {
        addUser(user, ADD_TEMPORALUSER);
    }

    public int addPermanentUser(User user) {
        return addUser(user, ADD_PERMANENTUSER);
    }

    private int addUser(User user, String sql) {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(sql); PreparedStatement psGetId = con.prepareStatement(GET_LAST_INSERTED_ID_QUERY)) {
            int insertedRegistersAmount;
            fillPreparedStatement(ps, user);
            insertedRegistersAmount = ps.executeUpdate();

            if (insertedRegistersAmount > 0) {
                try (ResultSet rs = psGetId.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("last_id");
                    }
                }
            }
            return -1;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al agregar usuario: " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }

    private int addUserToPool(int id) {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(ADD_USER_TO_POOL)) {
            int addedRecords;
            ps.setInt(1, id);
            addedRecords = ps.executeUpdate();
            return addedRecords;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al insertar al usuario al pool " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }

    public List<PermanentUser> getPermanentUserList() {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(GET_PERMANENTUSERS); ResultSet rs = ps.executeQuery();) {
            ArrayList<PermanentUser> pUserList = new ArrayList<>();
            PermanentUser pUser;
            while (rs.next()) {

                pUser = rsToPermanentUser(rs);
                pUserList.add(pUser);
            }
            return pUserList;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al traer relación carta-usuario Permanente " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }

    public List<TemporalUser> getTemporalUserList() {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(GET_TEMPORALUSERS); ResultSet rs = ps.executeQuery();) {
            ArrayList<TemporalUser> tUserList = new ArrayList<>();
            TemporalUser tUser;
            while (rs.next()) {

                tUser = rsToTemporalUser(rs);
                tUserList.add(tUser);
            }
            return tUserList;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al traer relación carta-usuario Temporal " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }

    public List<UserCards> getCardsByUser() {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(GET_CARDS_AND_USER_ID); ResultSet rs = ps.executeQuery();) {
            ArrayList<UserCards> userPlusCardsIds = new ArrayList<>();
            while (rs.next()) {
                int userId = rs.getInt(1);
                String cardIds = rs.getString(2);

                UserCards uCards = new UserCards(userId);
                if (cardIds != null) {
                    String[] cardIdsList = cardIds.split(",");

                    for (String idS : cardIdsList) {
                        int id = Integer.parseInt(idS);
                        uCards.addCardIds(id);
                    }
                    userPlusCardsIds.add(uCards);
                }
            }
            return userPlusCardsIds;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al traer lista carta-usuario " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }

    public Set<Integer> getNoAdminUserList() {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(GET_IDUSER_NOADMIN); ResultSet rs = ps.executeQuery();) {
            Set<Integer> idUsers = new HashSet<>();

            while (rs.next()) {
                idUsers.add(rs.getInt(1));
            }
            return idUsers;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al traer usuarios no admin " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }

    public boolean isUser(String name) {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(IS_USER_QUERY);) {
            ps.setString(1, name);
            ps.setString(2, name);
            try (ResultSet rs = ps.executeQuery();) {
                return rs.next();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al comprobar usuario " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }

    public boolean isMailRecorded(String mail) {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(EXIST_MAIL_QUERY);) {
            ps.setString(1, mail);
            ps.setString(2, mail);
            try (ResultSet rs = ps.executeQuery();) {
                return rs.next();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al comprobar usuario " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }

    public PermanentUser getUserByLogIn(String userName, String userPass) {
        System.out.println(userName + " " + userPass);
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(GET_PERMANENTUSER_BY_LOGIN);) {
            PermanentUser pUser = null;
            ps.setString(1, userName);
            ps.setString(2, userPass);
            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {

                    pUser = rsToPermanentUser(rs);
                }
                return pUser;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al leer el usuario logeado " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }

    public TemporalUser getTemporalUserByUserName(String name) {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(GET_TEMPORALUSER_BY_USER);) {
            TemporalUser tUser = null;
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {

                    tUser = rsToTemporalUser(rs);
                }
                return tUser;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al traer usuario temporal por nombre " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }

    public PermanentUser getPermanentUserByUserName(String name) {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(GET_PERMANENTUSER_BY_USER);) {
            PermanentUser pUser = null;
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {

                    pUser = rsToPermanentUser(rs);
                }
                return pUser;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al traer usuario permanente por nombre " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }

    public PermanentUser getPermanentUser(int id) {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(GET_PERMANENTUSER_BY_ID);) {
            PermanentUser pUser = null;
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery();) {
                rs.next();

                pUser = rsToPermanentUser(rs);
                return pUser;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al traer usuario permanente" + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }
    
    public TemporalUser getTemporalUser(int id) {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(GET_TEMPORALUSER_BY_ID);) {
            TemporalUser tUser = null;
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery();) {
                rs.next();

                tUser = rsToTemporalUser(rs);
                return tUser;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al traer usuario temporal" + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }

    public TemporalUser getUserByToken(String mailToken) {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(GET_TEMPORALUSER_BY_TOKEN);) {
            TemporalUser tUser = null;
            ps.setString(1, mailToken);
            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    tUser = rsToTemporalUser(rs);
                }
                return tUser;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al traer usuario por token " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }

    public int removePermanentUser(int id) {
        return removeUserById(id, DELETE_PERMANENTUSER_BY_ID);
    }

    public int removeTemporalUser(int id) {
        return removeUserById(id, DELETE_TEMPORALUSER_BY_ID);
    }

    private int removeUserById(int id, String sql) {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            int removedRecords;
            ps.setInt(1, id);
            removedRecords = ps.executeUpdate();
            return removedRecords;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al borrar usuario " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }

    public int removeUserFromPool(int idUser) {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(REMOVE_USER_TO_POOL)) {
            int removedRecords;
            ps.setInt(1, idUser);
            removedRecords = ps.executeUpdate();
            return removedRecords;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al remover el usuario del pool " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }

    public int updateUser(PermanentUser user) {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(UPDATE_PERMANENTUSER_QUERY);) {
            int updatedRecords;
            ps.setInt(1, user.getId());
            fillPreparedStatement(ps, user);
            ps.setBoolean(6, user.isAdmin());
            ps.setInt(7, user.getId());
            updatedRecords = ps.executeUpdate();
            return updatedRecords;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al editar usuario " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }

    private PermanentUser rsToPermanentUser(ResultSet rs) throws SQLException {
        User user = rsToUser(rs);
        boolean admin = rs.getBoolean("admin");
        return new PermanentUser(user, admin);
    }

    private TemporalUser rsToTemporalUser(ResultSet rs) throws SQLException {
        User user = rsToUser(rs);
        String token = rs.getString("token");
        return new TemporalUser(user, token);
    }

    private User rsToUser(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String password = rs.getString("password");
        String firstName = rs.getString("firstName");
        String lastName = rs.getString("lastName");
        String email = rs.getString("email");

        return new User(id, name, password, firstName, lastName, email);
    }

    public int moveUser(User user) {
        int idPUser = addPermanentUser(user);
        addUserToPool(idPUser);
        removeTemporalUser(user.getId());
        return idPUser;
    }

    private void fillPreparedStatement(PreparedStatement ps, User user) throws SQLException {
        ps.setString(1, user.getName());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getFirstName());
        ps.setString(4, user.getLastName());
        ps.setString(5, user.getEmail());
    }
}
