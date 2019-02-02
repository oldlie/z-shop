package com.iprzd.zshop.core.verify;

public class LengthVerifier extends Verifier {

    private int min;
    private int max;

    public LengthVerifier(String value, int min, int max, String message) {
        super(value, message);
    }

    @Override
    public boolean verify() {
        return !(
                (min > 0 && this.value.toString().length() < min) ||
                        (max > min && this.value.toString().length() >= max)
        );
    }
}
