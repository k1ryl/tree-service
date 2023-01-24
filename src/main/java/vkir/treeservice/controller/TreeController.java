package vkir.treeservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vkir.treeservice.dto.NodeDto;
import vkir.treeservice.dto.TreeDto;
import vkir.treeservice.mapper.TreeMapper;
import vkir.treeservice.service.TreeService;

@RestController
@RequestMapping("/tree")
@RequiredArgsConstructor
public class TreeController {

    private final TreeService service;
    private final TreeMapper mapper;

    @GetMapping
    public ResponseEntity<TreeDto> getTree() {
        try {
            TreeDto treeDto = mapper.map(service.getTree());
            return ResponseEntity.ok(treeDto);
        } catch (Exception e) {
            return new ResponseEntity("Error retrieving tree. " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<TreeDto> addNode(@RequestBody NodeDto node) {
        try {
            TreeDto nodeDto = mapper.map(
                    service.addNode(mapper.map(node)));
            return ResponseEntity.ok(nodeDto);
        } catch (Exception e) {
            return new ResponseEntity("Error adding node. " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
