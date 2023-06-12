package Structure;

public class Record {
    String From,To,Date,CarType;
    int Days,Fare;

    public Record(String from, String to, String date, int days, String carType, int fare) {
        From = from;
        To = to;
        Date = date;
        Days = days;
        CarType = carType;
        Fare = fare;
    }

    public Record() {
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getDays() {
        return Days;
    }

    public void setDays(int days) {
        Days = days;
    }

    public String getCarType() {
        return CarType;
    }

    public void setCarType(String carType) {
        CarType = carType;
    }

    public int getFare() {
        return Fare;
    }

    public void setFare(int fare) {
        Fare = fare;
    }
    public int CalculatedDistance(String from, String to)
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
    public int calculatefare(int distance, int days, String type)
    {
        if(type.equals("4-Seater"))
        {
            Fare = (CalculatedDistance(getFrom(),getTo())*5) + (days*1000);
        }
        else
        {
            Fare = (CalculatedDistance(getFrom(),getTo())*7) + (days*1000);
        }
        return Fare;
    }
}