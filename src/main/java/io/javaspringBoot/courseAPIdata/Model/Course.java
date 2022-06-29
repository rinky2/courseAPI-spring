/*package io.javaspringBoot.courseAPIdata.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course
{
    @Id
    private int id;
    private String name;
    private String description;
    @ManyToOne
    private Topic topic;


    public Course(int id, String name, String description, int topicId) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;

        this.topic = new Topic();
    }

}
*/