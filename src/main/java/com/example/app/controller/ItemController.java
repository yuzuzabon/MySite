package com.example.app.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
			System.out.println("getitem");
			return "list";

		}
		@GetMapping("/add")
		public String showAddForm(Model model) {
		model.addAttribute("title", "備品の登録");
		model.addAttribute("item", new Item());
		model.addAttribute("locations", service.getItemLocations());
		System.out.println(service.getItemLocations());
		System.out.println("getadd");
		return "save";
		}

		@PostMapping("/add")
		public String addItem(
					@Valid Item item,Errors errors,
					RedirectAttributes rd,
					Model model) {
					//model.addAttribute("items", new Item());
					System.out.println("post");

			if(errors.hasErrors()) {
				System.out.println("エラー内容："+errors.getAllErrors());
			}


			if(errors.hasErrors()) {
					model.addAttribute("title","備品の登録");
					model.addAttribute("locations",service.getItemLocations());
					System.out.println("postNG");
					return "save";
			}
			service.addItem(item);
			rd.addFlashAttribute("statusMessage", "備品を追加しました。");
			System.out.println("postok");
			return "redirect:/items";
		}
		@GetMapping("/detail/{id}")
		public String showItemDetail(@PathVariable Integer id,
															Model model) {
	    						Item item = service.getItemById(id);
	   if (item == null) {
	   	model.addAttribute("statusMessage", "お探しの備品は存在しません");
	   	model.addAttribute("title","備品管理");
	   	System.out.println("備品は存在しません");
	   	return "detail";
	  }
			model.addAttribute("title","備品管理");
			model.addAttribute("item",service.getItemById(id));
			return "detail";
		}
		@GetMapping("/delete/{id}")
		public String deleteItem(@PathVariable Integer id,
						RedirectAttributes rd) {
				service.deleteItem(id);
				rd.addFlashAttribute("statusMessage", "備品を削除しました");
				return "redirect:/items";
		}
		@GetMapping("/edit/{id}")
		public String showEditForm(
					@PathVariable Integer id,

					RedirectAttributes rd,
					Model model) {
				model.addAttribute("title","備品情報の編集");
				model.addAttribute("item",service.getItemById(id));
				//model.addAttribute("item", new Item());
				model.addAttribute("locations", service.getItemLocations());
				Item item = service.getItemById(id);
				System.out.println("取得した場所のID: " + (item.getLocation() != null ? item.getLocation().getId() : "NULLです！"));
				model.addAttribute("item", item);


				return "edit";
		}

}
