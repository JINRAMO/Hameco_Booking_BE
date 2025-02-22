package com.example.hamecobooking.dto.designer;

import com.example.hamecobooking.converter.AvailableHourConverter;
import com.example.hamecobooking.entity.DesignerEntity;
import lombok.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetAvailableHour {

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
        private Map<String, int[]> availableHours;  // int[] 대신 List<Integer>로 변경

        public static Response from(DesignerEntity designer) {
            System.out.println(designer.getAvailableHours().getAvailableHours());

            return Response.builder()
                    .designerId(designer.getDesignerId())
                    .designerName(designer.getUsername())
                    .availableHours(designer.getAvailableHours().getAvailableHours())
                    .build();
        }
    }
}
