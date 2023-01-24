package vkir.treeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vkir.treeservice.model.Tree;

@Repository
public interface TreeRepository extends JpaRepository<Tree, Long> {

    Tree findFirstByLabel(String label);
}
