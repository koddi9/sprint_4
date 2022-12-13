package model;

public class RentModel {
    String date;
    int period;

    public RentModel(String date, int period) {
        this.date = date;
        this.period = period;
    }

    public String getDate() {
        return date;
    }

    public int getPeriod() {
        return period;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
