package io.javaspringBoot.courseAPIdata.Model;

import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
public @Data class TopicDTO {

    private int id;
    private String name;
    private String description;

    public TopicDTO(TopicDAO dao) {
        //Converting from Entity to DTO
        this.id = dao.getId();
        this.name = dao.getName();
        this.description = dao.getDescription();
    }

}


