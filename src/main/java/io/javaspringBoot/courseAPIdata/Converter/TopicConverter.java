package io.javaspringBoot.courseAPIdata.Converter;

import io.javaspringBoot.courseAPIdata.Model.Topic;
import io.javaspringBoot.courseAPIdata.Model.TopicDTO;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TopicConverter {
    public TopicDTO entityToDto(Topic topic) {
        TopicDTO dto = Topic.builder().build();
        dto.setId(topic.getId());
        dto.setName(topic.getName());
        dto.setDescription(topic.getDescription());

        return dto;
    }

    public List<TopicDTO> entityToDto(List<Topic> topic) {
        return topic.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
    }


    public Topic dtoToEntity(TopicDTO dto) {
        Topic tp = Topic.builder().build();
        tp.setId(dto.getId());
        tp.setName(dto.getName());
        tp.setDescription(dto.getDescription());

        return tp;
    }

    public List<Topic> dtoToEntity(List<TopicDTO> dto) {
        return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }

}
