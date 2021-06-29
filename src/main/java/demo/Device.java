package demo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
class Device {
	
	private @Id Long UID;
	private String vendor;
	private Date created;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="gateway_serial")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Gateway gateway;
    
    public Device() {};
    
	public Device(Long uID, String vendor, Date created, Gateway gateway) {
		super();
		UID = uID;
		this.vendor = vendor;
		this.created = created;
		this.gateway = gateway;
	}

	public Long getUID() {
		return UID;
	}

	public void setUID(Long uID) {
		UID = uID;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Gateway getGateway() {
		return gateway;
	}

	public void setGateway(Gateway gateway) {
		this.gateway = gateway;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((UID == null) ? 0 : UID.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((gateway == null) ? 0 : gateway.hashCode());
		result = prime * result + ((vendor == null) ? 0 : vendor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Device other = (Device) obj;
		if (UID == null) {
			if (other.UID != null)
				return false;
		} else if (!UID.equals(other.UID))
			return false;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (gateway == null) {
			if (other.gateway != null)
				return false;
		} else if (!gateway.equals(other.gateway))
			return false;
		if (vendor == null) {
			if (other.vendor != null)
				return false;
		} else if (!vendor.equals(other.vendor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Device [UID=" + UID + ", vendor=" + vendor + ", created=" + created + "]";
	}

}
