import java.sql.*;
import java.util.Scanner;

public class PreparedStatementJDBC {
	 public static void main(String[] args) {
	        String jdbcURL = "jdbc:mysql://localhost:3306/StudentDB";
	        String username = "root";
	        String password = "root";
	        try (Scanner scanner = new Scanner(System.in)) {
				try {
				    // Load MySQL JDBC driver
				    Class.forName("com.mysql.cj.jdbc.Driver");

				    // Establish connection
				    Connection conn = DriverManager.getConnection(jdbcURL, username, password);
				    System.out.println("Database connected!");
				    
				    // User input
				    System.out.print("Enter name: ");
				    String name = scanner.nextLine();

				    System.out.print("Enter age: ");
				    int age = scanner.nextInt();
				    
				 // Insert using PreparedStatement
				    String insertQuery = "INSERT INTO students (name, age) VALUES (?, ?)";
				    PreparedStatement ps = conn.prepareStatement(insertQuery);
				    ps.setString(1, name);
				    ps.setInt(2, age);
				    ps.executeUpdate();
				    System.out.println("Student inserted using PreparedStatement.");

				    // Retrieve using ResultSet
				    String selectQuery = "SELECT * FROM students";
				    ResultSet rs = conn.createStatement().executeQuery(selectQuery);

				    System.out.println("\nStudent Records:");
				    while (rs.next()) {
				        System.out.printf("ID: %d | Name: %s | Age: %d\n",
				                rs.getInt(1), rs.getString("name"), rs.getInt("age"));
				    }

				} catch (Exception e) {
				    System.out.println(e);
				}
			}
	 }
}
