import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Quiz implements ActionListener{

    String [] questions =
            {
                    "What is the body's largest organ?",
                    "What's the name of the Royal family's castle in Scotland??",
                    "What was Java originally called?",
                    "Complete the well-known phrase: 'A leopard never changes its ____'"

            };


    //QUESTIONAIRE OPTIONS:
    String [][] options =
            {
                    //array for answers for first question:
                    {"Tongue", "Skin","Liver","Brain" },
                    //second question possible answers:
                    {"Buckingham Palace", "Cearphilly Castle","Balmoral Castle","Windsor Castle" },
                    //last question answers:
                    {"Apple", "Latte","Oak","Koffing" },
                    //question
                    {"Spots", "Teeth", "Walk","Roar"}
            };

    //CORRECT ANSWERS
    char[] Answers =
        {
                'B',
                'C',
                'C',
                'A'
        };

    //GUESS CHOSEN BY USER
    char guess;
    char answer;
    int index;
    int correct_guesses = 0;
    int total_questions = questions.length;
    int result;
    int seconds= 10;

    /////////////////////////////////////////CREATE GUI COMPONENTS/////////////////////////////////////
    //FRAME TO HOLD EVERYTHING:
    JFrame frame = new JFrame();

    //TEXT FIELD WILL HOLD THE CURRENT QUESTION WE ARE ON:
    JTextField textField = new JTextField();
    //CURRENT QUESTION:
    JTextArea textArea = new JTextArea();
    //BUTTONS
    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();

    //LABELS TO HOLD ALL THE DIFFERENT ANSWERS
    JLabel answer_labelA = new JLabel();
    JLabel answer_labelB = new JLabel();
    JLabel answer_labelC = new JLabel();
    JLabel answer_labelD = new JLabel();


    //TWO LABELS FOR THE TIMER
    JLabel time_label = new JLabel();//DISPLAYS WORD TIMER
    JLabel seconds_left = new JLabel(); // DISPLAYS COUNTDOWN

    JTextField number_right = new JTextField();    //WILL APPEAR AFTER WE CALCULATE THE RESULTS
    JTextField percentage = new JTextField(); //DISPLAYS PERCENTAGE OF SCORE


    Timer timer = new Timer(1000, new ActionListener()//EXECUTES EVERY 1 SECOND (1000 MILISECONDS)
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            seconds--;// COUNTDOWN OF SECONDS LEFT BY 1
            seconds_left.setText(String.valueOf(seconds));
            if(seconds<=0)
            {
                displayAnswer();
            }

        }
    });



    public Quiz()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650,650);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(null);//NOT TO USE A LAYOUT MANAGER,TO SIZE EVERYTHING BY HAND
        frame.setResizable(false);//IF YOU DO NOT WANT PEOPLE T RESIZE THE FRAME

        textField.setBounds(0,0,650,50);//HOLDS NUMBER WE ARE ON
        // /SET BOUNDS WILL DETERMINE WHERE THE TEXT FIELD WILL BE PLACES AS WELL AS HEIGHT+WIDTH
        textField.setBackground(new Color(25,25,25));
        textField.setForeground(new Color(25,255,0));
        textField.setFont(new Font("Ink Free", Font.BOLD,30 ));
        textField.setBorder(BorderFactory.createBevelBorder(1));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);//NOBODY CAN EDIT THE TEXTFIELD
        //textField.setText("testing test");

        textArea.setBounds(0,50,650,50);//HOLDS NUMBER WE ARE ON
        // /SET BOUNDS WILL DETERMINE WHERE THE TEXT FIELD WILL BE PLACES AS WELL AS HEIGHT+WIDTH
        textArea.setLineWrap(true);//INCASE THE TEXT GOES OFF THE SCREEN
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(25,25,25));
        textArea.setForeground(new Color(25,255,0));
        textArea.setFont(new Font("MV Boli", Font.BOLD,25 ));
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setEditable(false);//NOBODY CAN EDIT THE TEXTFIELD
        //textField.setText("testing test");
        textArea.setText("SAMPLE TEXT");


        //BUTTONS
        buttonA.setBounds(0,100,100,100);
        buttonA.setFont(new Font("MV Boli", Font.BOLD,35 ));
        buttonA.setFocusable(false);//STOPS CHOSEN BUTTON BEING HIGHLIGHTED
        buttonA.addActionListener(this);
        buttonA.setText("A");

        buttonB.setBounds(0,200,100,100);//SLIGHTLY DIFFERENT LOCATION
        buttonB.setFont(new Font("MV Boli", Font.BOLD,35 ));
        buttonB.setFocusable(false);//STOPS CHOSEN BUTTON BEING HIGHLIGHTED
        buttonB.addActionListener(this);
        buttonB.setText("B");


        buttonC.setBounds(0,300,100,100);
        buttonC.setFont(new Font("MV Boli", Font.BOLD,35 ));
        buttonC.setFocusable(false);//STOPS CHOSEN BUTTON BEING HIGHLIGHTED
        buttonC.addActionListener(this);
        buttonC.setText("C");


        buttonD.setBounds(0,400,100,100);
        buttonD.setFont(new Font("MV Boli", Font.BOLD,35 ));
        buttonD.setFocusable(false);//STOPS CHOSEN BUTTON BEING HIGHLIGHTED
        buttonD.addActionListener(this);
        buttonD.setText("D");


        answer_labelA.setBounds(125,100,500,100);
        answer_labelA.setBackground(new Color(50,50,50));
        answer_labelA.setForeground(new Color(25,255,0));
        answer_labelA.setFont(new Font("MV Boli",Font.PLAIN,35));

        answer_labelB.setBounds(125,200,500,100);//SLIGHLY BELOW LABELA
        answer_labelB.setBackground(new Color(50,50,50));
        answer_labelB.setForeground(new Color(25,255,0));
        answer_labelB.setFont(new Font("MV Boli",Font.PLAIN,35));

        answer_labelC.setBounds(125,300,500,100);
        answer_labelC.setBackground(new Color(50,50,50));
        answer_labelC.setForeground(new Color(25,255,0));
        answer_labelC.setFont(new Font("MV Boli",Font.PLAIN,35));

        answer_labelD.setBounds(125,400,500,100);
        answer_labelD.setBackground(new Color(50,50,50));
        answer_labelD.setForeground(new Color(25,255,0));
        answer_labelD.setFont(new Font("MV Boli",Font.PLAIN,35));

        seconds_left.setBounds(535,510,100,100);
        seconds_left.setBackground(new Color(25,25,25));
        seconds_left.setForeground(new Color(255,0,0));
        seconds_left.setFont(new Font("Ink Free", Font.BOLD,60));
        seconds_left.setBorder(BorderFactory.createBevelBorder(1));
        seconds_left.setOpaque(true);
        seconds_left.setHorizontalAlignment(JTextField.CENTER);
        seconds_left.setText(String.valueOf(seconds));//WILL CONVERT SECONDS TO A STRING AND DISPLAY IT


        time_label.setBounds(535,475,100,25);
        time_label.setBackground(new Color(50,50,50));
        time_label.setForeground(new Color(255,0,0));
        time_label.setFont(new Font("MV Boli", Font.PLAIN,16));
        time_label.setHorizontalAlignment(JTextField.CENTER);
        time_label.setText("TIMER");

        number_right.setBounds(225,225,200,100);
        number_right.setBackground(new Color(25,25,25));
        number_right.setForeground(new Color(25,255,0));
        number_right.setFont(new Font("Ink Free", Font.BOLD,50));
        number_right.setBorder(BorderFactory.createBevelBorder(1));
        number_right.setHorizontalAlignment(JTextField.CENTER);
        number_right.setEditable(false);


        percentage.setBounds(225,325,200,100);
        percentage.setBackground(new Color(25,25,25));
        percentage.setForeground(new Color(25,255,0));
        percentage.setFont(new Font("Ink Free", Font.BOLD,50));
        percentage.setBorder(BorderFactory.createBevelBorder(1));
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false);





        //frame.add(number_right);
        //frame.add(percentage);
        frame.add(time_label);
        frame.add(seconds_left);
        frame.add(answer_labelA);
        frame.add(answer_labelB);
        frame.add(answer_labelC);
        frame.add(answer_labelD);

        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);

        frame.add(textArea);
        frame.add(textField);
        frame.setVisible(true);
        NextQuestion();

    }


    public void NextQuestion()
    {
        if(index>= total_questions)
        {
            results();//IF NO MORE QUESTIONS
        }
        else{//SETS QUESTION WITH OPTION LABELS
            textField.setText("Question " + (index+1));
            textArea.setText(questions[index]);
            answer_labelA.setText(options[index][0]);
            answer_labelB.setText(options[index][1]);
            answer_labelC.setText(options[index][2]);
            answer_labelD.setText(options[index][3]);
            timer.start();

        }
    }


    @Override
    public void actionPerformed(ActionEvent e)//IS TRIGGERED WHEN SOMEONE CLICKS A BUTTON
    {
        //TEMPORARY DE-ACTIVATE ALL BUTTONS
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        /////////DETERMINE WHICH BUTTON IS PRESSED AND IF IT MATCHES WITH ANSWER:////////////////
        if(e.getSource()==buttonA)//IF SOMEONE PRESSES BUTTON A
        {
            answer = 'A';
            if(answer==Answers[index])//IF ANSWER == ANSWER FROM ARRAY OF ANSWERS
            {
                correct_guesses++;//INCREASE CORRECT ANSWERS COUNT
            }
        }
        if(e.getSource()==buttonB)
        {
            answer = 'B';
            if(answer==Answers[index])
            {
                correct_guesses++;
            }
        }
        if(e.getSource()==buttonC)
        {
            answer = 'C';
            if(answer==Answers[index])
            {
                correct_guesses++;
            }
        }
        if(e.getSource()==buttonD)
        {
            answer = 'D';
            if(answer==Answers[index])
            {
                correct_guesses++;
            }
        }
        displayAnswer();

    }

    public void displayAnswer()
    {
        timer.stop();
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if(Answers[index]!= 'A')
        {
            answer_labelA.setForeground(new Color(255,0,0));
        }
        if(Answers[index]!= 'B')
        {
            answer_labelB.setForeground(new Color(255,0,0));
        }
        if(Answers[index]!= 'C')
        {
            answer_labelC.setForeground(new Color(255,0,0));
        }
        if(Answers[index]!= 'D')
        {
            answer_labelD.setForeground(new Color(255,0,0));
        }

        ////////////////////////2 SECOND PAUSE UNTILL TEXT TURNS BACK TO GREEN////////////////
        Timer pause = new Timer(2000, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                answer_labelA.setForeground(new Color(25,255,0));
                answer_labelB.setForeground(new Color(25,255,0));
                answer_labelC.setForeground(new Color(25,255,0));
                answer_labelD.setForeground(new Color(25,255,0));
                answer = ' ';
                seconds=10; //RESET SECONDS TIMER
                seconds_left.setText(String.valueOf(seconds));
                //RE-ENABLE BUTTONS
                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);
                index++; //INCREASE INDEX VY 1 TO GO TO NEXT QUESTION
                NextQuestion();


            }
        });
        pause.setRepeats(false);
        pause.start();

    }



    public void results()
    {//DISABLE THE BUTTONS AFTER THE RIGHT ANSWER IS SHOWN
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        //PERCENTAGE
        result = (int)((correct_guesses/(double)total_questions)*100);

        textField.setText("RESULTS!");
        //CLEAR EVERYTHING ELSE
        textArea.setText(" ");
        answer_labelA.setText(" ");
        answer_labelB.setText(" ");
        answer_labelC.setText(" ");
        answer_labelD.setText(" ");

        number_right.setText("("+correct_guesses + "/" + total_questions+ ")");
        percentage.setText(result+"%");


        frame.add(percentage);
        frame.add(number_right);
    }




}
