package com.p4pijk.RepoReader.model;

import lombok.Data;

@Data
public class Branch {

    private String name;
    private Commit commit;
}
