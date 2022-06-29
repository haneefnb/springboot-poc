package com.haneefs.springbootpoc.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorInfo {
    String erroCode;
    String errorMessage;
}
