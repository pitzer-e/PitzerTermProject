/*
    TicketModel.kt
    Ethan Pitzer
    2021-13-3

    The TicketModel class is a Java class for a ticket object, which stores information about the
    ticket within itself. This ticket is then passed as an instance into the database when called
    using methods within the fragments (i.e, Create Ticket / Edit Ticket). Getters, Setters, and
    a ToString method is provided in this class for added feature access and functionality
 */
package com.example.pitzertermproject;

public class TicketModel {
    private String userName;
    private String helpTopic;
    private String helpDescription;
    private int id;

    //  constructors

    public TicketModel(String userName, String helpTopic, String helpDescription, int id) {
        this.userName = userName;
        this.helpTopic = helpTopic;
        this.helpDescription = helpDescription;
        this.id = id;
    }

    public TicketModel() {
    }

    //  getters and setters

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getHelpTopic() {
        return helpTopic;
    }

    public void setHelpTopic(String helpTopic) {
        this.helpTopic = helpTopic;
    }

    public String getHelpDescription() {
        return helpDescription;
    }

    public void setHelpDescription(String helpDescription) {
        this.helpDescription = helpDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //  public to string method
    @Override
    public String toString() {
        return "TicketModel{" +
                "userName='" + userName + '\'' +
                ", helpTopic='" + helpTopic + '\'' +
                ", helpDescription='" + helpDescription + '\'' +
                ", id=" + id +
                '}';
    }
}
