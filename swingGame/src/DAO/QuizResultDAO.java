package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.QuizResultDTO;

public class QuizResultDAO {
	
	// GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE quizResult TO testuser;
    private Connection cn = null;

    public QuizResultDAO() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/testdb";
            String id  = "testuser";
            String psw = "testpass";
            cn = DriverManager.getConnection(url, id, psw);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create
    public int addQuizResult(QuizResultDTO quizResult) {
    	int result = 0;
        String sql = "INSERT INTO quizResult (id, author, gameResult) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = cn.prepareStatement(sql)) {
            pstmt.setInt(1, quizResult.getId());
            pstmt.setString(2, quizResult.getAuthor());
            pstmt.setInt(3, quizResult.getGameResult());
            pstmt.executeUpdate();
            result = 1;
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Read
    public QuizResultDTO getQuizResultById(int id) {
        QuizResultDTO quizResult = new QuizResultDTO();
        String sql = "SELECT * FROM quizResult WHERE id = " + id;
        try (PreparedStatement pstmt = cn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                String author = rs.getString("author");
                int gameResult = rs.getInt("gameResult");

                quizResult.setId(id);
                quizResult.setAuthor(author);
                quizResult.setGameResult(gameResult);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizResult;
    }

    public List<QuizResultDTO> getAllQuizResults() {
        List<QuizResultDTO> quizResults = new ArrayList<>();
        String sql = "SELECT * FROM quizResult ORDER BY quizResult DESC";
        try (PreparedStatement pstmt = cn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String author = rs.getString("author");
                int gameResult = rs.getInt("gameResult");

                QuizResultDTO quizResult = new QuizResultDTO();
                quizResult.setId(id);
                quizResult.setAuthor(author);
                quizResult.setGameResult(gameResult);

                quizResults.add(quizResult);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizResults;
    }

    // Update
    public void updateQuizResult(QuizResultDTO quizResult) {
        String sql = "UPDATE quizResult SET author=?, gameResult=? WHERE id=?";
        try (PreparedStatement pstmt = cn.prepareStatement(sql)) {
            pstmt.setString(1, quizResult.getAuthor());
            pstmt.setInt(2, quizResult.getGameResult());
            pstmt.setInt(3, quizResult.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void deleteQuizResult(int id) {
        String sql = "DELETE FROM quizResult WHERE id=?";
        try (PreparedStatement pstmt = cn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // getting next id number
    public int getNextId() {
    	int maxCnt = 0;
        String sql = "SELECT MAX(id) FROM quizResult";
        try (PreparedStatement pstmt = cn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
            	maxCnt = rs.getInt(1);
            } else {
            	maxCnt = 1;
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return maxCnt + 1;
    }
}
