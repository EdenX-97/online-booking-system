package net.obs.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ReturnMessage {
    private Integer code;

    private String message;

    private Object data;

    public ReturnMessage() {

    }

    public ReturnMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ReturnMessage(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ObjectNode toJson() {
        ObjectNode returnMessage = new ObjectMapper().createObjectNode();
        returnMessage.put("code", code);
        returnMessage.put("message", message);
        if (data != null) {
            ObjectNode dataJson = new ObjectMapper().valueToTree(data);
            returnMessage.set("data", dataJson);
        }
        return returnMessage;
    }
}
