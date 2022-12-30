package com.peaksoft.springsecuritywithjwt.dto;

import lombok.Builder;

@Builder
public record AuthResponse (String token){


}
