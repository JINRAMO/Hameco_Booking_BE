package com.example.hamecobooking.dto.procedure;
import lombok.*;

public class GetProcedure {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Request {
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


        public static GetProcedure.Response from(ProcedureDto procedureDto){
            return GetProcedure.Response.builder()
                    .designerName(procedureDto.getDesigner().getUsername())
                    .procedureName(procedureDto.getProcedureName())
                    .duration(procedureDto.getDuration())
                    .description(procedureDto.getDescription())
                    .price(procedureDto.getPrice())
                    .build();
        }
    }
}
