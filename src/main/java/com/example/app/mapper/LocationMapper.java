package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.Location;

@Mapper
public interface LocationMapper {
	//locations テーブルから全場所のリストを取得する。
		List<Location>selectAll();
}
