package com.p4pijk.RepoReader.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDTO {

    private String status;
    private String message;

    @Override
    public String toString() {
        return "{\n" +
                "\t\"status\":\"" + status  + "\","+
                "\n\t\"message\":\"" + message + "\"" +
                "\n}";
    }
}
