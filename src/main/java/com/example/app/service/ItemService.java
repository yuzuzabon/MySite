package com.example.app.service;

import java.util.List;

import com.example.app.domain.Item;
import com.example.app.domain.Location;

public interface ItemService {

	//備品リストを取得する
		List<Item>getAllItems();
	//ID番号に基づき１件分の備品情報を取得する	
		Item getItemById(Integer id);
	//備品を登録する
		void addItem(Item item);
	//備品情報を編集する
		void editItem(Item item);
	//ID番号に基づき１件分の備品情報を削除する
		void deleteItem(Integer id);
	//備品補完場所のリストを取得する
		List<Location>getItemLocations();
}
