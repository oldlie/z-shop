package com.iprzd.zshop.core.verify;

public class NotEmptyVerifier extends Verifier {

    public NotEmptyVerifier(String value, String message) {
        super(value, message);
    }

    @Override
    public boolean verify() {
        return value != null && !"".equals(value);
    }
}
