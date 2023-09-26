package com.automation.apitesting.tests.pojos;

public class BookingDetails {

    private String firstname;
    private String lastname;
    private String additionalneeds;
    private int totalprice;
    private boolean depositpaid;

    public BookingDates getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(BookingDates bookingdates) {
        this.bookingdates = bookingdates;
    }

    private BookingDates bookingdates;

    public BookingDetails() {

    }

    public BookingDetails(String fname, String lname, String needs, int price, boolean paid, BookingDates bookingdates){
        setFirstname(fname);
        setLastname(lname);
        setAdditionalneeds(needs);
        setTotalprice(price);
        setDepositpaid(paid);
        setBookingdates(bookingdates);
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }
}
