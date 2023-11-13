package com.underlake.honey.domain;

public record DeleteResult(String message) {

    public static DeleteResult ok() {
        return new DeleteResult("success");
    }

    public static DeleteResult fail() {
        return new DeleteResult("failure");
    }
}
