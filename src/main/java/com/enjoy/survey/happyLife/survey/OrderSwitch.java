package com.enjoy.survey.happyLife.survey;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class OrderSwitch {
    String order;
    String filter;
    String orderBy;

    public List<String> switching(String order) {
        switch (order) {
            case "많이 참여한 순서" -> {
                filter = "hit";
                orderBy = "desc";
                break;
            }
            case "적게 참여한 순서" -> {
                filter = "hit";
                orderBy = "asc";
                break;
            }
            case "최신 순서" -> {
                filter = "id";
                orderBy = "desc";
                break;
            }
            case "오래된 순서" -> {
                filter = "id";
                orderBy = "asc";
                break;
            }
        }
        List<String> orderData = new ArrayList<>();
        orderData.add(filter);
        orderData.add(orderBy);
        return orderData;
    }
}
