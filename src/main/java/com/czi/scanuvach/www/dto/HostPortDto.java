package com.czi.scanuvach.www.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class HostPortDto {

    private String ip;
    private String status;
    private String openPorts;
    private String closedPorts;
    private LocalDateTime updated;
    private LocalDateTime created;
}
