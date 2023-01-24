package vkir.treeservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NodeDto {

    @JsonProperty(value = "parent", required = true)
    private Long parentId;

    @JsonProperty(required = true)
    private String label;

}
