package any.domain.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
public class ProductController {

	private ProductRepository repository;
	
    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }
    
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/products/properties")
    private List<Product> findCommonProducts() {
    	return repository.findCommonProducs();
        
    }
    
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/products")
    private List<String> findCommonProductsIds() {
    	return repository.findCommonProducIds();
        
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/products/{productId}/prices", produces = "application/json")
    public String getProductPrices(@PathVariable String productId) {

    	ArrayList <String> arrayList = new ArrayList<String>();
    	
		Stream.of("BTX","BFX","BNB").forEach(exchange ->	
			{
				try {
					String url = "https://api.moneeda.com/api/exchanges/" + exchange + "/ticker?product=" + productId;
					JSONObject priceObject = new JSONObject(MoneedaFetcher.getURL(url));
					priceObject.put("exchange", exchange);
					arrayList.add(priceObject.toString());				
				} catch (Exception e) {
					// Generic!
					e.printStackTrace();
				}
			}
		);
    	return arrayList.toString();    	
    }	
	
}
