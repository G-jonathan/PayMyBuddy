package com.openclassroomsproject.paymybuddy.backend.service;

public class ServiceUtils {
    final double CHARGES = 0.005;

    public double calculateCharges(double amount) {
        return amount * CHARGES;
    }
    public double calculateFinalAmountWithCharges(double amount) {
        return amount + (amount * CHARGES);
    }
}