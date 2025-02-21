package com.example.hamecobooking.dto.procedure;

import lombok.*;

public class CreateProcedure {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Request {
        private String designerName;
        private String procedureName;
        private int duration;
        private String description;
        private int price;

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private String designerName;
        private String procedureName;
        private int duration;
        private String description;
        private int price;


        public static CreateProcedure.Response from(ProcedureDto procedureDto){
            return CreateProcedure.Response.builder()
                    .designerName(procedureDto.getDesigner().getUsername())
                    .procedureName(procedureDto.getProcedureName())
                    .duration(procedureDto.getDuration())
                    .description(procedureDto.getDescription())
                    .price(procedureDto.getPrice())
                    .build();
        }
    }
}
