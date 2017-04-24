package order;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;

import product.Buyer;


/**
 * Order entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="`order`")

public class Order  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -6214627047833424593L;
	private Integer id;
     private Buyer buyer = new Buyer();
     private Integer status;
     private Date date;

    // Constructors

    /** default constructor */
    public Order() {
    }

    
    /** full constructor */
    public Order(Buyer buyer, Integer status) {
        this.buyer = buyer;
        this.status = status;
    }

   
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="id", unique=true, nullable=false)

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "buyer", nullable = false)

    public Buyer getBuyer() {
        return this.buyer;
    }
    
    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }
    
    @Column(name="status", nullable=false)

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
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
		return "Order [id=" + id + ", buyer=" + buyer + ", status=" + status
				+ ", date=" + date + "]";
	}

}