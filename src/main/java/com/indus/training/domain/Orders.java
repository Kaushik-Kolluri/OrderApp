package com.indus.training.domain;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class Orders implements Externalizable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	private int price;
	private String itemName;
	private String itemCategory;
	
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Orders(int orderId, int price, String itemName, String itemCategory) {
		super();
		this.orderId = orderId;
		this.price = price;
		this.itemName = itemName;
		this.itemCategory = itemCategory;
	}


	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public String getItemCategory() {
		return itemCategory;
	}


	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}


	@Override
	public int hashCode() {
		return Objects.hash(itemCategory, itemName, orderId, price);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orders other = (Orders) obj;
		return Objects.equals(itemCategory, other.itemCategory) && Objects.equals(itemName, other.itemName)
				&& orderId == other.orderId && price == other.price;
	}


	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", price=" + price + ", itemName=" + itemName + ", itemCategory="
				+ itemCategory + "]";
	}


	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(this.orderId);
		out.writeUTF(this.itemCategory);
		out.writeUTF(this.itemName);
		out.writeInt(this.price);
		
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		
		orderId = in.readInt();
		price = in.readInt();
		itemCategory=in.readUTF();
		itemName=in.readUTF();
		
	}
	
	

}
