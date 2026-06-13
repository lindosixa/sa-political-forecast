package com.mylindwana.opiniontracker;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;


public class OpinionApp extends JFrame {
    private ArrayList<OpinionQuestion> questions;
    private DefaultListModel<OpinionQuestion> listModel;
    private JList<OpinionQuestion> questionList; 
    private JLabel titleLabel;
    private JLabel categoryLabel;
    private JLabel totalVotesLabel;
    private JLabel yesLabel;
    private JLabel noLabel;
    private JLabel unsureLabel;
    private JProgressBar yesBar;
    private JProgressBar noBar;
    private JProgressBar unsureBar; 
    private JButton yesButton;
    private JButton noButton;
    private JButton unsureButton;

    
    OpinionApp(){
        setTitle("Opinion Tracker");
        setSize(900, 550);
        setLocationRelativeTo(null);        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        questions = new ArrayList<>();
        listModel = new DefaultListModel<>();
        
        createSampleQuestions();
        createLayout();
        loadSelectedQuestion();
           
    }
    
    private void createSampleQuestions(){
        
        questions.add(new OpinionQuestion(
                "Will voter turnout exceed 60% in the next national election?",
                "Elections"
        ));
        
        questions.add(new OpinionQuestion(
                "Will a coalition government change in Gauteng before year-end?",
                "Coalitions"
        ));

        questions.add(new OpinionQuestion(
                "Will Parliament pass a major electoral reform bill this year?",
                "Parliament"
        ));
        
        for (OpinionQuestion question : questions) {
            listModel.addElement(question);
        }

          
    }
    
    private void createLayout(){
        setLayout(new BorderLayout());
        
        JLabel heading = new JLabel("Opinion Tracker");
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        add(heading, BorderLayout.NORTH);
        
        questionList = new JList<>(listModel);
        questionList.setSelectedIndex(0);
        
        questionList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                loadSelectedQuestion();
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(questionList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Opinion Questions"));
        add(scrollPane, BorderLayout.WEST);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel, BorderLayout.CENTER);
        
        JPanel infoPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        
        titleLabel = new JLabel();
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        categoryLabel = new JLabel();
        totalVotesLabel = new JLabel();
        
        infoPanel.add(titleLabel);
        infoPanel.add(categoryLabel);
        infoPanel.add(totalVotesLabel);
        
        mainPanel.add(infoPanel, BorderLayout.NORTH);
        
        JPanel trackerPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        trackerPanel.setBorder(BorderFactory.createTitledBorder("Opinion live Tracker"));
        
        yesLabel = new JLabel();
        yesBar = new JProgressBar(0, 100);
        yesBar.setStringPainted(true);
        
        noLabel = new JLabel();
        noBar = new JProgressBar(0, 100);
        noBar.setStringPainted(true);

        unsureLabel = new JLabel();
        unsureBar = new JProgressBar(0, 100);
        unsureBar.setStringPainted(true);
        
        trackerPanel.add(yesLabel);
        trackerPanel.add(yesBar);

        trackerPanel.add(noLabel);
        trackerPanel.add(noBar);

        trackerPanel.add(unsureLabel);
        trackerPanel.add(unsureBar);
        
        mainPanel.add(trackerPanel, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        yesButton = new JButton("Yes");
        noButton = new JButton("No");
        unsureButton = new JButton("Unsure");
        
        yesButton.addActionListener(e -> submitVote("YES"));
        noButton.addActionListener(e -> submitVote("NO"));
        unsureButton.addActionListener(e -> submitVote("UNSURE"));
        
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        buttonPanel.add(unsureButton);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

    }
    

    private void submitVote(String voteType) {
        OpinionQuestion selectedQuestion = questionList.getSelectedValue();
        
        if (selectedQuestion == null) {
            JOptionPane.showMessageDialog(this, "Please select a forecast question first.");
            return;
        }
        
        switch (voteType) {
            case "YES":
                selectedQuestion.voteYes();
                break;

            case "NO":
                selectedQuestion.voteNo();
                break;

            case "UNSURE":
                selectedQuestion.voteUnsure();
                break;

            default:
                JOptionPane.showMessageDialog(this, "Invalid vote type.");
                return;
        }
        updateTracker(selectedQuestion);
    }
    
    private void loadSelectedQuestion(){
        OpinionQuestion selectedQuestion = questionList.getSelectedValue();
        
        if (selectedQuestion != null) {
            updateTracker(selectedQuestion);
        }
          
    }
    
    private void updateTracker(OpinionQuestion question) {
        titleLabel.setText(question.getTitle());
        categoryLabel.setText("Category: " + question.getCategory());
        totalVotesLabel.setText("Total forecasts: " + question.getTotalVotes());
        
        int yesPercentage = (int) Math.round(question.getYesPercentage());
        int noPercentage = (int) Math.round(question.getNoPercentage());
        int unsurePercentage = (int) Math.round(question.getUnsurePercentage());
        
        yesLabel.setText("Yes: " + question.getYesVotes() + " votes");
        noLabel.setText("No: " + question.getNoVotes() + " votes");
        unsureLabel.setText("Unsure: " + question.getUnsureVotes() + " votes");
        
        yesBar.setValue(yesPercentage);
        yesBar.setString(yesPercentage + "%");
        
        noBar.setValue(noPercentage);
        noBar.setString(noPercentage + "%");

        unsureBar.setValue(unsurePercentage);
        unsureBar.setString(unsurePercentage + "%");
        
    }
 
    
}
