package com.example.prjmidtermreview.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class ClientOrder implements Serializable
{
    private int clientNumber;
    private String clientName;
    private String pizzaType;
    private int nbSlices;


    public ClientOrder(int clientNumber, String clientName, String pizzaType, int nbSlices)
    {
        this.clientNumber = clientNumber;
        this.clientName = clientName;
        this.pizzaType = pizzaType;
        this.nbSlices = nbSlices;
    }

    public int getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(int clientNumber) {
        this.clientNumber = clientNumber;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPizzaType() {
        return pizzaType;
    }

    public void setPizzaType(String pizzaType) {
        this.pizzaType = pizzaType;
    }

    public int getNbSlices() {
        return nbSlices;
    }

    public void setNbSlices(int nbSlices) {
        this.nbSlices = nbSlices;
    }

    @NonNull
    @Override
    public String toString()
    {
        return "Client Nb: " + getClientNumber() + " | Client Name: " + getClientName() + " | Pizza Type: " + getPizzaType() + " | Total: " + getAmount();
    }
    public float getAmount()
    {
        float amount = 0;
        switch (pizzaType)
        {
            case "Cheese":
                amount = nbSlices * 1f;
                break;
            case "Vegetarian":
                amount = nbSlices * 5f;
                break;
            case "Mexican":
                amount = nbSlices * 10f;
                break;
            default:
                break;

        }
        return amount;
    }
}
