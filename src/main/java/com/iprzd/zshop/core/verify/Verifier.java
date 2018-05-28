package com.iprzd.zshop.core.verify;

public class Verifier<T> implements IVerifier {

    protected T value;
    protected String message;

    public Verifier() {}

    public Verifier(T value, String message) {
        this.value = value;
        this.message = message;
    }

    @Override
    public boolean verify() {
        return false;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
