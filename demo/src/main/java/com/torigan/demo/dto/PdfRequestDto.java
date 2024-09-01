package com.torigan.demo.dto;

public class PdfRequestDto {
    private String name;
    private String address;
    private String date;

    // Default constructor
    public PdfRequestDto() {}

    // Parameterized constructor
    public PdfRequestDto(String name, String address, String date) {
        this.name = name;
        this.address = address;
        this.date = date;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
