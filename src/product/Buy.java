package product;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Buy entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "buy", uniqueConstraints = @UniqueConstraint(columnNames = {
		"buyer", "product" }))
public class Buy implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2976841311309773388L;
	private Integer id;
	private Product product=new Product();
	private Buyer buyer = new Buyer();
	private Integer number;

	// Constructors

	/** default constructor */
	public Buy() {
	}

	/** full constructor */
	public Buy(Product product, Buyer buyer, Integer number) {
		this.product = product;
		this.buyer = buyer;
		this.number = number;
	}
	
	public Buy(Product product, Integer number) {
		this.product = product;
		this.number = number;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product", nullable = false)
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "buyer", nullable = false)
	public Buyer getBuyer() {
		return this.buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	@Column(name = "number", nullable = false)
	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Buy [id=" + id + ", product=" + product + ", buyer=" + buyer.getId()
				+ ", number=" + number + "]";
	}

}