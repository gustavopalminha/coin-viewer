package any.domain.backend;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface ProductRepository extends JpaRepository <Product, Long>{
	
    @Query(value = "SELECT * FROM PRODUCT p WHERE p.ID IN(SELECT p2.ID FROM PRODUCT p2 GROUP BY p2.ID HAVING COUNT(*) = 3)", nativeQuery = true)
    public List<Product> findCommonProducs();

    @Query(value = "SELECT p2.ID FROM PRODUCT p2 GROUP BY p2.ID HAVING COUNT(*) = 3", nativeQuery = true)    
    public List<String> findCommonProducIds();
    
}
