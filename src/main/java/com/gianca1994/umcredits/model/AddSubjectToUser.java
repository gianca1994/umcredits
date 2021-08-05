package com.gianca1994.umcredits.model;


public class AddSubjectToUser {
    private Long code;
    private byte note;

    public AddSubjectToUser() {
    }

    public AddSubjectToUser(Long code, byte note) {
        this.code = code;
        this.note = note;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public int getNote() {
        return note;
    }

    public void setNote(byte note) {
        this.note = note;
    }
}
