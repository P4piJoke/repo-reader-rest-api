package com.p4pijk.RepoReader.dto;

import lombok.Data;

import java.util.List;

@Data
public class RepoResponseDTO {

    private String owner;
    private String name;
    private List<BranchDTO> branches;
}
