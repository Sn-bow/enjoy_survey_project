package com.enjoy.survey.happyLife.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@ToString
public class OrderSwitch {
    String order;
    String filter;
    String orderBy;

    public HashMap<String, String> switching(String order, String title) {

        if (title.equals("1대1문의")) {
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
        } else if (title.equals("QnA")) {
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
        } else if (title.equals("설문")) {
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
        } else if (title.equals("게시판")) {
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
        } else if (title.equals("댓글")) {
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
        }


        HashMap<String, String> orderData = new HashMap<>();
        orderData.put("filter", filter);
        orderData.put("orderBy", orderBy);

        return orderData;
    }
}
