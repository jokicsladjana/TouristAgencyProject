package com.agency.touristagency.user_interface.paneli;

public enum PlainPassSupportedAlgorithm {
    HASH_ALGORITHM_NAME("PBKDF2WithHmacSHA1");

    private final String name;

    PlainPassSupportedAlgorithm(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

