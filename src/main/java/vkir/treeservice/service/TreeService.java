package vkir.treeservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vkir.treeservice.model.Tree;
import vkir.treeservice.repository.TreeRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TreeService {

    private final TreeRepository repository;

    public Tree getTree() {
        return Optional.ofNullable(repository.findFirstByLabel("root"))
                .orElseThrow(() -> new RuntimeException("Root node not found."));
    }

    public Tree addNode(Tree node) {
        return repository.save(node);
    }
}
