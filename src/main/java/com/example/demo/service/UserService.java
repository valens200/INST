package com.example.demo.service;

import com.example.demo.models.AppUser;
import com.example.demo.models.Customer;
import com.example.demo.models.Product;
import com.example.demo.models.Role;
import com.example.demo.utils.OrderResponse;

import java.util.Collection;
import java.util.List;

public interface UserService {

    public AppUser registerUser(AppUser user);
    public Role registerRole(Role role);
    public  AppUser addRoleTOUser(String email, String roleName);
    public  AppUser findByEmail(String email);
    public Collection<AppUser> getAllUsers();
    public  Collection<Role> getAllRoles();

    public  Product saveProduct(Product product);
    public Customer saveCustomer(Customer cUstomer);
    public List<Customer> getAllCustomers();
    public Collection<OrderResponse> getJoin();

}
