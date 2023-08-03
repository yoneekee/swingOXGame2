package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.QuizDTO;

public class QuizDAO {
	
	// GRANT SELECT, INSERT, UPDATE, DELETE ON TABLE quiz TO testuser;
    private Connection cn = null;

    public QuizDAO() {
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
    public void addQuiz(QuizDTO quiz)  {
        String sql = "INSERT INTO quiz (question, answer, author) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = cn.prepareStatement(sql)) {
            pstmt.setString(1, quiz.getQuestion());
            pstmt.setBoolean(2, quiz.isAnswer());
            pstmt.setString(3, quiz.getAuthor());
            pstmt.executeUpdate();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    }

    // Read
    
    public QuizDTO oneDto(int id) {
    	QuizDTO quiz = new QuizDTO();
    	String sql = "SELECT * FROM quiz WHERE id = " + id;
    	try (PreparedStatement pstmt = cn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
    		
    		if(rs.next()) {
    			String question = rs.getString("question");
                boolean answer = rs.getBoolean("answer");
                String author = rs.getString("author");

                quiz.setQuestion(question);
                quiz.setAnswer(answer);
                quiz.setAuthor(author);
                quiz.setId(id);
    		}
            
    	} catch (SQLException e) {
        	e.printStackTrace();
        }
    	return quiz;
    }	
    
    public List<QuizDTO> getAllQuizzes() {
        List<QuizDTO> quizzes = new ArrayList<>();
        String sql = "SELECT * FROM quiz";
        try (PreparedStatement pstmt = cn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String question = rs.getString("question");
                boolean answer = rs.getBoolean("answer");
                String author = rs.getString("author");
                QuizDTO quiz = new QuizDTO();
                quiz.setQuestion(question);
                quiz.setAnswer(answer);
                quiz.setAuthor(author);
                quiz.setId(id);
                quizzes.add(quiz);
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return quizzes;
    }

    // Update
    public void updateQuiz(QuizDTO quiz) {
        String sql = "UPDATE quiz SET question=?, answer=?, author=? WHERE id=?";
        try (PreparedStatement pstmt = cn.prepareStatement(sql)) {
            pstmt.setString(1, quiz.getQuestion());
            pstmt.setBoolean(2, quiz.isAnswer());
            pstmt.setString(3, quiz.getAuthor());
            pstmt.setInt(4, quiz.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    }

    // Delete
    public void deleteQuiz(int id) {
        String sql = "DELETE FROM quiz WHERE id=?";
        try (PreparedStatement pstmt = cn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    }
    
    // Counting total question numbers
    public int getQuizCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM quiz";
        try (PreparedStatement pstmt = cn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return count;
    }
    
    public int getNextId() {
    	int maxCnt = 0;
        String sql = "SELECT MAX(id) FROM quiz";
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
    
    public int newQuestionInput(String question, String author, String answer) {
    	int result = 0;
    	QuizDAO dao = new QuizDAO();
    	int nextId = dao.getNextId();
    	Boolean inputAnswer;
    	
    	if(answer.equals("YES")) {
    		inputAnswer = true;
    	} else if (answer.equals("NO")){
    		inputAnswer = false;
    	} else {
    		inputAnswer = true;
    	}
    	
    	
    	String sql = "INSERT INTO quiz VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = cn.prepareStatement(sql)) {
        	pstmt.setInt(1, nextId);
            pstmt.setString(2, question);
            pstmt.setBoolean(3, inputAnswer);
            pstmt.setString(4, author);
            pstmt.executeUpdate();
            result = 1;
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    	return result;
    }
    
    public int getQuizRandomNum() {
    	QuizDAO dao = new QuizDAO();
    	int num = dao.getQuizCount();
    	int randomNum = (int) (Math.random() * num) + 1;
    	
    	return randomNum;
    }
    
    public QuizDTO getRandomData() {
    	QuizDTO dto = new QuizDTO();
    	QuizDAO dao = new QuizDAO();
    	
    	int randomNum = dao.getQuizRandomNum();
    	
    	String sql = "SELECT * FROM quiz WHERE id = " + randomNum;
    	try (PreparedStatement pstmt = cn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
    		
    		if (rs.next()) {
    			String question = rs.getString("question");
                boolean answer = rs.getBoolean("answer");
                String author = rs.getString("author");

                dto.setQuestion(question);
                dto.setAnswer(answer);
                dto.setAuthor(author);
                dto.setId(randomNum);
    		}
            
            
    	} catch (SQLException e) {
        	e.printStackTrace();
        }
    	
    	return dto;
    }
}
