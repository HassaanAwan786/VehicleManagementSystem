package com.backcaps.vehiclemanagement;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author moiza
 */
public class Person {
    String FROM, TO, DATE, CARTYPE;
    int days,fare,distance;
    String Password,Name,UserName,DOB;
    Person()
    {
        FROM = "";
        TO ="";
        DATE = "";
        CARTYPE = "";
        days=0;
        fare = 0;
        distance = 0;
    }
    void setFROM(String FROM)
    {
        this.FROM = FROM;
    }
     void setTO(String TO)
    {
        this.TO = TO;
    }
      void setDATE(String DATE)
    {
        this.DATE = DATE;
    }
       void setCARTYPE(String CARTYPE)
    {
        this.CARTYPE = CARTYPE;
    }
    void setdays(int days)
    {
        this.days = days;
    }
    String getFROM()
    {
        return FROM;
    }
     String getTO()
    {
        return TO;
    }
      String getDATE()
    {
        return DATE;
    }
       String getCARTYPE()
    {
        return CARTYPE;
    }
       void setName(String Name)
       {
           this.Name = Name;
       }
       void setDOB(String DOB)
       {
           this.DOB = DOB;
       }
       void setUserName(String UserName)
       {
           this.UserName = UserName;
       }
       void setPassword(String Password)
       {
           this.Password = Password;
       }
       String getName()
       {
           return Name;
       }
       String getUserName()
       {
           return UserName;
       }
       String getDOB()
       {
           return DOB;
       }
       String getPassword()
       {
           return Password;
       }
    int getdays()
    {
        return days;
    }
    int CalculatedDistance(String from,String to)
    {
        if(from.equals("Karachi")&&to.equals("Lahore")||to.equals("Karachi")&&from.equals("Lahore"))
        {
            return 1200;
        }
        if(from.equals("Karachi")&&to.equals("Islamabad")||to.equals("Karachi")&&from.equals("Islamabad"))
        {
            return 1500;
        }
        if(from.equals("Karachi")&&to.equals("Multan")||to.equals("Karachi")&&from.equals("Multan"))
        {
            return 800;
        }
        if(from.equals("Islamabad")&&to.equals("Multan")||to.equals("Islamabad")&&from.equals("Multan"))
        {
            return 650;
        }
        if(from.equals("Islamabad")&&to.equals("Lahore")||to.equals("Islamabad")&&from.equals("Lahore"))
        {
            return 350;
        }
        if(from.equals("Lahore")&&to.equals("Multan")||to.equals("Lahore")&&from.equals("Multan"))
        {
            return 300;
        }
        
        return 100;
    }
    int calculatefare(int distance,int days,String type)
    {
        if(type.equals("4-Seater"))
        {
        fare = (CalculatedDistance(getFROM(),getTO())*5) + (days*1000);
        }
        else
        {
            fare = (CalculatedDistance(getFROM(),getTO())*7) + (days*1000);
        }
        return fare;
    }
    void setFare(int fare)
    {
        this.fare = fare;
    }
    int getfare()
    {
        return fare;
    }
}
