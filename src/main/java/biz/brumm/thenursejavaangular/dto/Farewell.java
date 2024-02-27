package biz.brumm.thenursejavaangular.dto;

import lombok.Data;

@Data
public class Farewell {
    private String message;
    private Integer remainingMinutes;

    public Farewell() {
    }

    public Farewell(String message, Integer remainingMinutes) {
        this.message = message;
        this.remainingMinutes = remainingMinutes;
    }
}
