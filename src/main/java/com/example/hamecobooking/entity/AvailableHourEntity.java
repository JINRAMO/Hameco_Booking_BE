package com.example.hamecobooking.entity;

import com.example.hamecobooking.converter.AvailableHourConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AvailableHourEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "designer_id")
    private DesignerEntity designer;
    @Column(columnDefinition = "JSON") // MySQL의 JSON 타입
    @Convert(converter = AvailableHourConverter.class)
    private Map<String, int[]> availableHours;
}
