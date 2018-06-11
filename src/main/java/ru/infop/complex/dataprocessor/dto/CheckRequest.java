package ru.infop.complex.dataprocessor.dto;

/**
 * Created by lconnected on 11/06/2018.
 */
public class CheckRequest {
    private String vin;
    private String captchaWord;
    private CheckType checkType;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getCaptchaWord() {
        return captchaWord;
    }

    public void setCaptchaWord(String captchaWord) {
        this.captchaWord = captchaWord;
    }

    public CheckType getCheckType() {
        return checkType;
    }

    public void setCheckType(CheckType checkType) {
        this.checkType = checkType;
    }
}
