package com.kaansenada.publisherprototype.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Book")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookID")
    private Long bookId;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "ISBN13")
    private String isbn13;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisherID", foreignKey = @ForeignKey(name = "FK_Book_Publisher"))
    private Publisher publisher;

}
