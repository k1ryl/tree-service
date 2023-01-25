package vkir.treeservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import vkir.treeservice.model.Tree;
import vkir.treeservice.repository.TreeRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TreeServiceTest {

    @Mock
    private TreeRepository repository;

    private TreeService service;

    @BeforeEach
    public void setUp() {
        service = new TreeService(repository);
    }

    @Test
    public void getTree_ShouldReturnTree() {
        // Given
        Tree tree = getRoot();
        when(repository.findFirstByLabel("root")).thenReturn(tree);

        // When
        Tree result = service.getTree();

        // Then
        assertEquals(tree, result);
    }

    @Test
    public void getTree_ShouldThrowException() {
        // Given
        when(repository.findFirstByLabel("root")).thenReturn(null);

        // Then
        assertThrows(RuntimeException.class, () -> service.getTree());
    }

    @Test
    public void addNode_ShouldReturnTree() {
        // Given
        Tree node = new Tree(null, getRoot(), null, "Node 1");
        Tree nodeWithId = new Tree(2L, getRoot(), null, "Node 1");
        when(repository.save(node)).thenReturn(nodeWithId);

        // When
        Tree result = service.addNode(node);

        // Then
        assertEquals(nodeWithId, result);
    }

    private Tree getRoot() {
        return new Tree(1L, null, null, "Tree 1");
    }

}