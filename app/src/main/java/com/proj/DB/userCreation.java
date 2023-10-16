import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class userCreation {
    private int id;
    private String email;
    private String phone;

    public userCreation(int id, String email, String phone) {
        this.id = id;
        this.email = email;
        this.phone = phone;
    }

    public void saveToDatabase(Connection connection) {
        String sql = "INSERT INTO residents (resident_id, resident_email, resident_phone) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setString(2, email);
            statement.setString(3, phone);
            statement.executeUpdate();
            System.out.println("Usuário adicionado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar usuário");
            e.printStackTrace();
        }
    }
}
