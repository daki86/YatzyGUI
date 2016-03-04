import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import java.util.ArrayList;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MyJFrame extends JFrame{
    
	private ArrayList<Player> player;
	private int totPlayers;
	private int currentPlayerIndex;
	private int minimumPlayersAllowed = 2;
	private int maximumPlayersAllowed = 5;	
	private int typeOfDiceIndex = 0;
	private int totDice = 5;
	private int totProtocolRows = 18;
	private String [] regularYatzyCombinations = {" Ones", " Twos", " Threes", " Fours", " Fives", " Sixs", " Total", " BONUS", 
											" One pair", " Two pair", " 3 of a kind", " 4 of a kind", " Full House", " Sm Straight ", " Lg Straight ", " Chance"," Yatzy"," Total Sum"};
	private String [] maxiYatzyCombinations = {" Ones", " Twos", " Threes", " Fours", " Fives", " Sixs", " Total", " BONUS", 
											" One pair", " Two pair", "Three pair", " 3 of a kind", " 4 of a kind", "5 of a kind", " Full House", "House", "Tower", " Sm Straight ", " Lg Straight ", "Full Straight", " Chance"," Yatzy"," Total Sum"};
	
	private Boolean shuffleDieIcon;
	private Boolean playMaxiYazty = false;
	private Random randomDieIcon;
	
	private String [] regularDiceIcon = {"dieOne", "dieTwo", "dieThree", "dieFour", "dieFive", "dieSix", "dieOneMarked", "dieTwoMarked", "dieThreeMarked", "dieFourMarked", "dieFiveMarked", "dieSixMarked"};
	private String [] binaryDiceIcon = {"binaryOne", "binaryTwo", "binaryThree", "binaryFour", "binaryFive", "binarySix", "binaryOneMarked", "binaryTwoMarked", "binaryThreeMarked", "binaryFourMarked", "binaryFiveMarked", "binarySixMarked"};
	private String [] romanNumeralDiceIcon = {"romanOne", "romanTwo", "romanThree", "romanFour", "romanFive", "romanSix", "romanOneMarked", "romanTwoMarked", "romanThreeMarked", "romanFourMarked", "romanFiveMarked", "romanSixMarked"};
	
	private JPanel contentPanel;
	private JPanel startPanel;
	private JPanel addPlayerToListPanel;
	private JPanel playGamePanel;
	private JPanel rulePanel;
	private JPanel centerPanel;
	private JPanel dicePanel;
	private JPanel protocolPanel;
	private JPanel rightPanel;
	private JPanel leftPanel;
	private JPanel topPanel;
	private JPanel bottomPanel;
	private JPanel leftBottomPanel;
	private JPanel rightBottomPanel;
    
	private JButton btnPlay;
	private JButton btnRules;
	private JButton btnExit;
	private JButton btnBack;
	private JButton btnQuit;
	private JButton btnAddPlayer;
	private JButton btnReset;
	private JButton btnRoll;
	private JButton btnPickRegularDie;
	private JButton btnPickRomanNumeralDie;
	private JButton btnPickBinaryDie;
	private JButton btnPickRandomDie;
	private JButton btnMaxiTitle;
	private JButton btnRegularTitle;
	private ArrayList <JButton> btnDie;
	private JButton [][] btnPlayerCombinationOnProtocol;
    
	
	private JLabel lblYatzyTitle;
	//private JLabel lblTrippleTitle;
	private JLabel lblRules;
	private JLabel lblPlayerList;
	private JLabel lblPlayerName;
	private JLabel lblRolls;
	private JLabel lblMinMaxPlayers;
	private JLabel [] lblSum;
	private JLabel [] lblNameOfYatzyCombos;
		
	private JTextField txtFieldPlayerName;
	
	private ImageIcon [][] selectedDieIcon;

	private ImageIcon randomDice = new ImageIcon(getClass().getClassLoader().getResource("randomDice.png"));
	private ImageIcon randomDiceMarked = new ImageIcon(getClass().getClassLoader().getResource("randomDiceMarked.png"));
	private ImageIcon rollingDieAnimation = new ImageIcon(getClass().getClassLoader().getResource("rollingDie.gif"));
	
	private ImageIcon yatzyTitle = new ImageIcon(getClass().getClassLoader().getResource("yatzyTitle.png"));
	private ImageIcon maxiTitle = new ImageIcon(getClass().getClassLoader().getResource("maxiTitle.png"));
	private ImageIcon maxiTitleMarked = new ImageIcon(getClass().getClassLoader().getResource("maxiTitleMarked.png"));
	private ImageIcon trippleTitle = new ImageIcon(getClass().getClassLoader().getResource("trippleTitle.png"));
	private ImageIcon regularTitle = new ImageIcon(getClass().getClassLoader().getResource("regularTitle.png"));
	private ImageIcon regularTitleMarked = new ImageIcon(getClass().getClassLoader().getResource("regularTitleMarked.png"));
	private ImageIcon stackedDice = new ImageIcon(getClass().getClassLoader().getResource("stackedDice.png")); 
	private ImageIcon stackedRedDice = new ImageIcon(getClass().getClassLoader().getResource("stackedRedDice.png"));
	
	private Color darkGreen = new Color(60,196,45);
	private Color darRed = new Color(167, 41, 41);
	
	private Dimension minFrameSize;
	
	private Timer timer;
	private CardLayout cl;
	private GridLayout gridProtocol;
    
    public MyJFrame () {
    	super();
    	initGui();
    }
    
    public void initGui(){
    	setTitle("Yatzy");
        //setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        minFrameSize = new Dimension(800, 600);
        cl = new CardLayout();
        
        contentPanel = new JPanel();
        contentPanel.setLayout(cl);

        this.add(contentPanel);
        contentPanel.add(getStartPanel(),"startPanel");
        contentPanel.add(getaddPlayerToListPanelPanel(), "addPlayerToListPanelPanel");
        contentPanel.add(getPlayYatzyPanel(), "playYatzyPanel");
        contentPanel.add(getRulePanel(), "rulePanel");
        cl.show(contentPanel, "startPanel");

        pack();
        setVisible(true);
        setMinimumSize(minFrameSize);
        setSize(800, 600);
    	setLocationRelativeTo(null);
    }
    
    public JPanel getStartPanel(){
        lblYatzyTitle = new JLabel();
        lblYatzyTitle.setIcon(yatzyTitle);
        lblYatzyTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
          
        btnPlay = new JButton("Play");
        btnPlay.setFocusable(false);
        btnPlay.setMaximumSize(new Dimension(80, btnPlay.getMinimumSize().height));
        btnPlay.setAlignmentX(Component.CENTER_ALIGNMENT);
          
        btnRules = new JButton("Rules");
        btnRules.setFocusable(false);
        btnRules.setMaximumSize(new Dimension(80, btnRules.getMinimumSize().height));
        btnRules.setAlignmentX(Component.CENTER_ALIGNMENT);
          
        btnExit = new JButton("Exit");
        btnExit.setFocusable(false);
        btnExit.setMaximumSize(new Dimension(80, btnExit.getMinimumSize().height));
        btnExit.setAlignmentX(Component.CENTER_ALIGNMENT);
        
    	startPanel = new JPanel();
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.PAGE_AXIS));   
        startPanel.setBackground(darkGreen);
        
    	startPanel.add(Box.createVerticalGlue());
    	startPanel.add(lblYatzyTitle);
    	startPanel.add(Box.createVerticalStrut(60));
        startPanel.add(btnPlay);
        startPanel.add(Box.createVerticalStrut(10));
        startPanel.add(btnRules);
        startPanel.add(Box.createVerticalStrut(70));
        startPanel.add(btnExit);
        startPanel.add(Box.createVerticalGlue());
        
        btnPlay.addActionListener(new ActionListener(){
              
        	public void actionPerformed(ActionEvent arg0){
        		cl.show(contentPanel, "addPlayerToListPanelPanel");
        	}
        });
          
        btnRules.addActionListener(new ActionListener(){
              
        	public void actionPerformed(ActionEvent arg0){
        		cl.show(contentPanel, "rulePanel");	
           	}
        });
         
        btnExit.addActionListener(new ActionListener(){
          
        	public void actionPerformed(ActionEvent arg0){
          		System.exit(0);
          	}
        });
        
        return startPanel;
    }
    
    public JPanel getaddPlayerToListPanelPanel(){
    	player = new ArrayList<>();
    	
    	selectedDieIcon = new ImageIcon [3][12];
    	
    	for(int i=0; i<3; i++){
    		for(int a=0; a<12; a++){
    			switch(i){
    				case 0:
    					selectedDieIcon[i][a] = new ImageIcon(getClass().getClassLoader().getResource(regularDiceIcon[a]+".png"));
    					break;
    				case 1:
    					selectedDieIcon[i][a] = new ImageIcon(getClass().getClassLoader().getResource(binaryDiceIcon[a]+".png"));
    					break;
    				case 2:
    					selectedDieIcon[i][a] = new ImageIcon(getClass().getClassLoader().getResource(romanNumeralDiceIcon[a]+".png"));
    					break;
    			}
    		}
    	}
    	
    	btnPickRegularDie = new JButton();
    	btnPickRegularDie.setIcon(selectedDieIcon[0][5]);
    	btnPickRegularDie.setFocusable(false);
    	btnPickRegularDie.setBorderPainted(false);
    	btnPickRegularDie.setContentAreaFilled(false);
    	btnPickRegularDie.setToolTipText("Regular dice");
    	
    	btnPickRegularDie.addActionListener(new ActionListener(){
    		
    		public void actionPerformed(ActionEvent arg0){
    			btnPickRegularDie.setIcon(selectedDieIcon[0][11]);
    			btnPickBinaryDie.setIcon(selectedDieIcon[1][5]);
    			btnPickRomanNumeralDie.setIcon(selectedDieIcon[2][5]);
    			typeOfDiceIndex = 0;
    		}
    	});
    	
    	btnPickBinaryDie = new JButton();
    	btnPickBinaryDie.setIcon(selectedDieIcon[1][5]);
    	btnPickBinaryDie.setFocusable(false);
    	btnPickBinaryDie.setBorderPainted(false);
    	btnPickBinaryDie.setContentAreaFilled(false);
    	btnPickBinaryDie.setToolTipText("Binary dice");
    	
    	btnPickBinaryDie.addActionListener(new ActionListener(){
    		
    		public void actionPerformed(ActionEvent arg0){
    			btnPickBinaryDie.setIcon(selectedDieIcon[1][11]);
    			btnPickRomanNumeralDie.setIcon(selectedDieIcon[2][5]);
    			btnPickRegularDie.setIcon(selectedDieIcon[0][5]);
    			typeOfDiceIndex = 1;
    		}
    	});
    	
    	btnPickRomanNumeralDie = new JButton();
    	btnPickRomanNumeralDie.setIcon(selectedDieIcon[2][5]);
    	btnPickRomanNumeralDie.setFocusable(false);
    	btnPickRomanNumeralDie.setBorderPainted(false);
    	btnPickRomanNumeralDie.setContentAreaFilled(false);
    	btnPickRomanNumeralDie.setToolTipText("Roman numeral dice");
    	
    	btnPickRomanNumeralDie.addActionListener(new ActionListener(){
    		
    		public void actionPerformed(ActionEvent arg0){
    			btnPickRomanNumeralDie.setIcon(selectedDieIcon[2][11]);
    			btnPickRegularDie.setIcon(selectedDieIcon[0][5]);
    			btnPickBinaryDie.setIcon(selectedDieIcon[1][5]);
    			typeOfDiceIndex = 2;
    		}
    	});
    	
    	lblPlayerList = new JLabel("<html><h2>PLAYER LIST</h2></html>");
    	lblPlayerList.setBorder(new EmptyBorder(new Insets(0,45,0,0)));
    	
    	lblMinMaxPlayers = new JLabel("<html><span style: color=white>MIN TWO PLAYERS MAX FIVE</span></html>");
    	lblMinMaxPlayers.setBackground(Color.BLACK);
    	lblMinMaxPlayers.setOpaque(true);
    	lblMinMaxPlayers.setMaximumSize(new Dimension(150, lblMinMaxPlayers.getMinimumSize().height));
    	lblMinMaxPlayers.setBorder(new EmptyBorder(new Insets(10,25,10,0)));
    	
    	txtFieldPlayerName = new JTextField();
    	txtFieldPlayerName.setMaximumSize(new Dimension(150, txtFieldPlayerName.getMinimumSize().height));
    	txtFieldPlayerName.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
    	txtFieldPlayerName.addKeyListener(new KeyAdapter(){
    		public void keyPressed(KeyEvent e){
    			
    			if(e.getKeyCode() == KeyEvent.VK_ENTER){
    				addPlayerToTheList();
    			}
    		}
    	});
    	
    	btnPlay = new JButton("Play");
    	btnPlay.setFocusable(false);
    	btnPlay.setMaximumSize(new Dimension(150, btnPlay.getMinimumSize().height));
    	btnPlay.setAlignmentX(Component.CENTER_ALIGNMENT);
    	btnPlay.setEnabled(false);
    	
    	btnAddPlayer = new JButton("Add player");
    	btnAddPlayer.setFocusable(false);
    	btnAddPlayer.setMaximumSize(new Dimension(150, btnAddPlayer.getMinimumSize().height));
    	btnAddPlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
    	btnBack = new JButton("Back");
        btnBack.setFocusable(false);
        btnBack.setMaximumSize(new Dimension(90, btnBack.getMinimumSize().height));
        btnBack.setAlignmentX(Component.RIGHT_ALIGNMENT);
    	
        addPlayerToListPanel = new JPanel(new BorderLayout()); 
    	addPlayerToListPanel.setBorder(new EmptyBorder(0,10,10,10));
    	addPlayerToListPanel.setBackground(darkGreen);
        
        topPanel = new JPanel(new FlowLayout());
        topPanel.setBackground(darkGreen);
        
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.PAGE_AXIS));
        bottomPanel.setBackground(darkGreen);
       
        bottomPanel.add(btnBack);
        
        leftPanel = new JPanel(new BorderLayout());
    	leftPanel.setBackground(Color.white);
    	leftPanel.setPreferredSize(new Dimension(220, 0));	
    	
    	leftPanel.add(lblPlayerList, BorderLayout.PAGE_START);  
    	leftPanel.add(lblMinMaxPlayers, BorderLayout.PAGE_END);
    	
    	rightPanel = new JPanel();
    	rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
    	rightPanel.setBackground(darkGreen);
    	
    	JLabel lblpickDiceType = new JLabel("<html><center><span style: color=black>PICK A DICE <br>TYPE</span></center></html>");
    	lblpickDiceType.setBorder(new EmptyBorder(0,12,0,0));
    	
    	//rightPanel.add(Box.createVerticalGlue());
    	rightPanel.add(lblpickDiceType);
    	rightPanel.add(Box.createVerticalStrut(10));
    	rightPanel.add(btnPickRegularDie);
    	rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(btnPickBinaryDie);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(btnPickRomanNumeralDie);
        rightPanel.add(Box.createVerticalGlue());
        
        centerPanel = new JPanel();
    	centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
    	centerPanel.setBackground(darkGreen);
        
        centerPanel.add(Box.createVerticalGlue());
        
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(txtFieldPlayerName);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(btnAddPlayer);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(btnPlay);
    	centerPanel.add(Box.createVerticalGlue());        
        
    	addPlayerToListPanel.add(topPanel, BorderLayout.PAGE_START);
    	addPlayerToListPanel.add(leftPanel, BorderLayout.LINE_START);
    	addPlayerToListPanel.add(centerPanel, BorderLayout.CENTER);
    	addPlayerToListPanel.add(rightPanel, BorderLayout.LINE_END);
    	addPlayerToListPanel.add(bottomPanel, BorderLayout.PAGE_END);
    	
    	btnAddPlayer.addActionListener(new ActionListener(){
    		
    		public void actionPerformed(ActionEvent arg0){
    			addPlayerToTheList();
    		}
    	});
    	
    	btnBack.addActionListener(new ActionListener(){
    		
    		public void actionPerformed(ActionEvent arg0){
    			resetCompontentsForAddPlayerToListPanel();
    			
    			cl.show(contentPanel, "startPanel");
    		}
    	});
    	
    	btnPlay.addActionListener(new ActionListener(){
    		
    		public void actionPerformed(ActionEvent arg0){
    			//maybe change methodeName
    			createGameField();
    			
    			cl.show(contentPanel, "playYatzyPanel");
    		}
    	});
    	
    	return addPlayerToListPanel;
    }
    
    public JPanel getPlayYatzyPanel(){
    	
    	currentPlayerIndex = 0;
    	
    	JLabel [] playName = new JLabel [player.size()];
    	
    	for(int i=0; i<player.size(); i++)
    		playName[i] = new JLabel(player.get(i).getName());
    	
    	gridProtocol = new GridLayout(18, 0);
    	
    	btnQuit = new JButton("Quit");
    	btnQuit.setFocusable(false);
    	btnQuit.setAlignmentX(Component.LEFT_ALIGNMENT);
    	btnQuit.setPreferredSize(new Dimension(100, btnBack.getMinimumSize().height));
    	
    	btnReset = new JButton("Reset");
    	btnReset.setFocusable(false);
    	btnReset.setAlignmentX(Component.LEFT_ALIGNMENT);
    	btnReset.setPreferredSize(new Dimension(100, btnReset.getMinimumSize().height));
    	
    	btnRoll = new JButton("ROLL");
    	btnRoll.setFocusable(false);
    	btnRoll.setAlignmentX(Component.RIGHT_ALIGNMENT);
    	btnRoll.setPreferredSize(new Dimension(150, btnRoll.getMinimumSize().height));
    	
    	leftBottomPanel = new JPanel();
    	leftBottomPanel.setLayout(new BoxLayout(leftBottomPanel, BoxLayout.LINE_AXIS));
    	leftBottomPanel.setOpaque(false);
    	leftBottomPanel.add(btnReset);
    	leftBottomPanel.add(Box.createHorizontalStrut(10));
    	leftBottomPanel.add(btnQuit);
    	
    	rightBottomPanel = new JPanel();
    	rightBottomPanel.setPreferredSize(new Dimension(130, 0));
    	rightBottomPanel.setOpaque(false);
    	rightBottomPanel.setLayout(new BoxLayout(rightBottomPanel, BoxLayout.LINE_AXIS));
    	rightBottomPanel.add(Box.createHorizontalStrut(10));
    	rightBottomPanel.add(btnRoll);
    	
    	lblRolls = new JLabel();
    	lblRolls.setAlignmentY(Component.CENTER_ALIGNMENT);
    	
    	lblPlayerName = new JLabel();
    	
    	playGamePanel = new JPanel(new BorderLayout());
    	playGamePanel.setBackground(darkGreen);
    	playGamePanel.setBorder(new EmptyBorder(new Insets(0, 10, 20, 10)));
    	
    	leftPanel = new JPanel();
    	leftPanel.setLayout(gridProtocol);
    	leftPanel.setOpaque(false);
    	
    	lblNameOfYatzyCombos = new JLabel [18];
    	
    	//Writes out the different combinations on the chart 
    	for(int i=0; i<18; i++){
    		if(i == 6 || i == 7 || i == 17){
    			lblNameOfYatzyCombos[i] = new JLabel("<html><b><h3><span style: color=white>"+regularYatzyCombinations[i]+"</span></h3></b></html>");
    			lblNameOfYatzyCombos[i].setBackground(Color.DARK_GRAY);
    		}else{
    			lblNameOfYatzyCombos[i] = new JLabel(regularYatzyCombinations[i]);
    			lblNameOfYatzyCombos[i].setBackground(Color.WHITE);
    		}
    		
    		lblNameOfYatzyCombos[i].setOpaque(true);
    		lblNameOfYatzyCombos[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
    		leftPanel.add(lblNameOfYatzyCombos[i], gridProtocol);
    	}
    	
    	topPanel = new JPanel();
    	topPanel.setLayout(new BorderLayout());
    	topPanel.setBorder(new EmptyBorder(new Insets(0, 5, 0, 22)));
    	topPanel.setPreferredSize(new Dimension(0, 38));
    	topPanel.setOpaque(false);
    	
    	topPanel.add(lblPlayerName, BorderLayout.LINE_START);  	
    	topPanel.add(lblRolls, BorderLayout.LINE_END);
    	
    	dicePanel = new JPanel();
    	dicePanel.setLayout(new BoxLayout(dicePanel, BoxLayout.PAGE_AXIS));
    	dicePanel.setOpaque(false);
    	dicePanel.setPreferredSize(new Dimension(120, 0));
    	
    	protocolPanel = new JPanel();
    	protocolPanel.setLayout(gridProtocol);
    	protocolPanel.setOpaque(false);
    	
    	bottomPanel = new JPanel(new BorderLayout());
    	bottomPanel.setOpaque(false);
    	bottomPanel.setPreferredSize(new Dimension(100, 50));
    	
    	bottomPanel.add(leftBottomPanel, BorderLayout.LINE_START);
    	bottomPanel.add(rightBottomPanel, BorderLayout.LINE_END);
    	
    	playGamePanel.add(topPanel, BorderLayout.PAGE_START);
    	playGamePanel.add(leftPanel, BorderLayout.LINE_START);
    	playGamePanel.add(protocolPanel, BorderLayout.CENTER);
    	playGamePanel.add(dicePanel, BorderLayout.LINE_END);
    	playGamePanel.add(bottomPanel, BorderLayout.PAGE_END);
    	
    	btnQuit.addActionListener(new ActionListener(){
    		
    		public void actionPerformed(ActionEvent arg0){
    			removePlayersAndResetComponentsForPlayYatzyPanel();
    			cl.show(contentPanel, "startPanel");
    		}
    	});  
    	
    	btnReset.addActionListener(new ActionListener(){
    		
    		public void actionPerformed(ActionEvent arg0){
    			resetYatzyGame();
    		}
    	});
    	
    	btnRoll.addActionListener(new ActionListener(){
    		
    		public void actionPerformed(ActionEvent arg0){   
    			if(!player.get(currentPlayerIndex).getPlayerYatzyProtocol().checkIfAllDiceAreLocked())
    				rollUnselectedDice();		
    		}
    	});
    	
    	return playGamePanel;
    }
    
    public JPanel getRulePanel(){
    	JLabel b = new JLabel();
    	JLabel c = new JLabel();
    	b.setIcon(stackedDice);
    	b.setOpaque(false);
    	c.setIcon(stackedRedDice);
    	c.setOpaque(true);
    	b.setOpaque(true);
    	c.setBackground(darkGreen);
    	b.setBackground(darkGreen);
    	
    	rulePanel = new JPanel(new BorderLayout());
    	
    	bottomPanel = new JPanel();
    	bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.PAGE_AXIS));
    	centerPanel = new JPanel(new BorderLayout());
    	
    	centerPanel.setBackground(darkGreen);
    	bottomPanel.setBackground(darkGreen);
    	
    	btnBack = new JButton("Back");
        btnBack.setFocusable(false);
        btnBack.setAlignmentX(RIGHT_ALIGNMENT);
        btnBack.setMaximumSize(new Dimension(90, btnBack.getMinimumSize().height));
    	
    	lblRules = new JLabel("<html><center><H1>YATZY</H1></center><br>"
    							+ "Each player takes turn to toss five die. You can re-roll the die three times.<br>"
    							+ "After each roll you can choos which die to keep and the rest of the<br>"
    							+ "dice you will re-roll. There is a total of 15 combinations in this game<br>"
    							+ "that you need to make to be able to get points. The player with the<br>"
    							+ "most points at the end wins.</html>");
    	
    	lblRules.setHorizontalAlignment(SwingConstants.CENTER);
    	
    	bottomPanel.add(btnBack);
    	bottomPanel.setBorder(new EmptyBorder(new Insets(0, 0, 10, 10)));
    	
    	centerPanel.add(lblRules, BorderLayout.CENTER);
    	
    	rulePanel.add(c, BorderLayout.LINE_START);
    	rulePanel.add(b, BorderLayout.LINE_END);
    	rulePanel.add(centerPanel, BorderLayout.CENTER);
    	rulePanel.add(bottomPanel, BorderLayout.PAGE_END);
    	
    	btnBack.addActionListener(new ActionListener(){
    		
    		public void actionPerformed(ActionEvent arg0){
    			cl.show(contentPanel, "startPanel");
    		}
    	});
    	
    	return rulePanel;
    }
    
    public void addPlayerToTheList(){
    	//Checks if user has input white space and if maximum players is reached
    	if(!txtFieldPlayerName.getText().trim().isEmpty() && totPlayers <= maximumPlayersAllowed){
			
    		//creates a new Player and adds the name that the user inputs from the textField
			player.add(new Player(txtFieldPlayerName.getText()));
		
			String playerName = "";
			int playerNr = 1;
			
			for(Player p : player){
				playerName += playerNr+": "+p.getName()+"<br>";  
				playerNr++;
			}
			
			totPlayers++;
			lblPlayerList.setText("<html><h2>PLAYER LIST</h2>"+playerName+"</html>");
			txtFieldPlayerName.setText("");
		}
		
		if(totPlayers >= minimumPlayersAllowed){
			btnPlay.setEnabled(true);
		}
		
		if(totPlayers >= maximumPlayersAllowed){
			btnAddPlayer.setEnabled(false);
			txtFieldPlayerName.setEnabled(false);
		}
    }
    
    public void resetCompontentsForAddPlayerToListPanel(){
    	player.removeAll(player);
		totPlayers = 0;
		btnAddPlayer.setEnabled(true);
		txtFieldPlayerName.setEnabled(true);
		btnPlay.setEnabled(false);
		lblPlayerList.setText("<html><center><h2>PLAYER LIST</h2></center></html>");
    }
    
    public void createGameField(){
    	lblPlayerList.setText("<html><center><h2>PLAYER LIST</h2></center></html>");
		lblPlayerName.setText("<html><h3>Turn: <span style: color=white>"+player.get(currentPlayerIndex).getName()+"</span></h3></html>");
		lblRolls.setText("<html><h3>Rolls left: <span style: color=white>"+player.get(currentPlayerIndex).getPlayerYatzyProtocol().getTotDiceTossLeft()+"</span></h3></html>");
		
		btnAddPlayer.setEnabled(true);
		txtFieldPlayerName.setEnabled(true);
		btnPlay.setEnabled(false);
		
		btnDie = new ArrayList<>();
		
		//DieImage dieImage = new DieImage();
    	//dieImage.addActionListener(dieImage);
		
		//create the dice buttons
		for(int i=0; i<totDice; i++){
			btnDie.add( new JButton());
			btnDie.get(i).setName(""+(i));
			btnDie.get(i).setFocusable(false);
			btnDie.get(i).setBorderPainted(false);
			btnDie.get(i).setContentAreaFilled(false);
			btnDie.get(i).setFocusPainted(false);
			btnDie.get(i).setOpaque(false);
			btnDie.get(i).setMaximumSize(new Dimension(70, 70));
			btnDie.get(i).setAlignmentX(CENTER_ALIGNMENT);
			
			btnDie.get(i).addActionListener(new ActionListener(){
				
				public void actionPerformed(ActionEvent e){
					chooseWhichDieToRoll(e);	
			    } 
			});	
			
			dicePanel.add(btnDie.get(i));
			dicePanel.add(Box.createVerticalGlue());
			dicePanel.add(Box.createRigidArea(new Dimension(0, 1)));
		}
			//dicePanel.add(dieImage);
			
		btnPlayerCombinationOnProtocol = new JButton [totProtocolRows][player.size()];
		
		for(int i=0; i<totProtocolRows; i++){
			for(int a=0; a<player.size(); a++){
				
	    		btnPlayerCombinationOnProtocol[i][a] = new JButton();
	    		btnPlayerCombinationOnProtocol[i][a].setFocusable(false);
	    		btnPlayerCombinationOnProtocol[i][a].setName(""+(i+1));
	    		btnPlayerCombinationOnProtocol[i][a].setText(lblNameOfYatzyCombos[i].getText());
	    		
	    		//Disable players chartButtons except for the current player
	    		if(currentPlayerIndex != a){
	    			btnPlayerCombinationOnProtocol[i][a].setEnabled(false);
	    		}
	    		//Disable buttons for sum and bonus on chart	    	    		
	    		if(i == 6 || i == 7 || i == 17){
	    			btnPlayerCombinationOnProtocol[i][a].setEnabled(false);
	    			btnPlayerCombinationOnProtocol[i][a].setContentAreaFilled(true);
	    			btnPlayerCombinationOnProtocol[i][a].setBackground(Color.WHITE);
    	    		btnPlayerCombinationOnProtocol[i][a].setFocusPainted(false);
    	    		btnPlayerCombinationOnProtocol[i][a].setText(""+player.get(currentPlayerIndex).getPlayerYatzyProtocol().getPointsFromProtocol(i));
	    		}
	    		
	    		btnPlayerCombinationOnProtocol[i][a].addActionListener(new ActionListener(){
    				
	    			public void actionPerformed(ActionEvent e){
        				handleYatzyProtocol(e);
    				}
    				
    			});
	    		
	    		//btnPlayerCombinationOnProtocol[i].setContentAreaFilled(false);
	    		//btnPlayerCombinationOnProtocol[i].setFocusPainted(true);
	    		//btnPlayerCombinationOnProtocol[i][a].setBorder(BorderFactory.createLineBorder(Color.black));
	    		protocolPanel.add(btnPlayerCombinationOnProtocol[i][a], new GridLayout(18, player.size()));
			}
		}
		
		for(int i=0; i<this.totDice; i++){
			btnDie.get(i).setIcon(selectedDieIcon[typeOfDiceIndex][0]);
		}

    }
    
    public void handleYatzyProtocol(ActionEvent e){
    	if(player.get(currentPlayerIndex).getPlayerYatzyProtocol().getTotDiceTossLeft() !=3){
			JButton btnProtocolBox = (JButton)e.getSource();
			int index = Integer.parseInt(btnProtocolBox.getName());
			
			resetDiceIcon();        					
			
			player.get(currentPlayerIndex).getPlayerYatzyProtocol().addPointsToProtocol(index);
			
			if(player.get(currentPlayerIndex).getPlayerYatzyProtocol().getPointsFromProtocol(index) == 0){
				btnProtocolBox.setText("X");
				btnProtocolBox.setBackground(darRed);
			}else
				btnProtocolBox.setText(player.get(currentPlayerIndex).getPlayerYatzyProtocol().getPointsFromProtocol(index)+"");
			
			btnProtocolBox.setEnabled(false);

			player.get(currentPlayerIndex).getPlayerYatzyProtocol().resetTotDiceToss();
			player.get(currentPlayerIndex).getPlayerYatzyProtocol().resetKeepDice();
			setNextcurrentPlayerIndex();
			lblPlayerName.setText("<html><h3>Turn: <span style: color=white>"+player.get(currentPlayerIndex).getName()+"</span></h3></html>");
			
			for(int i=0; i<totProtocolRows; i++){
				
    			for(int a=0; a<player.size(); a++){	
    				//Number 6, 7 and 17 can't be interacted with the player cause they update automatically  
	    	   		if(currentPlayerIndex == a && i != 6 && i != 7 && i != 17){
	    	   			
	    	   			if(!player.get(currentPlayerIndex).getPlayerYatzyProtocol().getCombinationTicktOff(i)){
	    	   				btnPlayerCombinationOnProtocol[i][a].setEnabled(true);
	    	   			}else{
	    	   				btnPlayerCombinationOnProtocol[i][a].setEnabled(false);
	    	   			}
	    	   			if(a-1 < 0){
	    	   				btnPlayerCombinationOnProtocol[i][player.size()-1].setEnabled(false);
	    	   			}else{
	    	   				btnPlayerCombinationOnProtocol[i][a-1].setEnabled(false);
	    	   			}
	    	   		}
	    	   		switch(i){
	    	   			case 6:
	    	   				btnPlayerCombinationOnProtocol[i][a].setText(""+player.get(a).getPlayerYatzyProtocol().getUperSum());
	    	   				break;
	    	   			case 7:
	    	   				btnPlayerCombinationOnProtocol[i][a].setText(""+player.get(a).getPlayerYatzyProtocol().getBonus());
	    	   				break;
	    	   			case 17:
	    	   				btnPlayerCombinationOnProtocol[i][a].setText(""+player.get(a).getPlayerYatzyProtocol().getTotSumOfProtocol());
	    	   				break;
	    	   		}
    			}
    		}
			
			boolean gameIsDone = false;
			int highestScore = 0;
			String winnerName = "";
			
			//Check player with highest points when game is over
			for(Player p: player){
				if(!p.getPlayerYatzyProtocol().getIfAllCombosTicktOff()){
					gameIsDone = false;
					break;
				}else
					gameIsDone = true;
			}
			
			if(gameIsDone){
				for(Player p: player){
					if(highestScore <= p.getPlayerYatzyProtocol().getTotSumOfProtocol()){
						highestScore = p.getPlayerYatzyProtocol().getTotSumOfProtocol();
						winnerName += p.getName()+" ";
					}
					btnRoll.setEnabled(false);
				}
				
				lblPlayerName.setText("<html><h3>Winner: <span style: color=white>"+winnerName+"</span></h3></html>");
				lblRolls.setText("");
			}else
				lblRolls.setText("<html><h3>Rolls left: <span style: color=white>"+player.get(currentPlayerIndex).getPlayerYatzyProtocol().getTotDiceTossLeft()+"</span></h3></html>");
			
			btnRoll.setEnabled(true);		        	    			
		}
    }
    
    public void chooseWhichDieToRoll(ActionEvent e){
    	//Checks if player have rolled the dice at least one time
    	if(player.get(currentPlayerIndex).getPlayerYatzyProtocol().getTotDiceTossLeft() !=3){
			JButton btnDie = (JButton)e.getSource();
			int index = Integer.parseInt(btnDie.getName());
    	
			//Change Icon on die depending on user interaction 
	    	switch(player.get(currentPlayerIndex).getPlayerYatzyProtocol().getDieNumber(index)){
				case 1:
					if(player.get(currentPlayerIndex).getPlayerYatzyProtocol().getKeepDice(index)){
						btnDie.setIcon(selectedDieIcon[typeOfDiceIndex][6]);
						player.get(currentPlayerIndex).getPlayerYatzyProtocol().keepDice(index);
						
					}else if(!player.get(currentPlayerIndex).getPlayerYatzyProtocol().getKeepDice(index)){
						btnDie.setIcon(selectedDieIcon[typeOfDiceIndex][0]);
						player.get(currentPlayerIndex).getPlayerYatzyProtocol().releaseDice(index);
					}
					break;
				case 2:
					if(player.get(currentPlayerIndex).getPlayerYatzyProtocol().getKeepDice(index)){
						btnDie.setIcon(selectedDieIcon[typeOfDiceIndex][7]);
						player.get(currentPlayerIndex).getPlayerYatzyProtocol().keepDice(index);
						
					}else if(!player.get(currentPlayerIndex).getPlayerYatzyProtocol().getKeepDice(index)){
						btnDie.setIcon(selectedDieIcon[typeOfDiceIndex][1]);
						player.get(currentPlayerIndex).getPlayerYatzyProtocol().releaseDice(index);
					}
					break;
				case 3:
					if(player.get(currentPlayerIndex).getPlayerYatzyProtocol().getKeepDice(index)){
						btnDie.setIcon(selectedDieIcon[typeOfDiceIndex][8]);
						player.get(currentPlayerIndex).getPlayerYatzyProtocol().keepDice(index);
						
					}else if(!player.get(currentPlayerIndex).getPlayerYatzyProtocol().getKeepDice(index)){
						btnDie.setIcon(selectedDieIcon[typeOfDiceIndex][2]);
						player.get(currentPlayerIndex).getPlayerYatzyProtocol().releaseDice(index);
					}
					break;
				case 4:
					if(player.get(currentPlayerIndex).getPlayerYatzyProtocol().getKeepDice(index)){
						btnDie.setIcon(selectedDieIcon[typeOfDiceIndex][9]);
						player.get(currentPlayerIndex).getPlayerYatzyProtocol().keepDice(index);
						
					}else if(!player.get(currentPlayerIndex).getPlayerYatzyProtocol().getKeepDice(index)){
						btnDie.setIcon(selectedDieIcon[typeOfDiceIndex][3]);
						player.get(currentPlayerIndex).getPlayerYatzyProtocol().releaseDice(index);
					}
					break;
				case 5:
					if(player.get(currentPlayerIndex).getPlayerYatzyProtocol().getKeepDice(index)){
						btnDie.setIcon(selectedDieIcon[typeOfDiceIndex][10]);
						player.get(currentPlayerIndex).getPlayerYatzyProtocol().keepDice(index);
						
					}else if(!player.get(currentPlayerIndex).getPlayerYatzyProtocol().getKeepDice(index)){
						btnDie.setIcon(selectedDieIcon[typeOfDiceIndex][4]);
						player.get(currentPlayerIndex).getPlayerYatzyProtocol().releaseDice(index);
					}
					break;
				case 6:
					if(player.get(currentPlayerIndex).getPlayerYatzyProtocol().getKeepDice(index)){
						btnDie.setIcon(selectedDieIcon[typeOfDiceIndex][11]);
						player.get(currentPlayerIndex).getPlayerYatzyProtocol().keepDice(index);
						
					}else if(!player.get(currentPlayerIndex).getPlayerYatzyProtocol().getKeepDice(index)){
						btnDie.setIcon(selectedDieIcon[typeOfDiceIndex][5]);
						player.get(currentPlayerIndex).getPlayerYatzyProtocol().releaseDice(index);
					}
					break;
	    	}
    	}
    }
    
    public void removePlayersAndResetComponentsForPlayYatzyPanel(){
		totPlayers = 0;
		currentPlayerIndex = 0;
    	player.removeAll(player);
		btnRoll.setEnabled(true);
		protocolPanel.removeAll();
		dicePanel.removeAll();
    }
    
    public void resetYatzyGame(){
    	currentPlayerIndex = 0;
    	
    	resetDiceIcon();
    	
    	for(Player p: player){
			p.getPlayerYatzyProtocol().resetProtocol();
			p.getPlayerYatzyProtocol().resetTotDiceToss();
			p.getPlayerYatzyProtocol().resetKeepDice();
		}
		
		for(int i=0; i<totProtocolRows; i++){
			for(int a=0; a<player.size(); a++){
				btnPlayerCombinationOnProtocol[i][a].setText(lblNameOfYatzyCombos[i].getText());
				btnPlayerCombinationOnProtocol[i][a].setEnabled(true);
				btnPlayerCombinationOnProtocol[i][a].setBackground(null);
				
				if(currentPlayerIndex != a){
	    			btnPlayerCombinationOnProtocol[i][a].setEnabled(false);
	    		}
				
				if(i == 6 || i == 7 || i == 17){
					btnPlayerCombinationOnProtocol[i][a].setEnabled(false);
					btnPlayerCombinationOnProtocol[i][a].setBackground(Color.WHITE);
    	    		btnPlayerCombinationOnProtocol[i][a].setText(""+player.get(currentPlayerIndex).getPlayerYatzyProtocol().getPointsFromProtocol(i));
	    		}
			}
		}
			
		btnRoll.setEnabled(true);
		lblPlayerName.setText("<html><h3>Turn: <span style: color=white>"+player.get(currentPlayerIndex).getName()+"</span></h3></html>");
		lblRolls.setText("<html><h3>Rolls left: <span style: color=white>"+player.get(currentPlayerIndex).getPlayerYatzyProtocol().getTotDiceTossLeft()+"</span></h3></html>");
    }
    
    public void rollUnselectedDice(){
    	player.get(currentPlayerIndex).getPlayerYatzyProtocol().rollDice();
		player.get(currentPlayerIndex).getPlayerYatzyProtocol().decreaseDieTosses();
		lblRolls.setText("<html><h3>Rolls left: <span style: color=white>"+player.get(currentPlayerIndex).getPlayerYatzyProtocol().getTotDiceTossLeft()+"</span></h3></html>");
		
		//Check each die for current player and roll the die that is not selected
		for(int i = 0; i < totDice; i++){
			if(player.get(currentPlayerIndex).getPlayerYatzyProtocol().getKeepDice(i)){
				switch(player.get(currentPlayerIndex).getPlayerYatzyProtocol().getDieNumber(i)){
					case 1:
						btnDie.get(i).setIcon(selectedDieIcon[typeOfDiceIndex][0]);
						break;
					case 2:
						btnDie.get(i).setIcon(selectedDieIcon[typeOfDiceIndex][1]);
						break;
					case 3:
						btnDie.get(i).setIcon(selectedDieIcon[typeOfDiceIndex][2]);
						break;
					case 4:
						btnDie.get(i).setIcon(selectedDieIcon[typeOfDiceIndex][3]);
						break;
					case 5:
						btnDie.get(i).setIcon(selectedDieIcon[typeOfDiceIndex][4]);
						break;
					case 6:
						btnDie.get(i).setIcon(selectedDieIcon[typeOfDiceIndex][5]);
						break;
				}
			}
		}
		//only for testing purpose
		//player.get(currentPlayerIndex).getPlayerYatzyProtocol().showDice();
		
		if(player.get(currentPlayerIndex).getPlayerYatzyProtocol().getTotDiceTossLeft() == 0){ 
			btnRoll.setEnabled(false);
		}
    }
    
    public void resetDiceIcon(){
    	for(JButton die: btnDie)
    		die.setIcon(selectedDieIcon[typeOfDiceIndex][0]);
    }
    
    public void setNextcurrentPlayerIndex(){
    	if( this.currentPlayerIndex < player.size()-1){
    		this.currentPlayerIndex++;
    	}else{
    		this.currentPlayerIndex = 0;
    	}
    	
    }
    
    public int getCurrentPlayerIndex(){
    	return this.currentPlayerIndex;
    }
    
    public static void main(String[] args) {
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MyJFrame();
			}
		});
    }
}
