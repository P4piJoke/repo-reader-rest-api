package com.p4pijk.RepoReader.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Repository {
    private String name;
    private Owner owner;
    private List<Branch> branches;
    private boolean fork;
    private int forks;
}
