package com.example.devblogandroidapp.api.response;

import com.example.devblogandroidapp.api.ErrorDetails;
import com.example.devblogandroidapp.api.Meta;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse <T> {
    private ErrorDetails error;
    private String message;
    private T data;
    private Meta meta;
}