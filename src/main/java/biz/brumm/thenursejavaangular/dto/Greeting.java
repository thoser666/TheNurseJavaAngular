package biz.brumm.thenursejavaangular.dto;

import lombok.Data;

@Data
public class Greeting {
    private String msg;
    private String name;

    public Greeting() {

    }

    public Greeting(String msg, String name) {
        this.msg = msg;
        this.name = name;
    }
}
