package com.example.shopping.item.repository;

import com.example.shopping.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByCategory_id(Long category_id);
}
