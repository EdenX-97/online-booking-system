package net.obs.controllers;


import java.text.ParseException;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import net.obs.repositories.RecordRepository;
import net.obs.services.AppointmentService;


@RestController
public class RecordController {
    @Autowired
    AppointmentService appointmentService;

    @Autowired
    RecordRepository recordRepository;

    // Api for getting the record for login user
    @GetMapping("/api/getRecord")
    public ObjectNode getRecord() {
        return appointmentService.getRecordByUser();
    }

    // Api for make an appointment
    @PostMapping("/api/makeAppointment")
    public ObjectNode makeAppointment(@RequestParam @NotNull String timeOfAppointment,
            @RequestParam @NotNull int agency_id) throws ParseException {
        return appointmentService.Appointment(timeOfAppointment, agency_id);
    }

    // Api for delete appointment
    @PostMapping("/api/deleteAppointment")
    public ObjectNode deleteAppontment(@RequestParam @NotNull String timeOfAppointment,
            @RequestParam @NotNull int agency_id) throws ParseException {
        return appointmentService.DeleteAppointment(timeOfAppointment, agency_id);
    }
}
