package com.cci.usertaskmngt.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cci_user")
public class User {

    @Id
    @GeneratedValue
    private long userId;

    private String userName;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Task> tasks;
}
