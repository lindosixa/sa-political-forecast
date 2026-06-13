package com.mylindwana.opiniontracker;


public class OpinionQuestion {

    private String title;
    private String category;
    private int yesVotes;
    private int noVotes;
    private int unsureVotes;
 
    
   public OpinionQuestion(String title, String category){
       this.title = title;
       this.category = category;
       this.yesVotes = 0;
       this.noVotes = 0;
       this.unsureVotes = 0;
       
   }
       
    public String getTitle(){
       return title; 
    }
    
    public String getCategory(){
       return category; 
    }
      
    public int getYesVotes(){
        return yesVotes;
    }
    
    public int getNoVotes(){
        return noVotes;
    }
    
    public int getUnsureVotes(){
        return unsureVotes;
    }
    
    public int getTotalVotes() {
        return yesVotes + noVotes + unsureVotes;
    }
    
    public void voteYes() {
        yesVotes++;
    }

    public void voteNo() {
        noVotes++;
    }

    public void voteUnsure() {
        unsureVotes++;
    }
    
    public double getYesPercentage() {
        if (getTotalVotes() == 0) {
            return 0;
        }

        return (yesVotes * 100.0) / getTotalVotes();
    }
    
    public double getNoPercentage() {
        if (getTotalVotes() == 0) {
            return 0;
        }

        return (noVotes * 100.0) / getTotalVotes();
    }
    
    public double getUnsurePercentage() {
        if (getTotalVotes() == 0) {
            return 0;
        }

        return (unsureVotes * 100.0) / getTotalVotes();
    }
    
    @Override
    public String toString() {
        return title;
    }
        
    
}
