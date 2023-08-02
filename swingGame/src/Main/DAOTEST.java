package Main;

import DAO.QuizDAO;
import DTO.QuizDTO;

public class DAOTEST {

	
	public static void main(String[] args) {
		QuizDTO dto = new QuizDTO();
		QuizDAO dao = new QuizDAO();

		int num = dao.getQuizCount();
		int randomNum = (int) (Math.random() * num) + 1;
		
		dto = dao.getRandomData();
		
		System.out.println("dao test num :: " + num);
		System.out.println("dao test random num :: " + randomNum);
		System.out.println("dao test dto :: " + dto.getQuestion());
	}
	
}
