package com.example.hamecobooking.dto.designer;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

public class CreateAvailableHour {

    @Getter
    @Setter
    public static class Request {
        private Map<String, int[]> availableHours;
    }

    public static class Response {
    }
}
