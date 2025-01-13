package com.example.shopping.item.entity;

import com.example.shopping.category.entity.Category;
import com.example.shopping.file.entity.File;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
public class Item {
    public Item() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String name;
    private Long price;
    private Long count;
    private String content;

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<File> files;

    @CreationTimestamp
    private Date create_date;

    @UpdateTimestamp
    private Date update_date;

    public void addImages(List<File> files) {
        for(File file : files){
            file.setItem(this);
        }
        this.files = files;
    }
}
