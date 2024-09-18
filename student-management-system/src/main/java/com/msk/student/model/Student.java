package com.msk.student.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
@SessionScope
public class Student {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String location;
    private String branch;
}
