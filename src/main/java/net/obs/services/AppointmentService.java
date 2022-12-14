package net.obs.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.obs.enums.StateEnum;
import net.obs.models.Agency;
import net.obs.models.Record;
import net.obs.models.User;
import net.obs.repositories.AgencyRepository;
import net.obs.repositories.RecordRepository;
import net.obs.repositories.UserRepository;
import net.obs.utils.ReturnMessage;


@Service
public class AppointmentService {
    @Autowired
    RecordRepository recordRepository;

    @Autowired
    AgencyRepository agencyRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    // make a appointment
    public ObjectNode Appointment(String timeOfAppointment, int agency_id) throws ParseException {
        ObjectNode returnMessage = null;
        if (agencyRepository.findById(agency_id).get() == null) { // check the agency
            returnMessage = new ReturnMessage(400, "No agency exists", null).toJson();
            return returnMessage;
        }
        Record newRecord = new Record();
        Agency agency = agencyRepository.findById(agency_id).get();
        User tempUser = userService.getLoginUserObj();
        if (tempUser == null) { // check the login user
            returnMessage = new ReturnMessage(400, "You need to login first", null).toJson();
            return returnMessage;
        }
        newRecord.setAgency(agency);
        newRecord.setUser(tempUser);
        newRecord.setState(StateEnum.book);
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date userTime = simpleDateFormat.parse(timeOfAppointment);
            // check the record if it is already exists
            if (recordRepository.findByRecordDatetimeAndAgencyAndStateAndUser(userTime, agency,
                    StateEnum.book, tempUser) != null) {
                returnMessage = new ReturnMessage(400, "Record exists", null).toJson();
                return returnMessage;
            } else if (!checkOpenDate(timeOfAppointment, agency)) {
                returnMessage = new ReturnMessage(400, "Agency not open this date").toJson();
                return returnMessage;
            }
            newRecord.setRecordDatetime(userTime);
        } catch (ParseException e) {
            returnMessage = new ReturnMessage(400, "Wrong Date format", e).toJson();
            return returnMessage;
        }
        recordRepository.save(newRecord);
        returnMessage = new ReturnMessage(200, "Make An Appointment success!", null).toJson();
        return returnMessage;
    }

    // Delete the appointment from record
    public ObjectNode DeleteAppointment(String time, int agency_id) throws ParseException {
        Agency agency = agencyRepository.findById(agency_id).get();
        User tempUser = userService.getLoginUserObj();
        if (tempUser == null) { // check if the user login
            ObjectNode returnMessage = new ReturnMessage(400, "No login user", null).toJson();
            return returnMessage;
        } else {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date tempTime = simpleDateFormat.parse(time);
                Record tempDelete = recordRepository.findByRecordDatetimeAndAgencyAndStateAndUser(
                        tempTime, agency, StateEnum.book, tempUser);
                if (tempDelete == null) { // check the record
                    ObjectNode returnMessage =
                            new ReturnMessage(400, "No booking record", null).toJson();
                    return returnMessage;
                }
                tempDelete.setState(StateEnum.delete);
                recordRepository.save(tempDelete);
                ObjectNode returnMessage = new ReturnMessage(200, "Delete success!", null).toJson();
                return returnMessage;
            } catch (ParseException e) { // check the date format
                ObjectNode returnMessage = new ReturnMessage(400, "Wrong Date format", e).toJson();
                return returnMessage;
            }
        }
    }

    // find the existing record of the login user
    public ObjectNode getRecordByUser() {
        ObjectNode tempUserRecord = new ObjectMapper().createObjectNode();
        User tempUser = userService.getLoginUserObj();
        if (tempUser == null) { // check if the user login
            ObjectNode returnMessage = new ReturnMessage(400, "No login user", null).toJson();
            return returnMessage;
        } else {
            List<Record> records = tempUser.getRecords();
            List<Record> returnRecords = new ArrayList<Record>();
            for (Record record : records) {
                record.setUser(null);
                if (record.getState() != StateEnum.delete) {
                    returnRecords.add(record);
                }
            }
            ArrayNode array = new ObjectMapper().valueToTree(returnRecords);
            tempUserRecord.putArray("Records").addAll(array);
            ObjectNode returnMessage =
                    new ReturnMessage(200, "Get record success!", tempUserRecord).toJson();
            return returnMessage;
        }
    }

    // ???????????????????????????????????????????????????true???????????????false
    public boolean checkOpenDate(String datetime, Agency a) {
        // System.out.println(a);
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar cal = Calendar.getInstance(); // ??????????????????
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK); // ???????????????1???
        if (w == 1) {
            if (a.getSundayOpeningHours().equals("null")) {
                return false;
            } else {
                return judge(a.getSundayOpeningHours(), datetime);
            }
        } else if (w == 2) {
            if (a.getMondayOpeningHours().equals("null")) {
                return false;
            } else {
                return judge(a.getMondayOpeningHours(), datetime);
            }
        } else if (w == 3) {
            if (a.getTuesdayOpeningHours().equals("null")) {
                return false;
            } else {
                return judge(a.getTuesdayOpeningHours(), datetime);
            }
        } else if (w == 4) {
            if (a.getWednesdayOpeningHours().equals("null")) {
                return false;
            } else {
                return judge(a.getWednesdayOpeningHours(), datetime);
            }
        } else if (w == 5) {
            if (a.getThursdayOpeningHours().equals("null")) {
                return false;
            } else {
                return judge(a.getThursdayOpeningHours(), datetime);
            }
        } else if (w == 6) {
            if (a.getFridayOpeningHours().equals("null")) {
                return false;
            } else {
                return judge(a.getFridayOpeningHours(), datetime);
            }
        } else if (w == 7) {
            if (a.getSaturdayOpeningHours().equals("null")) {
                return false;
            } else {
                return judge(a.getSaturdayOpeningHours(), datetime);
            }
        }
        return false;
    }

    // ?????????????????????????????????????????????time1?????????????????????time2????????????????????????
    public boolean judge(String time1, String time2) {
        int[] t1 = getTime(time1);// ?????????0????????????????????????1??????????????????
        char[] t2 = time2.toCharArray();
        int realtime = (t2[11] - '0') * 10 + (t2[12] - '0');// yyyy-MM-dd HH:mm????????????
                                                            // 12???13???????????????????????????????????????-1???
        if (t1[0] <= realtime && t1[1] > realtime) {
            return true;
        } else {
            return false;
        }
    }

    // ??????api?????????????????????????????????10am-9pm??????10-21???
    public static int[] getTime(String time) {
        int startTime = 0;
        int endTime = 0;
        int[] result = new int[2];
        char[] ch = time.toCharArray();
        // ?????????????????????15???16???17????????????
        if (ch.length == 15) {
            startTime = ch[0] - '0';
            endTime = ch[9] - '0';
            if (ch[4] == 'A') {
                if (ch[13] == 'P') {
                    endTime += 12;
                }
            }
            if (ch[4] == 'P') {
                if (ch[13] == 'P') {
                    startTime += 12;
                    endTime += 12;
                }
            }
            result[0] = startTime;
            result[1] = endTime;
        } // ????????????15?????????,??????????????????4???13???,???????????????0???9???
        if (ch.length == 17) {
            startTime = (ch[0] - '0') * 10 + (ch[1] - '0');
            endTime = (ch[10] - '0') * 10 + (ch[11] - '0');
            if (ch[5] == 'A') {
                if (ch[15] == 'P') {
                    endTime += 12;
                }
            }
            if (ch[5] == 'P') {
                if (ch[15] == 'P') {
                    startTime += 12;
                    endTime += 12;
                }
            }
            result[0] = startTime;
            result[1] = endTime;
        } // ????????????17?????????,??????????????????5???15???,???????????????0,1???10,11???
        if (ch.length == 16) {
            if (ch[1] == ':') {
                startTime = ch[0] - '0';
                endTime = (ch[9] - '0') * 10 + (ch[10] - '0');
                if (ch[4] == 'A') {
                    if (ch[14] == 'P') {
                        endTime += 12;
                    }
                }
                if (ch[4] == 'P') {
                    if (ch[14] == 'P') {
                        startTime += 12;
                        endTime += 12;
                    }
                }
                result[0] = startTime;
                result[1] = endTime;
            } // ?????????????????????????????????????????????????????????4???14???,???????????????0???9,10???
            else {
                startTime = (ch[0] - '0') * 10 + (ch[1] - '0');
                endTime = ch[10] - '0';
                if (ch[5] == 'A') {
                    if (ch[14] == 'P') {
                        endTime += 12;
                    }
                }
                if (ch[5] == 'P') {
                    if (ch[14] == 'P') {
                        startTime += 12;
                        endTime += 12;
                    }
                }
                result[0] = startTime;
                result[1] = endTime;
            } // ???????????????????????????????????????????????????5???14???,???????????????0,1???10???
        } // ????????????16?????????,???????????????
        return result;
    }
}
