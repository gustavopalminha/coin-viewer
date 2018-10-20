package any.domain.backend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProductCommandLineRunner implements CommandLineRunner{

	private final ProductRepository repository;
	
	public ProductCommandLineRunner(ProductRepository repository) {
		this.repository = repository;
	}
	 
	@Override
	public void run(String... args) throws Exception {
		
		
		System.out.println("Loading....");
				
		Stream.of("BTX","BFX","BNB").forEach(exchange ->	
			{
				try {
					this.loadExchange(exchange);
				} catch (Exception e) {
					// Generic!
					e.printStackTrace();
				}
			}
		);
		System.out.println("\nFinished loading!");
		
		
	}
	
    /**
     * Load Exchange to repository...
     */
    public void loadExchange (String exchange) throws Exception {
		System.out.println("\n" + exchange);
		
    	String exchangeUrl = "https://api.moneeda.com/api/exchanges/" + exchange + "/products";

    	String productsResponse = MoneedaFetcher.getURL(exchangeUrl);
    	
    	JSONArray productsExchange = new JSONArray(productsResponse);
    	
    	for (int i = 0; i < productsExchange.length(); i++)
        {
            JSONObject jsonObj = productsExchange.getJSONObject(i);
            jsonObj.put("exchange", exchange);
                        
            Product product = new Product(
            		jsonObj.getString("id"),
            		jsonObj.getString("originalId"),
            		jsonObj.getString("base_currency"),
            		jsonObj.getString("quote_currency"),
            		jsonObj.getString("exchange")
            );            
            repository.save(product);
        }    			
		
	}
}
