package vkir.treeservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vkir.treeservice.dto.NodeDto;
import vkir.treeservice.dto.TreeDto;
import vkir.treeservice.model.Tree;

@Mapper
public interface TreeMapper {

    @Mapping(target = "parent.id", source = "parentId")
    Tree map(NodeDto node);

    @Mapping(target = "parentId", source = "parent.id")
    TreeDto map(Tree tree);

}
