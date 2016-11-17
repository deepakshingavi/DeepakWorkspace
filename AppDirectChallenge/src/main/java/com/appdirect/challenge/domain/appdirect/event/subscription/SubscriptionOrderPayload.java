package com.appdirect.challenge.domain.appdirect.event.subscription;

import javax.xml.bind.annotation.XmlRootElement;

import com.appdirect.challenge.domain.appdirect.Company;
import com.appdirect.challenge.domain.appdirect.Order;

@XmlRootElement(name = "payload")
public class SubscriptionOrderPayload {
    private Company company;
    private Order order;
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
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
		SubscriptionOrderPayload other = (SubscriptionOrderPayload) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		return true;
	}
    
    
}
