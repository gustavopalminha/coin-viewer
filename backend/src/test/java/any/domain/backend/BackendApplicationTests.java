package any.domain.backend;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BackendApplicationTests {

    @Autowired
    private ProductRepository productRepository;
    
	@Test
	public void contextLoads(){
		
    	List<String> commonIds = productRepository.findCommonProducIds();    	
    	assertTrue("No common productids where found... ",commonIds.size() > 0);
	}

}
