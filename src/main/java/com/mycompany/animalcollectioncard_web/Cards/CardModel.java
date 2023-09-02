package com.mycompany.animalcollectioncard_web.Cards;

import com.mycompany.animalcollectioncard_web.ConnectionDataBase.ConnectionDB;
import com.mycompany.animalcollectioncard_web.User.PermanentUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class CardModel {

    private static final String CARDS_BY_USER = "SELECT ac.id AS animal_card_id, a.id AS animal_id, a.name AS animal_name, "
            + "a.genus AS animal_genus, a.species AS animal_species, a.foto AS animal_foto\n"
            + "FROM permanentuser u\n"
            + "JOIN cardsbyuser cu ON u.id = cu.user_id\n"
            + "JOIN animalcard ac ON cu.card_id = ac.id\n"
            + "JOIN animals a ON ac.animal_id = a.id\n"
            + "WHERE u.id = ?;";

    private static final String ADD_CARD_BY_USER = "INSERT INTO cardsbyuser (user_id, card_id) VALUES (?, ?);";

    private static final String GET_CARDS = "SELECT ac.id AS animal_card_id, a.id AS animal_id, a.name AS animal_name, "
            + "a.genus AS animal_genus, a.species AS animal_species, a.foto AS animal_foto\n"
            + "FROM animalcard ac\n"
            + "JOIN animals a ON ac.animal_id = a.id;";

    private static final String GET_CARD_BY_ID_QUERY = "SELECT ac.id AS animal_card_id, a.id AS animal_id, a.name AS animal_name, "
            + "a.genus AS animal_genus, a.species AS animal_species, a.foto AS animal_foto\n"
            + "FROM animalcard ac\n"
            + "JOIN animals a ON ac.animal_id = a.id\n"
            + "WHERE ac.id = ?;";

    private static final String ADD_ANIMAL_QUERY = "INSERT INTO animals (name, genus, species, foto) VALUES (?, ?, ?, ?)";
    private static final String GET_LAST_INSERTED_ID_QUERY = "SELECT LAST_INSERT_ID() AS last_id";
    private static final String ADD_CARD_QUERY = "INSERT INTO animalcard (animal_id) VALUES (?)";

    private static final String UPDATE_CARD_QUERY = "UPDATE animals SET name=?, genus=?, species=?, foto=? WHERE id=?";
    private static final String UPDATE_CARD_TO_EMPTYANIMAL = "UPDATE animalcard SET id_animal = ? WHERE id_animal = ?;";

    private static final String UPDATE_DECK = "UPDATE deckbyuser SET id_card_1 = ?, id_card_2 = ?, id_card_3 = ? WHERE id_user = ?;";

    private static final String GET_EDGES_CARDS = "SELECT MIN(id) AS min, MAX(id) AS max FROM animalcard;";
    private static final String GET_DECKCARDS_POOL = "SELECT ac.id FROM animalcard ac JOIN animals a ON ac.animal_id = a.id WHERE a.name != 'empty';";
    private static final String GET_USER_IDS_DECK_TABLE = "SELECT id_user FROM deckbyuser";
    private static final String GET_USER_DECK = "SELECT ac.id AS animal_card_id, a.id AS animal_id, a.name AS animal_name, a.genus AS animal_genus, a.species AS animal_species, a.foto AS animal_foto\n"
            + "FROM deckbyuser du\n"
            + "JOIN AnimalCard ac ON du.id_card_? = ac.id\n"
            + "JOIN animals a ON ac.animal_id = a.id\n"
            + "WHERE du.id_user = ?;";
    private static final String UPDATE_ID_DECK = "INSERT INTO deckbyuser (id_user, id_card_1, id_card_2, id_card_3) VALUES (?, ?, ?, ?)\";";

    private static final String DELETE_ANIMAL_QUERY = "DELETE FROM animals WHERE id = ?";
    private static final String DELETE_CARD_BY_USER = "DELETE FROM cardsbyuser\n"
            + "WHERE card_id = ? AND user_id = ?";
    private static final String DELETE_USER_CARDBYUSER = "DELETE FROM cardsbyuser\n"
            + "WHERE user_id = ?";
    private static final String DELETE_CARD_QUERY = "DELETE FROM animalcard WHERE id = ?";

    public CardModel() {
    }

    public ArrayList<AnimalCard> getCardsByUser(PermanentUser user) {

        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(CARDS_BY_USER);) {
            ArrayList<AnimalCard> cards = new ArrayList<>();
            int userId = user.getId();
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    int cardId = rs.getInt(1);
                    int animalId = rs.getInt(2);
                    String animalName = rs.getString(3);
                    String animalGenus = rs.getString(4);
                    String animalSpecies = rs.getString(5);
                    String animalFoto = rs.getString(6);

                    cards.add(new AnimalCard(cardId, new Animal(animalId, animalName, animalGenus, animalSpecies, animalFoto)));
                }
                return cards;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al traer lista de cartas por usuario " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }

    public ArrayList<AnimalCard> getCardList() {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(GET_CARDS); ResultSet rs = ps.executeQuery();) {
            ArrayList<AnimalCard> cards = new ArrayList<>();
            while (rs.next()) {
                AnimalCard card = rsToCard(rs);
                cards.add(card);
            }
            return cards;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al traer lista de cartas " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }

    public AnimalCard getCard(int id) {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(GET_CARD_BY_ID_QUERY);) {
            AnimalCard card = null;
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery();) {
                rs.next();
                card = rsToCard(rs);
                return card;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al leer card por id " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }

    public List<Integer> getIdDeckByUSer() {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(GET_USER_IDS_DECK_TABLE); ResultSet rs = ps.executeQuery();) {
            List<Integer> ids = new ArrayList<>();
            while (rs.next()) {
                ids.add(rs.getInt(1));
            }
            return ids;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al obtener los id de la lista de usuarios para el mazo " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }
    
    public int[] getAnimalCardIdEdges() {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(GET_EDGES_CARDS); ResultSet rs = ps.executeQuery();) {
            if (rs.next()) {
                return new int[]{rs.getInt("min"), rs.getInt("max")};
            } else {
                System.out.println("No se encontraron valores en el conjunto de resultados para los id extremos de las cartas ");
                throw new RuntimeException("Error interno");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al obtener los id extremo de la lista de cartas " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }

    public Set<Integer> getDeckCardsPool() {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(GET_DECKCARDS_POOL); ResultSet rs = ps.executeQuery();) {
            Set<Integer> idPool = new HashSet<>();

            while (rs.next()) {
                idPool.add(rs.getInt(1));
            }
            return idPool;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al obtener el pool de cartas del mazo " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }

    public ArrayList<AnimalCard> getUserDeck(int id) {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(GET_USER_DECK);) {
            ArrayList<AnimalCard> deck = new ArrayList<AnimalCard>();
            for (int i = 1; i <= 3; i++) {
                ps.setInt(1, i);
                ps.setInt(2, id);
                try (ResultSet rs = ps.executeQuery();) {
                    while (rs.next()) {
                        AnimalCard card = rsToCard(rs);
                        deck.add(card);
                    }
                }
            }
            return deck;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al obtener el mazo para el usuario " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }

    private int addNewUsersToDeckTable(Set<Integer> ids) {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(UPDATE_ID_DECK);) {
            int cantRegsAfectados = 0;
            for (Integer userId : ids) {
                final int EMPTY_CARD = 4;
                ps.setInt(1, userId);
                ps.setInt(2, EMPTY_CARD);
                ps.setInt(3, EMPTY_CARD);
                ps.setInt(4, EMPTY_CARD);

                cantRegsAfectados = ps.executeUpdate();
            }
            return cantRegsAfectados;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al insertar los id a la lista de usuarios para el mazo " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }

    public void setUserDeck(int idUser, int[] cards) {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(UPDATE_DECK);) {
            int idCard1 = cards[0];
            int idCard2 = cards[1];
            int idCard3 = cards[2];

            ps.setInt(1, idCard1);
            ps.setInt(2, idCard2);
            ps.setInt(3, idCard3);
            ps.setInt(4, idUser);

            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al definir mazo al usuario " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }
    
    public int addUserCard(int idUser, int idCard) {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(ADD_CARD_BY_USER)) {
            int cantRegsAfectados;
            ps.setInt(1, idUser);
            ps.setInt(2, idCard);
            cantRegsAfectados = ps.executeUpdate();
            return cantRegsAfectados;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Error al insertar en cardsbyuser: " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }
    
    public int addAnimal(Animal anim) {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement psInsert = con.prepareStatement(ADD_ANIMAL_QUERY); PreparedStatement psGetId = con.prepareStatement(GET_LAST_INSERTED_ID_QUERY)) {
            int cantRegsAfectados;
            fillPreparedStatement(psInsert, anim);
            cantRegsAfectados = psInsert.executeUpdate();

            //probablemente quede esta parte obsoleta junto a su PreparedStatement
            if (cantRegsAfectados > 0) {
                try (ResultSet rs = psGetId.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("last_id");
                    }
                }
            }
            return -1;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al crear animal " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }

    public int addAnimalCard(int idAnimal) {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(ADD_CARD_QUERY);) {
            int cantRegsAfectados;
            ps.setInt(1, idAnimal);
            cantRegsAfectados = ps.executeUpdate();
            return cantRegsAfectados;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al crear carta " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }
    
    public int disableDeck(int idUser) {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(UPDATE_DECK);) {
            int cantRegsAfectados;
            final int EMPTY_CARD = 4;

            for (int i = 1; i <= 3; i++) {
                ps.setInt(i, EMPTY_CARD);
            }
            ps.setInt(4, idUser);
            cantRegsAfectados = ps.executeUpdate();
            return cantRegsAfectados;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al definir mazo de caras vacías al usuario " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }
    
    public void updateIdUserInDeckByUserTable(Set<Integer> ids) {
        List<Integer> invertedList = new ArrayList<>(ids);
        Collections.reverse(invertedList);
        List<Integer> deckByUserIds = getIdDeckByUSer();
        Set<Integer> nonExistingIds = new HashSet<>();
        int i = 0;
        while (!invertedList.contains(deckByUserIds.get(i))) {
            nonExistingIds.add(deckByUserIds.get(i));
            i++;
        }
        addNewUsersToDeckTable(nonExistingIds);
    }

    public int updateAnimal(Animal anim) {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(UPDATE_CARD_QUERY);) {
            int cantRegsAfectados;
            fillPreparedStatement(ps, anim);
            ps.setInt(5, anim.getId());
            cantRegsAfectados = ps.executeUpdate();
            return cantRegsAfectados;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al editar animal " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }

    private int updateCardToEmptyAnimal(int idAnimal) {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(UPDATE_CARD_TO_EMPTYANIMAL)) {
            int cantRegsAfectados;

            final int EMPTY_ID_ANIMAL = 123;

            ps.setInt(1, EMPTY_ID_ANIMAL);
            ps.setInt(2, idAnimal);

            cantRegsAfectados = ps.executeUpdate();
            return cantRegsAfectados;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Error al actualizar AnimalCard " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }
    
    public int removeAnimalCard(int id) {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(DELETE_CARD_QUERY);) {
            int cantRegsAfectados;
            ps.setInt(1, id);
            cantRegsAfectados = ps.executeUpdate();
            return cantRegsAfectados;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al borrar carta " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }

    public void removeAnimal(int idAnimal) {
        updateCardToEmptyAnimal(idAnimal);
        deleteAnimal(idAnimal);
    }

    private int deleteAnimal(int id) {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(DELETE_ANIMAL_QUERY);) {
            int cantRegsAfectados;
            ps.setInt(1, id);
            cantRegsAfectados = ps.executeUpdate();
            return cantRegsAfectados;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al borrar animal " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }

    public int removeUserCard(int idCard, int idUser) {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(DELETE_CARD_BY_USER);) {
            int cantRegsAfectados;
            ps.setInt(1, idCard);
            ps.setInt(2, idUser);
            cantRegsAfectados = ps.executeUpdate();
            return cantRegsAfectados;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al borrar card del usuario " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }

    public int removeUserFromCardByUser(int idUser) {
        try (Connection con = ConnectionDB.getConnection(); PreparedStatement ps = con.prepareStatement(DELETE_USER_CARDBYUSER);) {
            int cantRegsAfectados;
            ps.setInt(1, idUser);
            cantRegsAfectados = ps.executeUpdate();
            return cantRegsAfectados;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al borrar usuario de cardbyuser " + ex.getMessage());
            throw new RuntimeException("Error interno");
        }
    }

    private AnimalCard rsToCard(ResultSet rs) throws SQLException {
        int cardId = rs.getInt(1);
        int animalId = rs.getInt(2);
        String name = rs.getString(3);
        String genus = rs.getString(4);
        String species = rs.getString(5);
        String foto = rs.getString(6);

        return new AnimalCard(cardId, new Animal(animalId, name, genus, species, foto));
    }

    private void fillPreparedStatement(PreparedStatement ps, Animal anim) throws SQLException {
        ps.setString(1, anim.getName());
        ps.setString(2, anim.getGenus());
        ps.setString(3, anim.getSpecies());
        ps.setString(4, anim.getFoto());
    }
    
    public int[] generateDeck(int[] idEdges, Set<Integer> idCards) {
        int min = idEdges[0];
        int max = idEdges[1];

        final int deckSize = 3;
        int[] deckCards = new int[deckSize];
        int count = 0;
        while (count < deckSize) {
            Random random = new Random();
            int idCard = random.nextInt(max - min + 1) + min;
            if (idCards.contains(idCard)) {
                deckCards[count] = idCard;
                count++;
            }
        }
        return deckCards;
    }
}
