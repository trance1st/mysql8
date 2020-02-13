import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserCredential uc = UserInteraction.getCredentialsFromUser();
        Connection c = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://" + uc.ip + ":" + uc.port +
                            "/" + uc.database,
                    uc.user,
                    uc.password);
        } catch (Exception e1) {
            System.out.println("EROARE!!!");
            System.out.println(e1.getMessage());
        }
        while (true) {
            String query = UserInteraction.getQuery();
            if (query.startsWith("select")) {
                try {
                    Statement st = c.createStatement();
                    ResultSet rs = st.executeQuery(query);
                    UserInteraction.showResult(rs);
                } catch (SQLException e) {
                    System.out.println("eroare");
                    System.out.println(e);
                }
            } else {
                try {
                    Statement st = c.createStatement();
                    st.execute(query);
                    System.out.println("Done");
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

}
