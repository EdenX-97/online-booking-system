package net.obs.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import net.obs.enums.AgencyTypeEnum;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "agency")
public class Agency {

    public Agency() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AgencyTypeEnum type;

    @NotNull
    private String agencyName;

    @NotNull
    private float longitude;

    @NotNull
    private float latitude;

    @NotNull
    private String address;

    @NotNull
    private String suburb;

    @NotNull
    private String states;

    @NotNull
    private Integer postCode;

    @Nullable
    private String phone;

    @Nullable
    private String website;

    @Nullable
    @Column(columnDefinition = "LONGTEXT")
    private String instruction;

    @Nullable
    private String driveThroughTesting;

    @Nullable
    private String appointmentRequired;

    @Nullable
    private String patientRestriction;

    @Nullable
    private String prescriptionRequired;

    @Nullable
    private String mondayOpeningHours;

    @Nullable
    private String tuesdayOpeningHours;

    @Nullable
    private String wednesdayOpeningHours;

    @Nullable
    private String thursdayOpeningHours;

    @Nullable
    private String fridayOpeningHours;

    @Nullable
    private String saturdayOpeningHours;

    @Nullable
    private String sundayOpeningHours;

    // @OneToOne
    // @JoinColumn(name = "recordId")
    // private Record record;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public AgencyTypeEnum getType() { return type; }

    public void setType(AgencyTypeEnum type) { this.type = type; }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public Integer getPostCode() {
        return postCode;
    }

    public void setPostCode(Integer postCode) {
        this.postCode = postCode;
    }

    @Nullable
    public String getPhone() {
        return phone;
    }

    public void setPhone(@Nullable String phone) {
        this.phone = phone;
    }

    @Nullable
    public String getWebsite() {
        return website;
    }

    public void setWebsite(@Nullable String website) {
        this.website = website;
    }

    @Nullable
    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(@Nullable String instruction) {
        this.instruction = instruction;
    }

    @Nullable
    public String getDriveThroughTesting() {
        return driveThroughTesting;
    }

    public void setDriveThroughTesting(@Nullable String driveThroughTesting) {
        this.driveThroughTesting = driveThroughTesting;
    }

    @Nullable
    public String getAppointmentRequired() {
        return appointmentRequired;
    }

    public void setAppointmentRequired(@Nullable String appointmentRequired) {
        this.appointmentRequired = appointmentRequired;
    }

    @Nullable
    public String getPatientRestriction() {
        return patientRestriction;
    }

    public void setPatientRestriction(@Nullable String patientRestriction) {
        this.patientRestriction = patientRestriction;
    }

    @Nullable
    public String getPrescriptionRequired() {
        return prescriptionRequired;
    }

    public void setPrescriptionRequired(@Nullable String prescriptionRequired) {
        this.prescriptionRequired = prescriptionRequired;
    }

    @Nullable
    public String getMondayOpeningHours() {
        return mondayOpeningHours;
    }

    public void setMondayOpeningHours(@Nullable String mondayOpeningHours) {
        this.mondayOpeningHours = mondayOpeningHours;
    }

    @Nullable
    public String getTuesdayOpeningHours() {
        return tuesdayOpeningHours;
    }

    public void setTuesdayOpeningHours(@Nullable String tuesdayOpeningHours) {
        this.tuesdayOpeningHours = tuesdayOpeningHours;
    }

    @Nullable
    public String getWednesdayOpeningHours() {
        return wednesdayOpeningHours;
    }

    public void setWednesdayOpeningHours(@Nullable String wednesdayOpeningHours) {
        this.wednesdayOpeningHours = wednesdayOpeningHours;
    }

    @Nullable
    public String getThursdayOpeningHours() {
        return thursdayOpeningHours;
    }

    public void setThursdayOpeningHours(@Nullable String thursdayOpeningHours) {
        this.thursdayOpeningHours = thursdayOpeningHours;
    }

    @Nullable
    public String getFridayOpeningHours() {
        return fridayOpeningHours;
    }

    public void setFridayOpeningHours(@Nullable String fridayOpeningHours) {
        this.fridayOpeningHours = fridayOpeningHours;
    }

    @Nullable
    public String getSaturdayOpeningHours() {
        return saturdayOpeningHours;
    }

    public void setSaturdayOpeningHours(@Nullable String saturdayOpeningHours) {
        this.saturdayOpeningHours = saturdayOpeningHours;
    }

    @Nullable
    public String getSundayOpeningHours() {
        return sundayOpeningHours;
    }

    public void setSundayOpeningHours(@Nullable String sundayOpeningHours) {
        this.sundayOpeningHours = sundayOpeningHours;
    }

    // public Record getRecord() {
    //     return record;
    // }

    // public void setRecord(Record record) {
    //     this.record = record;
    // }
}
