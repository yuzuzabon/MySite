package com.example.app.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.domain.Item;
import com.example.app.mapper.ItemMapper;
import com.example.app.service.ItemService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

		private final ItemService service;
		private final ItemMapper mapper;

		@GetMapping
		public String showItems(Model model) {
			List<Item>items=mapper.selectAll();
			System.out.println(items);
			model.addAttribute("items",items);
			model.addAttribute("title","備品リスト");
			return "list";

		}
		@GetMapping("/add")
		public String showAddForm(Model model) {
		model.addAttribute("title", "備品の登録");
		model.addAttribute("items", new Item());
		model.addAttribute("locations", service.getItemLocations());
		System.out.println(service.getItemLocations());
		return "save";
		}

		@PostMapping("/add")
		public String addItem(
					@Valid Item item,Errors errors,
					RedirectAttributes rd,
					Model model) {
					model.addAttribute("items", new Item());

			if(errors.hasErrors()) {
					model.addAttribute("title","備品の登録");
					model.addAttribute("locations",service.getItemLocations());
					return "save";
			}
			service.addItem(item);
			rd.addFlashAttribute("statusMessage", "備品を登録しました。");
			return "redirect:/items";
		}

}
