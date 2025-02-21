package com.example.hamecobooking.dto.designer;

import lombok.*;

public class GetAvailableHour{

    public static class Request {
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long designerId;
        private String designerName;
        private int availableHourId;
        private int hour;
        private boolean isAvailable;
    }
}