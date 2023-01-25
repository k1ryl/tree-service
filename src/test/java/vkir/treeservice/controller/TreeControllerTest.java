package vkir.treeservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import vkir.treeservice.dto.NodeDto;
import vkir.treeservice.dto.TreeDto;
import vkir.treeservice.mapper.TreeMapperImpl;
import vkir.treeservice.model.Tree;
import vkir.treeservice.service.TreeService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TreeController.class)
@Import(TreeMapperImpl.class)
public class TreeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TreeService service;

    @Test
    public void getTree_ShouldReturnCorrectTreeDto() throws Exception {
        // Given
        TreeDto treeDto = new TreeDto(1L, "Tree 1", null, null);
        Tree tree = getRoot();
        when(service.getTree()).thenReturn(tree);

        // When
        MvcResult result = mockMvc.perform(get("/tree"))
                .andExpect(status().isOk())
                .andReturn();

        // Then
        assertEquals(objectMapper.writeValueAsString(treeDto), result.getResponse().getContentAsString());
    }

    @Test
    public void getTree_ShouldReturnErrorMessage() throws Exception {
        // Given
        when(service.getTree()).thenThrow(new RuntimeException("DB exception."));

        // When
        MvcResult result = mockMvc.perform(get("/tree"))
                .andExpect(status().isInternalServerError())
                .andReturn();

        // Then
        assertEquals("Error retrieving tree. DB exception.", result.getResponse().getContentAsString());
    }

    @Test
    public void addNode_ShouldReturnTreeDto() throws Exception {
        // Given
        NodeDto nodeDto = new NodeDto(1L, "Node 1");
        Tree node = new Tree(2L, getRoot(), null, "Node 1");
        TreeDto expected = new TreeDto(2L, "Node 1", 1L, null);
        when(service.addNode(any())).thenReturn(node);

        // When
        MvcResult result = mockMvc.perform(post("/tree")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nodeDto)))
                .andExpect(status().isOk())
                .andReturn();

        // Then
        assertEquals(objectMapper.writeValueAsString(expected), result.getResponse().getContentAsString());
    }

    @Test
    public void addNode_ShouldReturnErrorMessage() throws Exception {
        // Given
        NodeDto nodeDto = new NodeDto(1L, "Node 1");
        when(service.addNode(any())).thenThrow(new RuntimeException("DB exception."));

        // When
        MvcResult result = mockMvc.perform(post("/tree")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nodeDto)))
                .andExpect(status().isInternalServerError())
                .andReturn();

        // Then
        assertEquals("Error adding node. DB exception.", result.getResponse().getContentAsString());
    }

    private Tree getRoot() {
        return new Tree(1L, null, null, "Tree 1");
    }
}