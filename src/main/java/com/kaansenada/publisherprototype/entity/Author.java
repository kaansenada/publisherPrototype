package com.kaansenada.publisherprototype.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Author")
@Getter
@Setter
@ToString
public class Author {
    @Id
    @Column(name = "authorID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long authorId;

    @Column(name = "authorNameSurname")
    String authorNameSurname;

    @OneToOne
    @JoinColumn(name = "bookID" , foreignKey = @ForeignKey(name = "FK_BOOK_AUTHOR"))
    Book book;
    /**
     * Burada neden book tutuluyor? bir kitabın bir yazarı vardır fakat bir yazarın birden fazla kitabı olabilir.
     * One to many ilişkisi book nesnesinde tutulması daha mantıklı değil mi sor ??
     */

}
