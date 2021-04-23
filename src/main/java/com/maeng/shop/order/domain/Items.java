package com.maeng.shop.order.domain;

import com.maeng.shop.order.dto.ItemDto;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class Items {

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.PERSIST)
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public boolean contains(Item item) {
        return items.contains(item);
    }

    public List<ItemDto> toItemsDto() {
        return items.stream()
                .map(Item::toItemDto)
                .collect(Collectors.toList());
    }
}
