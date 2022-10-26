package net.obs.services;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class PageService {
    public String showMainPage(Model model) {
        return "mainPage";
    }

    public String showProfilePage(Model model) {
        return "profilePage";
    }

    public String showClinicPage(Model model) {
        return "clinic";
    }

    public String showHospitalPage(Model model) {
        return "hospital";
    }

    public String showErrorPage(Model model) {
        return "error";
    }

    public String showRegisterPage(Model model) {
        return "register";
    }

    public String showReservePage(Model model) {
        return "reserve";
    }

    public String showAboutPage(Model model) {
        return "aboutPage";
    }
}
