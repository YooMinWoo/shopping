package com.example.shopping.file.entity;

import com.example.shopping.item.entity.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Setter
@Getter
@Builder
@Entity
@AllArgsConstructor
public class File {
    public File() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private String original_name;

    private String stored_name;

    private String file_path;

    private String content_type;

    private Long size;

    @CreationTimestamp
    private Date create_date;
}
