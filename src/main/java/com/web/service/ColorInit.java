package com.web.service;

import java.util.ArrayList;
import java.util.List;

import com.web.model.ColorDto;

public class ColorInit {

	public static List<ColorDto> InitColor() {
		List<ColorDto> list = new ArrayList<>();
		list.add(new ColorDto("#fff","35"));
		list.add(new ColorDto("#fff","36"));
		list.add(new ColorDto("#fff","37"));
		list.add(new ColorDto("#fff","38"));
		list.add(new ColorDto("#fff","39"));
		list.add(new ColorDto("#fff","40"));
		list.add(new ColorDto("#fff","41"));
		list.add(new ColorDto("#fff","42"));
		list.add(new ColorDto("#fff","43"));
		list.add(new ColorDto("#fff","44"));
		return list;
	}
}
