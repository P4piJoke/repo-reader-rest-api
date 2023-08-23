package com.p4pijk.RepoReader.service;

import com.p4pijk.RepoReader.dto.RepoResponseDTO;
import com.p4pijk.RepoReader.exception.RepoDataException;

import java.util.List;

public interface RepoService {

    List<RepoResponseDTO> getRepoData(String username) throws RepoDataException;
}
