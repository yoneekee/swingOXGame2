package Main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import DAO.QuizDAO;
import DTO.QuizDTO;

public class OXGame extends JFrame {
	boolean myChoice;
	int answerSum;
	boolean result;
	QuizDAO dao = new QuizDAO();
	
     public OXGame(QuizDTO dto) {
    	// declare, initialize
    	result = dto.isAnswer();
    	myChoice = true;
    	answerSum = 0;
    	 
    	// total conatiner
		Container contentPane = getContentPane();
		
		// north panel
		JPanel northPanel = new JPanel();
		JLabel title = new JLabel("「OXクイズ」アプリ：CRUD");
		
		// center panel
	    JPanel centerPanel = new JPanel(new BorderLayout());
	    JPanel centerPanelNorth = new JPanel(new GridLayout(3, 1, 0, 10)); 
	    JLabel centerTitle = new JLabel("タイトル");
	    JLabel quizTitle = new JLabel("クイズの内容");
	    JLabel quizContent = new JLabel(dto.getQuestion());
	    JPanel centerPanelCenter = new JPanel(new GridLayout(2, 3, 20, 20)); 
	    JLabel answerTitle = new JLabel("クイズの解答");
	    JButton answerO = new JButton("O");
	    JButton answerX = new JButton("X");
	    JLabel answerPerson = new JLabel("作成者");
	    JLabel answerPersonWho = new JLabel(dto.getAuthor());
	    JButton sousin = new JButton("送信");
	    
	    // south panel
	    JPanel southPanel = new JPanel(new BorderLayout());
	    JPanel southPanelTable = new JPanel(new GridLayout(2, 6, 20, 10));
	    JLabel register = new JLabel("[ 登録クイズ一覧：プレイ ]");
	    JLabel registerNotice = new JLabel("登録されているクイズはありません。");
	    
	    JLabel id = new JLabel(Integer.toString(dto.getId()));
	    JTextField naiyou = new JTextField(3);
	    JTextField kaitou = new JTextField(3);
	    JLabel sakuseisha = new JLabel(dto.getAuthor());
	    
	    JButton hensyu = new JButton("編集");
	    JButton sakujyo = new JButton("削除");
	    
	    
    	 setTitle("OXクイズアプリ：CRUD");
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setSize(850, 450);
         setVisible(true);

         contentPane.setLayout(new BorderLayout(20, 20));

         // Top Title
         title.setFont(new Font("Meiryo", Font.BOLD, 28));
         northPanel.add(title);

         // North Panel
         centerTitle.setFont(new Font("Meiryo", Font.PLAIN, 20));
         centerPanelNorth.add(centerTitle);
         centerPanelNorth.add(quizTitle);
         centerPanelNorth.add(quizContent);

         // Center Panel
         centerPanelCenter.add(answerTitle);
         centerPanelCenter.add(answerO);
         centerPanelCenter.add(answerX);

         centerPanelCenter.add(answerPerson);
         centerPanelCenter.add(answerPersonWho);
         centerPanelCenter.add(sousin);
         
         centerPanel.add(centerPanelNorth, BorderLayout.NORTH);
         centerPanel.add(centerPanelCenter, BorderLayout.SOUTH);

         // South Panel
         southPanelTable.add(new JLabel("ID"));
         southPanelTable.add(new JLabel("内容"));
         southPanelTable.add(new JLabel("解答"));
         southPanelTable.add(new JLabel("作成者"));
         southPanelTable.add(new JLabel("編集"));
         southPanelTable.add(new JLabel("削除"));
         
         southPanelTable.add(id);
         southPanelTable.add(naiyou);
         southPanelTable.add(kaitou);
         southPanelTable.add(sakuseisha);
         southPanelTable.add(hensyu);
         southPanelTable.add(sakujyo);
         
         register.setFont(new Font("Meiryo", Font.PLAIN, 16));
         southPanel.add(register, BorderLayout.NORTH);
         southPanel.add(southPanelTable, BorderLayout.CENTER);
         southPanel.add(registerNotice, BorderLayout.SOUTH);
         
         // Put components into contentPane using BorderLayout
         contentPane.add(northPanel, BorderLayout.NORTH);
         contentPane.add(centerPanel, BorderLayout.CENTER);
         contentPane.add(southPanel, BorderLayout.SOUTH);
         
         
         answerO.addMouseListener(
        		
         		new MouseAdapter() {
         			public void mouseClicked(MouseEvent e) {
         				myChoice = true;
         				if(myChoice != result) {
         					QuizDTO newDto = new QuizDTO();
         					newDto = dao.getRandomData();
         					quizContent.setText(newDto.getQuestion());
         					result = newDto.isAnswer();
         					id.setText(Integer.toString(newDto.getId()));
         					sakuseisha.setText(newDto.getAuthor());
         					answerPersonWho.setText(newDto.getAuthor());
         					
         					centerTitle.setText("You are Wrong!");
         				} else {
         					answerSum++;
         					kaitou.setText(Integer.toString(answerSum));
         					
         					QuizDTO newDto = new QuizDTO();
         					newDto = dao.getRandomData();
         					quizContent.setText(newDto.getQuestion());
         					result = newDto.isAnswer();
         					id.setText(Integer.toString(newDto.getId()));
         					sakuseisha.setText(newDto.getAuthor());
         					answerPersonWho.setText(newDto.getAuthor());
         					
         					centerTitle.setText("You are Right!");
         				}
         			}
 				}
         );
         
         answerX.addMouseListener(
         		new MouseAdapter() {
         			public void mouseClicked(MouseEvent e) {
         				myChoice = false;
         				if(myChoice != result) {
         					QuizDTO newDto = new QuizDTO();
         					newDto = dao.getRandomData();
         					quizContent.setText(newDto.getQuestion());
         					result = newDto.isAnswer();
         					id.setText(Integer.toString(newDto.getId()));
         					sakuseisha.setText(newDto.getAuthor());
         					answerPersonWho.setText(newDto.getAuthor());
         					
         					centerTitle.setText("You are Wrong!");
         				} else {
         					answerSum++;
         					kaitou.setText(Integer.toString(answerSum));
         					
         					QuizDTO newDto = new QuizDTO();
         					newDto = dao.getRandomData();
         					quizContent.setText(newDto.getQuestion());
         					result = newDto.isAnswer();
         					id.setText(Integer.toString(newDto.getId()));
         					sakuseisha.setText(newDto.getAuthor());
         					answerPersonWho.setText(newDto.getAuthor());
         					
         					centerTitle.setText("You are Right!");
         				}
         			}
 				}
         );
         
         sousin.addMouseListener(
         		new MouseAdapter() {
         			public void mouseClicked(MouseEvent e) {
         				System.out.println("sousin clicked........");
         			}
 				}
         );
         
         hensyu.addMouseListener(
         		new MouseAdapter() {
         			public void mouseClicked(MouseEvent e) {
         				
         			}
 				}
         );
         
         hensyu.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 JFrame newFrame = new JFrame("New Window");
                 newFrame.setVisible(true);
                 newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                 
                 newFrame.setSize(430, 200);
                 JLabel label = new JLabel("新しい問題を入力してください。");
                 label.setHorizontalAlignment(SwingConstants.CENTER);
                 
                 JPanel input = new JPanel(new GridLayout(3, 2, 10, 10));
                 JTextField authorInput = new JTextField(4);
                 JTextField questionInput = new JTextField(4);
                 final String[] answer = { "" };
                 
                 // 라디오 버튼 생성
                 JRadioButton radioButton1 = new JRadioButton("YES");
                 JRadioButton radioButton2 = new JRadioButton("NO");

                 // 라디오 버튼 그룹 생성
                 ButtonGroup buttonGroup = new ButtonGroup();
                 buttonGroup.add(radioButton1);
                 buttonGroup.add(radioButton2);
                 JPanel answerInput = new JPanel();
                 
                 answerInput.add(radioButton1);
                 answerInput.add(radioButton2);
                 
                 ActionListener radioListener = event -> { 
                	    JRadioButton source = (JRadioButton) event.getSource();
                	    answer[0] = source.getText();
                	    System.out.println("answer :: " + answer);
                };

     	        
     	        radioButton1.addActionListener(radioListener);
     	        radioButton2.addActionListener(radioListener);
                 
                 input.add(new JLabel("Author"));
                 input.add(authorInput);
                 input.add(new JLabel("Question"));
                 input.add(questionInput);
                 input.add(new JLabel("Answer"));
                 input.add(answerInput);
                 
                 JButton newQbtn = new JButton("新問題送信");
                 newQbtn.setHorizontalAlignment(SwingConstants.CENTER);
                 
                 newFrame.add(label, BorderLayout.NORTH);
                 newFrame.add(input, BorderLayout.CENTER);
                 newFrame.add(newQbtn, BorderLayout.SOUTH);
                 
                 newQbtn.addMouseListener(new MouseAdapter() {
                	    public void mouseClicked(MouseEvent e) {
                	        String question = questionInput.getText();
                	        String author = authorInput.getText();

                	        // dao.newQuestionInput 메서드를 ActionListener 내부에서 호출
                	        int result = dao.newQuestionInput(question, author, answer[0]);
                	        if(result == 1) {
                	        	System.out.println("input success.");
                	        } else {
                	        	System.out.println("input failed.");
                	        }
                	    }
                	});
             }
         });
         
         sakujyo.addMouseListener(
         		new MouseAdapter() {
         			public void mouseClicked(MouseEvent e) {
         				//System.out.println("sakujyo clicked........");
         				answerSum = 0;
         				kaitou.setText(Integer.toString(answerSum));
         			}
 				}
         );
     }

    
}
