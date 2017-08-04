package com.bycc.domain;

import com.bycc.enumItem.Gender;
import com.bycc.enumItem.Nation;

/**
 * ���֤-����ģ��
 * 
 */
public class IDCard {
	// ���֤��
	private String id;

	// ����
	private String name;

	// ��������
	private String birthday;

	// �Ա�
	private Gender gender;

	// ����
	private Nation nation;

	// סַ
	private String address;

	// ǩ������
	private String issuer;

	// ��Ч�ڿ�ʼ
	private String issueDate;

	// ��Ч�ڽ���
	private String expireDate;

	// base64������Ƭ
	private String photo;
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Nation getNation() {
		return nation;
	}

	public void setNation(Nation nation) {
		this.nation = nation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	@Override
	public String toString() {
		return "IDCard [id=" + id + ", name=" + name + ", birthday=" + birthday + ", gender=" + gender + ", nation=" + nation
				+ ", address=" + address + ", issuer=" + issuer + ", issueDate=" + issueDate + ", expireDate=" + expireDate + "]";
	}
}
