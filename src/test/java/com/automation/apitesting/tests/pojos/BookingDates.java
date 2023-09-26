package com.automation.apitesting.tests.pojos;

public class BookingDates {
    private String checkin;
    private String checkout;

    public BookingDates(){

    }
     public BookingDates(String in, String out) {
        setCheckin(in);
        setCheckout(out);
     }
    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }
}
