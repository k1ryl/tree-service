package vkir.treeservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class TreeDto {

    private Long id;
    private String label;
    private Long parentId;
    private List<TreeDto> children;

}
