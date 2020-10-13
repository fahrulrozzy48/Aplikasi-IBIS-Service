package com.example.ibisfood.Common;

import com.example.ibisfood.Model.PostMessageModel;

public class Common {
    public static PostMessageModel currentUser;

    public static final String UPDATE = "Update";
    public static final String DELETE = "Delete";

    public static final int PICK_MESSAGE_REQUEST = 71;

    public static String convertCodeToStatus(String code){
        if(code.equals("0")){
            return "Cek";
        } else if (code.equals("1")){
            return "Dalam Perjalanan";
        } else {
            return "Dikerjakan";
        }
    }
}
