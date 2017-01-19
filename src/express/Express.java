package express;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;

/**
 * Express entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "express")
public class Express implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7197629060669041280L;
	private Integer id;
	private String name;
	private String phone;
	private String number;
	private String company;
	private Date date;

	// Constructors

	/** default constructor */
	public Express() {
	}

	/** minimal constructor */
	public Express(String name, String phone, String number) {
		this.name = name;
		this.phone = phone;
		this.number = number;
	}

	/** full constructor */
	public Express(String name, String phone, String number, String company) {
		this.name = name;
		this.phone = phone;
		this.number = number;
		this.company = company;
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

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "phone", nullable = false, length = 30)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "number", nullable = false, length = 50)
	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Column(name = "company", length = 20)
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "date", nullable = false)
	public Date getDate() {
		return this.date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Express [id=" + id + ", name=" + name + ", phone=" + phone
				+ ", number=" + number + ", company=" + company + ", date="
				+ date + "]";
	}


}