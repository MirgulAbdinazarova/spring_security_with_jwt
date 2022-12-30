package com.peaksoft.springsecuritywithjwt.dto;

import lombok.Builder;

@Builder
public record AuthRequest(String email,String password) {
}
