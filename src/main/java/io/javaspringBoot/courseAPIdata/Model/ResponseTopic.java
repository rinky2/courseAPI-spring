package io.javaspringBoot.courseAPIdata.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public @Data class ResponseTopic<S>{
    private S body;
    private int status;
    private String message;

}
