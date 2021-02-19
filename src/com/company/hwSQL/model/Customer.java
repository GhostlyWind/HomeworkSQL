package com.company.hwSQL.model;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.Objects;

public class Customer {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private long storeId;
    private long addressId;

    private Customer() {
    }

    public static class Builder {
        private Customer newCustomer;

        public Builder() {
            newCustomer = new Customer();
        }

        public Builder id(long id) {
            if (id > 0) {
                newCustomer.id = id;
                return this;
            } else throw new IllegalArgumentException("Id can't be negative.");
        }

        public Builder firstName(String firstName) {
            newCustomer.firstName = firstName.toUpperCase().trim();
            return this;
        }

        public Builder lastName(String lastName) {
            newCustomer.lastName = lastName.toUpperCase().trim();
            return this;
        }

        public Builder email(String email) {
            if (EmailValidator.getInstance().isValid(email)) {
                newCustomer.email = email;
                return this;
            } else throw new IllegalArgumentException("Email is not valid.");
        }

        public Builder storeId(long storeId) {
            if (storeId == 1 || storeId == 2) {
                newCustomer.storeId = storeId;
                return this;
            } else throw new IllegalArgumentException("StoreId can be only 1 or 2.");
        }

        public Builder addressId(long addressId) {
            if (addressId > 0) {
                newCustomer.addressId = addressId;
                return this;
            } else throw new IllegalArgumentException("AddressId can't be negative.");
        }

        public Customer build() {
            return newCustomer;
        }
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.toUpperCase().trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.toUpperCase().trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (EmailValidator.getInstance().isValid(email)) {
            this.email = email;
        }
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        if (storeId == 1 || storeId == 2) {
            this.storeId = storeId;
        }
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        if (addressId > 0) {
            this.addressId = addressId;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return addressId == customer.addressId && firstName.equals(customer.firstName) && lastName.equals(customer.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, addressId);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", storeId=" + storeId +
                ", addressId=" + addressId +
                '}';
    }

}
