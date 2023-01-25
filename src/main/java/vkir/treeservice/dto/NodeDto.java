package vkir.treeservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NodeDto {

    @JsonProperty(value = "parent", required = true)
    private Long parentId;

    @JsonProperty(required = true)
    private String label;

}
