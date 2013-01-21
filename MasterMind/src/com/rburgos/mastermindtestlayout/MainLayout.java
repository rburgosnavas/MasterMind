package com.rburgos.mastermindtestlayout;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class MainLayout extends JFrame 
{
    private JPanel mainPanel, topPanel, pegsLeftPanel, rightPanel, bottomPanel, 
            titlePanel, hintsPanel;
    private JLabel label, hintsTitleLabel;
    private JMenuBar menuBar;
    private JMenu gameMenu, helpMenu;
    private JMenuItem easyModeItem, advModeItem, exitItem, instrItem, aboutItem;
    
    private ColorPeg peg1, peg2, peg3, peg4, peg5, 
            peg6, peg7, peg8, peg9, peg10;
    private ColorPeg tempColorPeg;
    private DummyPeg tempDummyPeg;
    
    private GridBagConstraints dummyPegsConstraints;
    
    private String[] colors = {"#eccc75", "#72688c", "#f6a01a", "#116348", 
            "#3b7e98", "#d53533", "#a36526", "#679317", "#ed9f9f", "#1be2b4"};
    private String winMsg = "Congratulations! You won! \n\n" + 
            "Select another game from the Game menu \nor exit to close";
    private String loseMsg = "Sorry, try again.\n\n" + 
            "Select another game from the Game menu \nor exit to close";
    private String[] yn;
    
    private int col = 0, row = 12, num = 0, numPos = 0, top = 5;
    
    private boolean advMode = false;
    
    private ArrayList<ArrayList<? super JComponent>> initPegsArray = 
            new ArrayList<>();
    private ArrayList<? super JComponent> answerPegsArray = new ArrayList<>();
    private ArrayList<JLabel> hintLabels = new ArrayList<>();

    /**
     * Create the frame.
     */
    public MainLayout()
    {
        // Main frame
        setResizable(false);
        setTitle("MasterMind");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 480, 700);
        
        // Create the menu...
        createMenu();
        
        // Create GUI components
        createGUI();
    }

    /**
     * Create the GUI components.
     */
    private void createGUI()
    {
        // Main panel:
        // Divided into topPanel and bottomPanel
        mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(mainPanel);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{0};
        gbl_contentPane.rowHeights = new int[]{591, 0, 0};
        gbl_contentPane.columnWeights = new double[]{1.0};
        gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        mainPanel.setLayout(gbl_contentPane);
        
        // Top panel:
        // This panel contains all the "pegs" used for the game and the 
        // panel the will display the hints and result
        topPanel = new JPanel();
        GridBagConstraints gbc_topPanel = new GridBagConstraints();
        gbc_topPanel.insets = new Insets(0, 0, 5, 0);
        gbc_topPanel.fill = GridBagConstraints.BOTH;
        gbc_topPanel.gridx = 0;
        gbc_topPanel.gridy = 0;
        mainPanel.add(topPanel, gbc_topPanel);
        GridBagLayout gbl_topPanel = new GridBagLayout();
        gbl_topPanel.columnWidths = new int[]{0, 0, 0};
        gbl_topPanel.rowHeights = new int[]{0, 0};
        gbl_topPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_topPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        topPanel.setLayout(gbl_topPanel);
        
        // Pegs panel sits at the left side of the top panel
        pegsLeftPanel = new JPanel();
        pegsLeftPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, 
                                                 null, 
                                                 null));
        GridBagConstraints gbc_pegsLeftPanel = new GridBagConstraints();
        gbc_pegsLeftPanel.anchor = GridBagConstraints.NORTH;
        gbc_pegsLeftPanel.fill = GridBagConstraints.BOTH;
        gbc_pegsLeftPanel.gridx = 0;
        gbc_pegsLeftPanel.gridy = 0;
        topPanel.add(pegsLeftPanel, gbc_pegsLeftPanel);
        GridBagLayout gbl_pegsLeftPanel = new GridBagLayout();
        gbl_pegsLeftPanel.columnWidths = new int[] {0};
        gbl_pegsLeftPanel.rowHeights = new int[] {30};
        gbl_pegsLeftPanel.columnWeights = new double[]{0.0};
        gbl_pegsLeftPanel.rowWeights = new double[]{0.0};
        pegsLeftPanel.setLayout(gbl_pegsLeftPanel);
        
        dummyPegsConstraints = new GridBagConstraints();
        dummyPegsConstraints.insets = new Insets(2, 3, 2, 3);
                
        // Fill the pegsLeftPanel with the dummy pegs
        createDummyPegs();
        
        // Right panel:
        // Divided into a top panel to hold the "Hints" title, and a bottom
        // panel to display the hints.
        rightPanel = new JPanel();
        rightPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, 
                                              null, 
                                              null));
        GridBagConstraints gbc_rightPanel = new GridBagConstraints();
        gbc_rightPanel.fill = GridBagConstraints.BOTH;
        gbc_rightPanel.gridx = 1;
        gbc_rightPanel.gridy = 0;
        topPanel.add(rightPanel, gbc_rightPanel);
        rightPanel.setLayout(new BorderLayout(0, 0));
        
        // Title panel
        titlePanel = new JPanel();
        titlePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, 
                                              null, 
                                              null));
        FlowLayout flowLayout = (FlowLayout) titlePanel.getLayout();
        flowLayout.setVgap(11);
        rightPanel.add(titlePanel, BorderLayout.NORTH);
        
        // Title that goes in the Title panel
        hintsTitleLabel = new JLabel("Hints");
        hintsTitleLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        titlePanel.add(hintsTitleLabel);
        
        // Hints panel
        hintsPanel = new JPanel();
        rightPanel.add(hintsPanel);
        hintsPanel.setLayout(new GridLayout(12, 1, 4, 4));

        // Creates empty dummy labels
        createDummyLabels();
        
        // Bottom panel:
        // Contains the all color pegs that will be used as "buttons"
        bottomPanel = new JPanel();
        bottomPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, 
                                               null, 
                                               null));
        GridBagConstraints gbc_bottomPanel = new GridBagConstraints();
        gbc_bottomPanel.fill = GridBagConstraints.BOTH;
        gbc_bottomPanel.gridx = 0;
        gbc_bottomPanel.gridy = 1;
        mainPanel.add(bottomPanel, gbc_bottomPanel);
        
        // The pegs that will be used as "buttons". They reside in the
        // Bottom panel
        peg1 = new ColorPeg(Color.decode(colors[0]));
        peg2 = new ColorPeg(Color.decode(colors[1]));
        peg3 = new ColorPeg(Color.decode(colors[2]));
        peg4 = new ColorPeg(Color.decode(colors[3]));
        peg5 = new ColorPeg(Color.decode(colors[4]));
        peg6 = new ColorPeg(Color.decode(colors[5]));
        peg7 = new ColorPeg(Color.decode(colors[6]));
        peg8 = new ColorPeg(Color.decode(colors[7]));
        peg9 = new ColorPeg(Color.decode(colors[8]));
        peg10 = new ColorPeg(Color.decode(colors[9]));
        
        // Add Mouse Listeners to the pegs to be used as "buttons"
        peg1.addMouseListener(new PegListener());
        peg2.addMouseListener(new PegListener());
        peg3.addMouseListener(new PegListener());
        peg4.addMouseListener(new PegListener());
        peg5.addMouseListener(new PegListener());
        peg6.addMouseListener(new PegListener());
        peg7.addMouseListener(new PegListener());
        peg8.addMouseListener(new PegListener());
        peg9.addMouseListener(new PegListener());
        peg10.addMouseListener(new PegListener());
        
        // Add the pegs to the Bottom panel
        bottomPanel.setLayout(new GridLayout(0, 10, 0, 0));        
        bottomPanel.add(peg1);
        bottomPanel.add(peg2);
        bottomPanel.add(peg3);
        bottomPanel.add(peg4);
        bottomPanel.add(peg5);
        bottomPanel.add(peg6);
        bottomPanel.add(peg7);
        bottomPanel.add(peg8);
        bottomPanel.add(peg9);
        bottomPanel.add(peg10);
    }

    /**
     * Create the menu bar, game and help menus, and their respective items. 
     * Also adds Action Listeners to the items.
     */
    private void createMenu()
    {
        menuBar = new JMenuBar();
        menuBar.setBorderPainted(false);
        setJMenuBar(menuBar);
        
        // The game menu.
        gameMenu = new JMenu("Game");
        menuBar.add(gameMenu);
        
        // The easy mode item
        easyModeItem = new JMenuItem("Easy Mode");
        gameMenu.add(easyModeItem);
        easyModeItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                top = 5;
                row = 12;
                col = 0;
                num = 0;
                numPos = 0;
                advMode = false;
                createDummyPegs();
                createDummyLabels();
            }
        });
        
        // The advanced mode item
        advModeItem = new JMenuItem("Advanced Mode");
        gameMenu.add(advModeItem);
        advModeItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                top = 0;
                row = 12;
                col = 0;
                num = 0;
                numPos = 0;
                advMode = true;
                createDummyPegs();
                createDummyLabels();
            }
        });
        
        // Add separator between the game modes and the exit item
        gameMenu.addSeparator();
        
        // Exit menu item
        exitItem = new JMenuItem("Exit");
        gameMenu.add(exitItem);
        exitItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                System.exit(0);
            }
        });
        
        // The help menu.
        helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);
        
        // The help menu items.
        instrItem = new JMenuItem("Instruction");
        helpMenu.add(instrItem);
        instrItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(null, 
                        "You are given 5 unique colors and your goal is to\n" + 
                        "guess what they are with the hints provided.\n\n" + 
                        "In Easy Mode, hints are given as \"yes\\no\". \n" + 
                        "If you guess the right color in its right \n" + 
                        "position, you get a \"yes\".\n" + 
                        "You have 7 chances to guess correctly.\n\n" + 
                        "In Advanced Mode, hints are given as the number \n" + 
                        "of right colors in the right position (cp), or the \n" + 
                        "the number right colors in the wrong position (c).\n" + 
                        "You have 12 chances to guess correctly.",
                        "Instructions", 
                        JOptionPane.DEFAULT_OPTION);
            }
        });
        
        // The "About" menu item
        aboutItem = new JMenuItem("About...");
        helpMenu.add(aboutItem);
        aboutItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(null, 
                        "MasterMind by Roberto Burgos\n\nrburgos.com", 
                        "About MasterMind", 
                        JOptionPane.DEFAULT_OPTION);
            }
        });
    }
    
    /**
     * Creates a <code>JLabel</code> that will display a hint. The hint is
     * saved in an ArrayList which holds all hints. The <code>JLabel</code>
     * stored in the array is then added to the <code>JPanel</code> that will display all the
     * labels.
     * @param t a <code>String</code> that represents the hint to display
     * @param i an <code>int</code> to indicate the position in the
     * <code>ArrayList</code> to save the JLabel at.
     */
    public void createHint(String t, int i)
    {
        // Create the label
        label = new JLabel(t, JLabel.CENTER);
        label.setBorder(BorderFactory.createDashedBorder(
                Color.BLACK, 2, 5, 2, true));
        label.setFont(new Font("Monospaced", Font.BOLD, 18));
        
        // Add to array
        hintLabels.add(label);
        
        // Add to panel from array
        hintsPanel.add(hintLabels.get(i));
    }
    
    /**
     * Similar to <code>createHint</code> except that it replaces a hint in
     * an array with a new hint and updates the panel at its corresponding
     * index.
     * @param t a <code>String</code> that represents the hint to display
     * @param i an <code>int</code> to indicate the position in the
     * <code>ArrayList</code> to save the JLabel at.
     */
    public void updateHint(String t, int i)
    {
        // Create the label
        label = new JLabel(t, JLabel.CENTER);
        label.setBorder(BorderFactory.createDashedBorder(
                Color.BLACK, 2, 5, 2, true));
        label.setFont(new Font("Monospaced", Font.BOLD, 18));
        
        // Add the new hint to the array
        hintLabels.remove(i);
        hintLabels.add(i, label);
        
        // Add to panel from array at the current position
        hintsPanel.remove(i);
        hintsPanel.add(hintLabels.get(i), i);
        hintsPanel.revalidate();
    }
    
    /**
     * Creates empty dummy <code>JLabels</code>.
     */
    public void createDummyLabels()
    {
        hintLabels.clear();
        hintsPanel.removeAll();
        for (int i = 0; i < 12; i++)
        {
            createHint("", i);
        }
        hintsPanel.revalidate();
    }
    
    /**
     * This method creates the five pegs that the user needs to guess
     * correctly by:
     * <ul>
     * <li>Picking five random and unique colors from the <code>colors</code>
     * array;</li>
     * <li>Creating the appropriate pegs with these colors;</li>
     * <li>Adding the pegs to the <code>answerPegsArray</code> ArrayList.</li>
     * </ul>
     */
    public void createRandomPegs()
    {
        ArrayList<String> uniqueColors = new ArrayList<>();
        answerPegsArray.clear();
        int rand = 0;
        Random r = new Random();
        ColorPeg tmpPeg;
        
        for (int i = 0; uniqueColors.size() < 5; i++)
        {
            rand = r.nextInt(10);            
            
            if (!uniqueColors.contains(colors[rand]))
            {
                uniqueColors.add(colors[rand]);
            }
        }
        
        for (int i = 0; i < 5; i++)
        {
            tmpPeg = new ColorPeg(Color.decode(uniqueColors.get(i)));
            answerPegsArray.add(tmpPeg);
        }
    }
    
    /**
     * Creates dummy pegs that will populate the pegs panel. Dummy pegs are 
     * colorless pegs that get painted on the panel to simulate a grid-like
     * structure.
     */
    public void createDummyPegs()
    {
        createRandomPegs();
        initPegsArray.clear();
        
        pegsLeftPanel.removeAll();
        
        for (int i = 0; i < 13; i++)
        {
            initPegsArray.add(new ArrayList<>());
            dummyPegsConstraints.gridy = i;
            
            for (int j = 0; j < 5; j++)
            {
                dummyPegsConstraints.gridx = j;
                
                if (i == 0)
                {
                    tempColorPeg = new ColorPeg(Color.DARK_GRAY);
                    initPegsArray.get(i).add(j, tempColorPeg);
                    pegsLeftPanel.add((JComponent) initPegsArray.get(i).get(j), 
                            dummyPegsConstraints);
                    
                }
                else
                {
                    tempDummyPeg = new DummyPeg();
                    initPegsArray.get(i).add(j, tempDummyPeg);
                    pegsLeftPanel.add((JComponent) initPegsArray.get(i).get(j), 
                            dummyPegsConstraints); 
                }
            }
            pegsLeftPanel.revalidate();
        }        
    }
    
    /**
     * This method updates the panel that holds the pegs. Every time a
     * change is made that involves pegs, mainly whenever a user clicks on the
     * peg buttons, the colors are added to the pegs panel. This method ensures
     * that the changes are displayed.
     */
    public void updatePegs()
    {
        pegsLeftPanel.removeAll();
        for (int i = 0; i < 13; i++)
        {
            dummyPegsConstraints.gridy = i;
            for (int j = 0; j < 5; j++)
            {
                dummyPegsConstraints.gridx = j;
                pegsLeftPanel.add((JComponent) initPegsArray.get(i).get(j), 
                        dummyPegsConstraints);
                pegsLeftPanel.revalidate();
            }
        }
    }
    
    /**
     * Like <code>updatePegs()</code> except that it also reveals the answer
     * at the top of the pegs panel.
     */
    public void showAnswer()
    {
        pegsLeftPanel.removeAll();
        for (int i = 0; i < 13; i++)
        {
            dummyPegsConstraints.gridy = i;
            for (int j = 0; j < 5; j++)
            {
                dummyPegsConstraints.gridx = j;
                if (i == 0)
                {
                    initPegsArray.get(i).add(j, 
                            (JComponent) answerPegsArray.get(j));
                    pegsLeftPanel.add((JComponent) initPegsArray.get(i).get(j), 
                            dummyPegsConstraints);
                }
                else
                {
                    pegsLeftPanel.add((JComponent) initPegsArray.get(i).get(j), 
                            dummyPegsConstraints);
                    pegsLeftPanel.revalidate();
                }
            }
        }
    }
    
    /**
     * This listener is used by the pegs that reside in the Buttons panel, 
     * which are used as buttons.
     * @author Roberto Burgos
     */
    class PegListener extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            super.mouseClicked(e);
            
            // As long as we have not reach the top row and the user has not 
            // guessed all five pegs correctly (indicated by 'int numPos')
            // then the peg buttons will continue to be active and functional.
            if (row > top && numPos != 5)
            {
                if (e.getComponent().equals(peg1))
                {
                    tempColorPeg = new ColorPeg(peg1.getColor());
                }
                else if (e.getComponent().equals(peg2))
                {
                    tempColorPeg = new ColorPeg(peg2.getColor());
                }
                else if (e.getComponent().equals(peg3))
                {
                    tempColorPeg = new ColorPeg(peg3.getColor());
                }
                else if (e.getComponent().equals(peg4))
                {
                    tempColorPeg = new ColorPeg(peg4.getColor());
                }
                else if (e.getComponent().equals(peg5))
                {
                    tempColorPeg = new ColorPeg(peg5.getColor());
                }
                else if (e.getComponent().equals(peg6))
                {
                    tempColorPeg = new ColorPeg(peg6.getColor());
                }
                else if (e.getComponent().equals(peg7))
                {
                    tempColorPeg = new ColorPeg(peg7.getColor());
                }
                else if (e.getComponent().equals(peg8))
                {
                    tempColorPeg = new ColorPeg(peg8.getColor());
                }
                else if (e.getComponent().equals(peg9))
                {
                    tempColorPeg = new ColorPeg(peg9.getColor());
                }
                else if (e.getComponent().equals(peg10))
                {
                    tempColorPeg = new ColorPeg(peg10.getColor());
                }
                
                // As soon as a selection is made, the peg at the current
                // position (represented by 'col') is removed from the array
                // and the new selection is added in that same position.
                initPegsArray.get(row).remove(col);
                initPegsArray.get(row).add(col, tempColorPeg);
                
                // Once the user has made 5 selections (represented by
                // 'col == 4' where 4 is the last column for the current row)
                // we get the results from the MasterMindEngine, and these are
                // passed to the updateHint() method to create the new hint.
                // The value for 'col' is reset back to 0 so we can continue
                // filling in the five positions for the next row.
                if (col == 4) 
                {
                    col = 0;
                    num = MasterMindEngine.getTotalNum(answerPegsArray, 
                            initPegsArray.get(row));
                    numPos = MasterMindEngine.getTotalNumPos(answerPegsArray, 
                            initPegsArray.get(row));
                    yn = MasterMindEngine.getYesNoHints(answerPegsArray, 
                            initPegsArray.get(row));
                    
                    // If 'advMode' is true, then we create advanced mode hitns
                    // else we create easy mode hints
                    if (advMode)
                    {
                        updateHint(num + 
                                " c, " + 
                                numPos + " cp", 
                                row-1);
                    }
                    else if (!advMode)
                    {
                        updateHint(yn[0] + " " + yn[1] + " " + yn[2] + " " + 
                                yn[3] + " " + yn[4], row-1);
                    }
                    
                    // Since we are going from bottom to top, as long as 'row'
                    // is greater than the top row, we decrease its value
                    if (row > top)
                    {
                        row--;
                    }
                }
                else
                {
                    col++;
                }
                
                // We then update the pegs to reflect the new changes made by
                // the user.
                updatePegs();
            }            
            
            // If 'numPos' equals 5, then the user has guessed all five colors.
            // The answer is shown and a message pops up to tell the user that
            // she/he has won.
            if (numPos == 5)
            {
                showAnswer();
                JOptionPane.showMessageDialog(null, 
                        winMsg, 
                        "You Won!", 
                        JOptionPane.INFORMATION_MESSAGE);
            }       
            
            // If we have reach the top row, then the user did not guessed
            // the color combination correctly and the game is over. A message
            // is also displayed.
            if (row == top)
            {
                showAnswer();
                JOptionPane.showMessageDialog(null, 
                        loseMsg, 
                        "You Lost!", 
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public static void main(String[] args)
    {
        try 
        {
            UIManager.
                setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } 
        catch (Throwable e) 
        {
            e.printStackTrace();
        }
        
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    MainLayout frame = new MainLayout();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}
