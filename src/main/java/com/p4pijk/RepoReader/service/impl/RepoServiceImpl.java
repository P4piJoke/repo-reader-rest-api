package com.p4pijk.RepoReader.service.impl;

import com.p4pijk.RepoReader.dto.BranchDTO;
import com.p4pijk.RepoReader.dto.RepoResponseDTO;
import com.p4pijk.RepoReader.exception.RepoDataException;
import com.p4pijk.RepoReader.model.Branch;
import com.p4pijk.RepoReader.model.Repository;
import com.p4pijk.RepoReader.service.RepoService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepoServiceImpl implements RepoService {

    private static final String REPO_URL = "https://api.github.com/users/";
    private static final String BRANCH_URL = "https://api.github.com/repos/";
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<RepoResponseDTO> getRepoData(String username) {
        Repository[] repositories = restTemplate.getForObject(REPO_URL + username + "/repos",
                Repository[].class);

        assert repositories != null;
        if (repositories.length == 0) {
            throw new RepoDataException("Repositories weren't found for user: " + username);
        }

        return Arrays.stream(repositories)
                .map(repo -> mapToRepoResponseDTO(username, repo))
                .collect(Collectors.toList());
    }

    private List<BranchDTO> extractBranches(String owner, String repoName) {
        Branch[] branches = restTemplate.getForObject(
                BRANCH_URL + owner + '/' + repoName + "/branches",
                Branch[].class);

        assert branches != null;

        return Arrays.stream(branches)
                .map(this::mapToBranchDTO)
                .collect(Collectors.toList());
    }

    private BranchDTO mapToBranchDTO(Branch branch) {
        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setName(branch.getName());
        branchDTO.setSha(branch.getCommit().getSha());
        return branchDTO;
    }

    private RepoResponseDTO mapToRepoResponseDTO(String username, Repository repo) {
        RepoResponseDTO repoDTO = new RepoResponseDTO();
        repoDTO.setOwner(username);
        repoDTO.setName(repo.getName());
        repoDTO.setBranches(extractBranches(username, repo.getName()));
        return repoDTO;
    }
}
