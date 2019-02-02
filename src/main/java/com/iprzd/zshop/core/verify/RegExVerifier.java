package com.iprzd.zshop.core.verify;

import java.util.regex.Pattern;

public class RegExVerifier extends Verifier {

    private String expression;

    public RegExVerifier(String value, String message, String expression) {
        super(value, message);
        this.expression = expression;
    }

    @Override
    public boolean verify() {
        return Pattern.matches(this.expression, this.value.toString());
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
