package net.obs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.obs.services.PageService;


@Controller
public class PageController {
	@Autowired
	PageService pageService;

	@GetMapping("/")
	public String showMainPage(Model model) {
		return pageService.showMainPage(model);
	};

	@GetMapping("/profile")
	public String showProfilePage(Model model) {
		return pageService.showProfilePage(model);
	}

	@GetMapping("/clinic")
	public String showClinicPage(Model model) {
		return pageService.showClinicPage(model);
	}

	@GetMapping("/hospital")
	public String showHospitalPage(Model model) {
		return pageService.showHospitalPage(model);
	}

	@GetMapping("/error")
	public String showErrorPage(Model model) {
		return pageService.showErrorPage(model);
	}

	@GetMapping("/register")
	public String showRegisterPage(Model model) {
		return pageService.showRegisterPage(model);
	}

	@GetMapping("/reserve")
	public String showReservePage(Model model) {
		return pageService.showReservePage(model);
	}

	@GetMapping("/aboutPage")
	public String showAboutPage(Model model) {
		return pageService.showAboutPage(model);
	}
}
