package com.metaxiii.fr.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.metaxiii.fr.model.Item;
import com.metaxiii.fr.model.Views;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    @JsonView(Views.Public.class)
    @GetMapping("/items-public")
    public Item getItemPublic() {
        return new Item(1, "item name", "owner");
    }

    @JsonView(Views.Internal.class)
    @GetMapping("/items-internal")
    public Item getItemInternal() {
        return new Item(1, "item name", "owner");
    }
}
