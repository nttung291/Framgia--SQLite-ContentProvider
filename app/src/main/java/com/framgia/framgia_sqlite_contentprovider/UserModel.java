package com.framgia.framgia_sqlite_contentprovider;

/**
 * Created by nttungg on 2/6/18.
 */

public class UserModel {
    private String name;
    private String phonenumber;

    public UserModel(String name, String phonenumber) {
        this.name = name;
        this.phonenumber = phonenumber;
    }

    public String getName() {
        return name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }
}
