package vn.oceantech.mita.dto;

public class AndroidResponseDto <T> {
    T data;
    String message;
    Integer code;
    public AndroidResponseDto() {
    }
    public AndroidResponseDto(T data, String message, Integer code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }
    public AndroidResponseDto(String message, Integer code) {
        this.message = message;
        this.code = code;
    }
    public AndroidResponseDto(Integer code) {
        this.code = code;
    }

    public AndroidResponseDto(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
