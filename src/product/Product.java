package product;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Product entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "product")
public class Product implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 641000759806200847L;
	private Integer id;
	private String name;
	private String alias;
	private Integer price;
	private Integer valid;
	private String img;
	private String link;
	private String description;
	private String label;
	private Set<Buy> buies = new HashSet<Buy>(0);

	// Constructors

	/** default constructor */
	public Product() {
	}

	/** minimal constructor */
	public Product(String name, String alias, Integer price, Integer valid) {
		this.name = name;
		this.alias = alias;
		this.price = price;
		this.valid = valid;
	}

	/** full constructor */
	public Product(String name, String alias, Integer price, Integer valid, Set<Buy> buies) {
		this.name = name;
		this.alias = alias;
		this.price = price;
		this.valid = valid;
		this.buies = buies;
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

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "alias", nullable = false, length = 20)
	public String getAlias() {
		return this.alias;
	}
	
	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Column(name = "price", nullable = false)
	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Column(name = "valid", nullable = false)
	public Integer getValid() {
		return this.valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}
	
	@Column(name = "img")
	public String getImg() {
		/*if (null == img || "".equals(img)) {
			img = "#";
		}*/
		return img;
	}
	
	public void setImg(String img) {
		this.img = img;
	}
	
	@Column(name = "link")
	public String getLink() {
		/*if (null == link || "".equals(link)) {
			link = "#";
		}*/
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "label")
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "product")
	public Set<Buy> getBuies() {
		return this.buies;
	}

	public void setBuies(Set<Buy> buies) {
		this.buies = buies;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", alias=" + alias + ", price=" + price + "]";
				//+ ", valid=" + valid + ", link=" + link + "]";
	}

}