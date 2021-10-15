package model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class ClientOrder implements Serializable {
    private int clientNumber;
    private String pizzaType;
    private int numberSlices;

    public ClientOrder(int clientNumber, String pizzaType, int numberSlices) {
        this.clientNumber = clientNumber;
        this.pizzaType = pizzaType;
        this.numberSlices = numberSlices;
    }

    public int getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(int clientNumber) {
        this.clientNumber = clientNumber;
    }

    public String getPizzaType() {
        return pizzaType;
    }

    public void setPizzaType(String pizzaType) {
        this.pizzaType = pizzaType;
    }

    public int getNumberSlices() {
        return numberSlices;
    }

    public void setNumberSlices(int numberSlices) {
        this.numberSlices = numberSlices;
    }

    @NonNull
    @Override
    public String toString()
    {
        return "Client number: " + clientNumber + "| Pizza Type: " +
                pizzaType + "| Number of slices: " + numberSlices +
                "| Amount: " + getAmount();
    }

    public float getAmount()
    {
        float amount = 0;
        if(pizzaType.equals("Cheese"))
        {
            amount = numberSlices * 2.0f;
        }
        else if(pizzaType.equals("Vegetarian"))
        {
            amount = numberSlices * 2.5f;
        }
        else if(pizzaType.equals("Mexican"))
        {
            amount = numberSlices * 3.0f;
        }
        return amount;
    }
}
