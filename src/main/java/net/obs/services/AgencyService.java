package net.obs.services;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import net.obs.enums.AgencyTypeEnum;
import net.obs.models.Agency;
import net.obs.repositories.UserRepository;
import net.obs.utils.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.obs.repositories.AgencyRepository;

import java.io.IOException;
import java.sql.Array;

@Service
public class AgencyService {
    @Autowired
    AgencyRepository agencyRepository;

    @Autowired
    UserRepository userRepository;

    // update the agency information by the api provided on the internet
    public void updateAgency() {

        RestTemplate restTemplate = new RestTemplate();
        String url =
                "https://data.nsw.gov.au/data/api/3/action/datastore_search?resource_id=85da884f-a9f5-4cb3-95e8-d6b81b0d2e3a&q=New South Wales&limit=10000";
        String res = restTemplate.getForEntity(url, String.class).getBody().toString();

        ObjectMapper mapper = new ObjectMapper();

        JsonNode agencies = null;
        try {
            agencies = mapper.readTree(res).get("result").get("records");
        } catch (JsonParseException je) {
            System.out.println(je);
        } catch (IOException ie) {
            System.out.println(ie);
        }

        for (JsonNode agency : agencies) {

            Agency newAgency = agencyRepository.findByAgencyName(agency.get("title").asText());

            if (newAgency == null) { // check the agency if it's already in the repository
                newAgency = new Agency();
            }

            String agencyName = agency.get("title").asText();
            newAgency.setAgencyName(agencyName);

            // if the agency is clinic the type will be clinic
            if (agencyName.contains("clinic") || agencyName.contains("Clinic")) {
                newAgency.setType(AgencyTypeEnum.clinic);
            } else {
                newAgency.setType(AgencyTypeEnum.hospital);
            }
            newAgency.setLongitude(((Double) agency.get("Longitude").asDouble()).floatValue());
            newAgency.setLatitude(((Double) agency.get("Latitude").asDouble()).floatValue());
            newAgency.setAddress(
                    agency.get("Clinic Address (field_clinic_address:address_line1)").asText());
            newAgency.setSuburb(
                    agency.get("Clinic Address (field_clinic_address:address_line1)").asText());
            newAgency.setPostCode(Integer.valueOf(agency.get("Postcode").asText()));
            newAgency.setPhone(agency.get("Clinic Phone").asText());
            newAgency.setWebsite(agency.get("Clinic Website").asText());
            newAgency.setInstruction(agency.get("Clinic Facility Instruction").asText());
            newAgency.setDriveThroughTesting(agency.get("Clinic Drive Through Testing").asText());
            newAgency.setAppointmentRequired(agency.get("Clinic Appointment Required").asText());
            newAgency.setPatientRestriction(agency.get("Clinic Patient Restriction").asText());
            newAgency.setPrescriptionRequired(agency.get("Clinic Prescription Required").asText());
            newAgency.setMondayOpeningHours(agency.get("Clinic Monday opening hours").asText());
            newAgency.setTuesdayOpeningHours(agency.get("Clinic Tuesday opening hours").asText());
            newAgency.setWednesdayOpeningHours(
                    agency.get("Clinic Wednesday opening hours").asText());
            newAgency.setThursdayOpeningHours(agency.get("Clinic Thursday opening hours").asText());
            newAgency.setFridayOpeningHours(agency.get("Clinic Friday opening hours").asText());
            newAgency.setSaturdayOpeningHours(agency.get("Clinic Saturday opening hours").asText());
            newAgency.setSundayOpeningHours(agency.get("Clinic Sunday opening hours").asText());
            newAgency.setStates(agency.get("States").asText());
            agencyRepository.save(newAgency);

        }
    }

    // Api for getting all Agency
    public ObjectNode getAgency() {
        ArrayNode agency = new ObjectMapper().valueToTree(agencyRepository.findAll());
        ObjectNode agencyNode = new ObjectMapper().createObjectNode();
        agencyNode.putArray("Agency").addAll(agency).get("Agency");
        ObjectNode returnMessage =
                new ReturnMessage(200, "Get Agency Success", agencyNode).toJson();
        return returnMessage;
    }

    // Api for getting the Hospital
    public ObjectNode getHospital(float longitude, float latitude) {
        ObjectNode hospitalNode = new ObjectMapper().createObjectNode();
        ArrayNode array = new ObjectMapper()
                .valueToTree(agencyRepository.findAllByType(AgencyTypeEnum.hospital));
        if (longitude != 0 && latitude != 0) {
            for (JsonNode agency : array) {
                float distance = getDistance(longitude, latitude,
                        agency.get("longitude").floatValue(), agency.get("latitude").floatValue());
                ((ObjectNode) agency).put("distance", distance);
            }
        }
        hospitalNode.putArray("Hospital").addAll(array);
        ObjectNode returnMessage =
                new ReturnMessage(200, "Get Hospital Success", hospitalNode).toJson();
        return returnMessage;
    }

    // Api for getting the Clinic
    public ObjectNode getClinic(float longitude, float latitude) {
        ObjectNode ClinicNode = new ObjectMapper().createObjectNode();
        ArrayNode array = new ObjectMapper()
                .valueToTree(agencyRepository.findAllByType(AgencyTypeEnum.clinic));
        if (longitude != 0 && latitude != 0) {
            for (JsonNode agency : array) {
                float distance = getDistance(longitude, latitude,
                        agency.get("longitude").floatValue(), agency.get("latitude").floatValue());
                ((ObjectNode) agency).put("distance", distance);
            }
        }
        ClinicNode.putArray("Clinic").addAll(array);
        ObjectNode returnMessage =
                new ReturnMessage(200, "Get Clinic Success", ClinicNode).toJson();
        return returnMessage;
    }

    // Function to get distance
    private float getDistance(float fromLongitude, float fromlatitude, float toLongitude,
            float toLatitude) {
        float EARTH_RADIUS = 6378137;
        float fromLonRad = (float) (fromLongitude * Math.PI / 180.0);
        float fromLatRad = (float) (fromlatitude * Math.PI / 180.0);
        float toLonRad = (float) (toLongitude * Math.PI / 180.0);
        float toLatRad = (float) (toLatitude * Math.PI / 180.0);

        float x = fromLatRad - toLatRad;
        float y = fromLonRad - toLonRad;

        float distance = (float) (2
                * Math.asin(Math.sqrt(Math.pow(Math.sin(x / 2), 2)
                        + Math.cos(fromLatRad) * Math.cos(toLatRad) * Math.pow(Math.sin(y / 2), 2)))
                * EARTH_RADIUS);

        return distance / 1000;
    }
}
