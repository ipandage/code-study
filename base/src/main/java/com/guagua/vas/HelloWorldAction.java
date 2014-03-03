package com.guagua.vas;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HelloWorldAction {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		return "index";
	}

	@RequestMapping(value = "/springmvc/helloworld", method = RequestMethod.GET)
	public String helloworld(Model model) {
		model.addAttribute("msg", "springmvc helloworld例子演示成功啦...");
		return "helloworld";
	}
}
