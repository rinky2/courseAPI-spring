package io.javaspringBoot.courseAPIdata.Model;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public @Data class TopicDTO {

    private int id;
    private String name;
    private String description;

}


