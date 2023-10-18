import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class Main {
    private static final String DB_URL = "jdbc:sqlite:/C:\\CodeTools\\sqlite\\tools\\sqlite-tools-win32-x86-3430000\\jornada.db";

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL);

            // Gerar um novo ID automaticamente
            int newId = generateNewId(connection);

            // Gerar um e-mail e número de telefone aleatórios
            String randomEmail = generateRandomEmail();
            String randomPhone = generateRandomPhone();

            // Adicionar um novo usuário com o ID gerado e os dados aleatórios
            userCreation newUser = new userCreation(newId, randomEmail, randomPhone);
            newUser.saveToDatabase(connection);

            // Realizar a consulta e imprimir os resultados
            retrieveResidents(connection);

            // Fechar a conexão
            connection.close();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados");
            e.printStackTrace();
        }
    }

    private static int generateNewId(Connection connection) throws SQLException {
        String sql = "SELECT MAX(resident_id) FROM residents";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            if (result.next()) {
                int maxId = result.getInt(1);
                return maxId + 1;
            }
        }

        // Se não houver nenhum ID, comece com 1
        return 1;
    }


    private static String generateRandomEmail() {
        String[] domains = { "gmail.com", "yahoo.com", "outlook.com", "example.com", "mail.com" };
        Random random = new Random();
        String randomDomain = domains[random.nextInt(domains.length)];
        return "user" + random.nextInt(1000) + "@" + randomDomain;
    }

    private static String generateRandomPhone() {
        Random random = new Random();
        StringBuilder phoneNumber = new StringBuilder("+");
        phoneNumber.append(1 + random.nextInt(9)); // First digit
        for (int i = 0; i < 9; i++) {
            phoneNumber.append(random.nextInt(10)); // Rest of the digits
        }
        return phoneNumber.toString();
    }

    private static void retrieveResidents(Connection connection) {
        // Método para recuperar e imprimir os residentes, sem alterações
        // ...
    }
}
