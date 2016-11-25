package product;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;

/**
 * Buyer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "buyer")
public class Buyer implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3910700839771476348L;
	private Integer id;
	private String name;
	private String phone;
	private String address;
	private Integer price=0;
	private Integer freight=0;
	private Integer tprice=0;
	private Date date=new Date();
	private Integer paid=0;
	private Integer sent=0;
	private String remark="";
	private Set<Buy> buies = new HashSet<Buy>(0);

	// Constructors

	/** default constructor */
	public Buyer() {
	}

	/** minimal constructor */
	public Buyer(String name, String phone, String address, Integer price,
			Integer freight, Integer tprice, Date date, Integer paid,
			Integer sent, String remark) {
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.price = price;
		this.freight = freight;
		this.tprice = tprice;
		this.date = date;
		this.paid = paid;
		this.sent = sent;
		this.remark = remark;
	}

	/** full constructor */
	public Buyer(String name, String phone, String address, Integer price,
			Integer freight, Integer tprice, Date date, Integer paid,
			Integer sent, String remark, Set<Buy> buies) {
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.price = price;
		this.freight = freight;
		this.tprice = tprice;
		this.date = date;
		this.paid = paid;
		this.sent = sent;
		this.remark = remark;
		this.buies = buies;
	}

	public Buyer(int price, Set<Buy> buies) {
		this.price = price;
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

	@Column(name = "name", nullable = false, length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "phone", nullable = false, length = 20)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "address", nullable = false, length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "price", nullable = false)
	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Column(name = "freight", nullable = false)
	public Integer getFreight() {
		return this.freight;
	}

	public void setFreight(Integer freight) {
		this.freight = freight;
	}

	@Column(name = "tprice", nullable = false)
	public Integer getTprice() {
		return this.tprice;
	}

	public void setTprice(Integer tprice) {
		this.tprice = tprice;
	}

	@Column(name = "date", nullable = false, length = 19, updatable = false)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "paid", nullable = false)
	public Integer getPaid() {
		return this.paid;
	}

	public void setPaid(Integer paid) {
		this.paid = paid;
	}

	@Column(name = "sent", nullable = false)
	public Integer getSent() {
		return this.sent;
	}

	public void setSent(Integer sent) {
		this.sent = sent;
	}

	@Column(name = "remark", nullable = false, length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	//@JSON(serialize=false)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "buyer")
	public Set<Buy> getBuies() {
		return this.buies;
	}

	public void setBuies(Set<Buy> buies) {
		this.buies = buies;
	}

	@Override
	public String toString() {
		return "Buyer [id=" + id + ", name=" + name + ", phone=" + phone
				+ ", address=" + address + ", price=" + price + ", freight="
				+ freight + ", tprice=" + tprice + ", date=" + date + ", paid="
				+ paid + ", sent=" + sent + ", remark=" + remark + "]";
	}

}