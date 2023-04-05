package com.helmes.citymanagement.vo;

import org.springframework.http.HttpStatus;

public record ErrorEntry(HttpStatus status, String message) {

}
