package appointment.person;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import appointment.time.Time;

/**
 * Person entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "person")
public class Person implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	private Integer id;
	private Time time;
	private String name;
	private Boolean gender;
	private String age;
	private String phone;
	private Integer paid = 0;
	private Date date;

	// Constructors

	/** default constructor */
	public Person() {
	}

	/** full constructor */
	public Person(Time time, String name, Boolean gender, String age,
			String phone, Integer paid, Date date) {
		this.time = time;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.phone = phone;
		this.paid = paid;
		this.date = date;
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

	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "time", nullable = false)
//	@JSON(serialize=false)
	public Time getTime() {
		return this.time;
	}	

	public void setTime(Time time) {
		this.time = time;
	}

	@Column(name = "name", nullable = false, length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "gender", nullable = false, precision = 12, scale = 0)
	public Boolean getGender() {
		return this.gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	@Column(name = "age", nullable = false, length = 20)
	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Column(name = "phone", nullable = false)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "paid", nullable = false)
	public Integer getPaid() {
		return this.paid;
	}

	public void setPaid(Integer paid) {
		this.paid = paid;
	}

	@Column(name = "date", nullable = false, length = 19, updatable=false)
	public Date getDate() {
		if (null == date) {
//    		return new Timestamp(System.currentTimeMillis());
    		return new Date();
    	}
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return id == ((Person) obj).getId();
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", time=" + time.getId() + ", name=" + name
				+ ", gender=" + gender + ", age=" + age + ", phone=" + phone
				+ ", paid=" + paid + ", date=" + date + "]";
	}
	
	
}