package net.obs.controllers;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import net.obs.repositories.AgencyRepository;
import net.obs.services.AgencyService;


@RestController
public class AgencyController {
    @Autowired
    AgencyRepository agencyRepository;

    @Autowired
    AgencyService agencyService;

    // Controller for getting all Agency
    @GetMapping("/api/getAgency")
    public ObjectNode getAgency() {
        return agencyService.getAgency();
    }

    // Controller for getting the Clinic
    @GetMapping("/api/getClinic")
    public ObjectNode getClinic(@NotNull float longitude, @NotNull float latitude) {
        return agencyService.getClinic(longitude, latitude);
    }

    // Controller for getting the Hospital
    @GetMapping("/api/getHospital")
    public ObjectNode getHospital(@NotNull float longitude, @NotNull float latitude) {
        return agencyService.getHospital(longitude, latitude);
    }
}


