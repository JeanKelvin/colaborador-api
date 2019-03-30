package com.colaborador.model.dto;

public class ErroDTO {

    private String httpMethod;

    private String message;

    private String url;

    private String field;

    private String reason;

    public ErroDTO(String message, String httpMethod, String url) {
        this.message = message;
        this.httpMethod = httpMethod;
        this.url = url;
    }

    public ErroDTO(String message, String field, String url, String httpMethod, String reason) {
        this.message = message;
        this.field = field;
        this.url = url;
        this.httpMethod = httpMethod;
        this.reason = reason;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
