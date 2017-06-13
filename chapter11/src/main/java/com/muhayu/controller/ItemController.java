package com.muhayu.controller;

import com.muhayu.domain.item.Book;
import com.muhayu.domain.item.Item;
import com.muhayu.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hyecheon on 2017. 6. 13..
 */
@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping(value = "/items")
    public String list(Model model) {
        final List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "/items/itemList";
    }

    @GetMapping(value = "/items/{id}/edit")
    public String updateItemForm(@PathVariable Long id, Model model) {
        final Item item = itemService.findOne(id);
        model.addAttribute(item);
        return "/item/updateItemForm";
    }

    @PostMapping(value = "/items/{id}/edit")
    public String updateItem(@ModelAttribute("item") Book item) {
        itemService.saveItem(item);
        return "redirect:/items";
    }

    @RequestMapping(value = "/items/new", method = RequestMethod.GET)
    public String createForm(@ModelAttribute("item") Book item) {
        return "/items/createItemForm";
    }

    @RequestMapping(value = "/items/new", method = RequestMethod.POST)
    public String create(@ModelAttribute("item") Book item) {
        itemService.saveItem(item);
        return "redirect:/items";
    }
}
