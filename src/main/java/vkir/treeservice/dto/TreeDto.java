package vkir.treeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeDto {

    private Long id;
    private String label;
    private Long parentId;
    private List<TreeDto> children;

}
