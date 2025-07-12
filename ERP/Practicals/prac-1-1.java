import java.sql.*;

public class InsertJDBC {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/StudentDB";
        String username = "root";
        String password = "root";
        

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection conn = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Database connected!");

         // Insert
            String insertSQL = "INSERT INTO students (name, age) VALUES ('Ashish', 20)";
            Statement insertStmt = conn.createStatement();
            insertStmt.execute(insertSQL);
            System.out.println("Record inserted!");

            // Retrieve and display records
            String selectQuery = "SELECT * FROM students";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectQuery);

            System.out.println("\nStudent Records:");
            while (rs.next()) {
                System.out.printf("ID: %d, Name: %s, Age: %d%n", 
                                  rs.getInt(1), rs.getString("name"), rs.getInt("age"));
            }

            // Close resources 
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    }
}
