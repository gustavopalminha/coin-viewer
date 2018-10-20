package any.domain.backend;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
	/* FIELDS */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long oid;
	private String id;
	private String originalId;
	private String baseCurrency;
	private String quoteCurrency;	
	private String exchange;
	
	/* CONSTRUCTOR */	
	
	public Product() {}
	
	public Product(
			String id, 
			String originalId, 
			String baseCurrency, 
			String quoteCurrency, 
			String exchange) {
		this.id = id;
		this.originalId = originalId;
		this.baseCurrency = baseCurrency;
		this.quoteCurrency = quoteCurrency;
		this.exchange = exchange;
	}

	/* GETTERS & SETTERS */	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOriginalId() {
		return originalId;
	}
	public void setOriginalId(String originalId) {
		this.originalId = originalId;
	}
	public String getBaseCurrency() {
		return baseCurrency;
	}
	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}
	public String getQuoteCurrency() {
		return quoteCurrency;
	}
	public String getExchange() {
		return exchange;
	}
	public void setQuoteCurrency(String quoteCurrency) {
		this.quoteCurrency = quoteCurrency;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}	

}
