package com.example.demo.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "ownerName")
    private String ownerName;
    @Column(name = "ownerId")
    private String ownerId;
    @Column(name = "postDescription")
    private String postDescription;
    @Column(name = "postImage")
    private String postImage;
}
