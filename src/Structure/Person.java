package Structure;
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
    int days,fare;
    String Password,Name,UserName,DOB,Role;

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public Person()
    {
        FROM = "";
        TO ="";
        DATE = "";
        CARTYPE = "";
        days=0;
        fare = 0;
    }

    public void setName(String Name)
    {
        this.Name = Name;
    }
    public void setDOB(String DOB)
    {
        this.DOB = DOB;
    }
    public void setUserName(String UserName)
    {
        this.UserName = UserName;
    }
    public void setPassword(String Password)
    {
        this.Password = Password;
    }
    public String getName()
    {
        return Name;
    }
    public String getUserName()
    {
        return UserName;
    }
    public String getDOB()
    {
        return DOB;
    }
    public String getPassword()
    {
        return Password;
    }

}