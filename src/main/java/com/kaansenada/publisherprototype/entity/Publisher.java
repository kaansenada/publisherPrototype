package com.kaansenada.publisherprototype.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Publisher")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long publisherID;
    @Column(name = "publisherName")
    private String publisherName;
}
