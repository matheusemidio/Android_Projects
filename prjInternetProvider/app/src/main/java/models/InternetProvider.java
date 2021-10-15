package models;

import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class InternetProvider implements Serializable {

    private int clientNumber;
    private String provider;
    private int numberMonth;

    public InternetProvider(int clientNumber, String provider, int numberMonth) {
        this.clientNumber = clientNumber;
        this.provider = provider;
        this.numberMonth = numberMonth;
    }

    @NonNull
    @Override
    public String toString() {
        return "Client number: " + clientNumber + " | Provider: " +
                provider + " | Number of months: " + numberMonth +
                " | Amount: " + getAmount();
    }

    public float getAmount()
    {

        float price = getPrice();
        float TPS = getTPS(price);
        float TVB = getTVB(price);
        float total = (price + TPS + TVB);
        return total;
    }

    private float getTVB(float price)
    {
        float TVB = (float) ((price * 9.995) / 100);
        return TVB;
    }

    private float getTPS(float price)
    {
        float TPS = (float) ((price * 6) / 100 );
        return TPS;
    }

    private float getPrice()
    {
        int months = getNumberMonth();
        float price = 0;
        if(provider.equals("Bell"))
        {
            if(months >= 1 && months < 12)
            {
                price = (months * 60);
                return price ;
            }
            else if(months == 12)
            {
                price = 600;
                return price;
            }
        }
        else if(provider.equals("Videotron"))
        {
            if(months >= 1 && months < 6)
            {
                price = (months * 70);
                return price;
            }
            else if(months >=6 && months < 12)
            {
                price = (350 + ( (months - 6) * 70) );
                return price;
            }
            else if(months == 12)
            {
                price = 12 * 70;
                //30% off
                price = price - ( (price * 30) /100);
                return price;
            }
        }
        else if(provider.equals("aCanac"))
        {
            if(months >= 1 && months < 12)
            {
                price = (months * 45);
                return price;
            }
            else if(months == 12)
            {
                price = (11 * 45);
                return price;
            }
        }
        return price;
    }

    public int getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(int clientNumber) {
        this.clientNumber = clientNumber;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public int getNumberMonth() {
        return numberMonth;
    }

    public void setNumberMonth(int numberMonth) {
        this.numberMonth = numberMonth;
    }
}
