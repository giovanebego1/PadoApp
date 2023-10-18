import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dbConnector {
    private static final String DB_URL = "jdbc:sqlite:/C:\\CodeTools\\sqlite\\tools\\sqlite-tools-win32-x86-3430000\\jornada.db";

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL);

            // Realizar a consulta e imprimir os resultados
            retrieveResidents(connection);

            // Fechar a conexão
            connection.close();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados");
            e.printStackTrace();
        }
    }

    private static void retrieveResidents(Connection connection) {
        String sql = "SELECT * FROM residents";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                String id = result.getString("resident_id");
                String email = result.getString("resident_email");
                String phone = result.getString("resident_phone");

                System.out.println(id + "|" + email + "|" + phone);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao executar a consulta");
            e.printStackTrace();
        }
    }
}
