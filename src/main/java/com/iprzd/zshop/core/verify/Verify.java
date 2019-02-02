package com.iprzd.zshop.core.verify;

import java.util.ArrayList;
import java.util.List;

public class Verify {

    private List<Verifier> list = new ArrayList<>();
    private boolean result;
    private String message;
    private List<String> messages;

    public Verify(Verifier verifier) {
        this.list.add(verifier);
    }

    public Verify add(Verifier verifier) {
        this.list.add(verifier);
        return this;
    }

    public boolean verify(){
        for (Verifier verifier : list) {
            if (!verifier.verify()) {
                this.message = verifier.getMessage();
                this.result = false;
                break;
            }
        }
        this.result = true;
        return true;
    }

    public boolean verifyAll() {
        this.result = true;
        for (Verifier verifier : list) {
            if (!verifier.verify()) {
                this.result = false;
                this.message += verifier.getMessage() + ",";
                this.messages.add(verifier.getMessage());
            }
        }
        return this.result;
    }
}
