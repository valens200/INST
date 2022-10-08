package com.example.demo.service;

import com.example.demo.models.AppUser;
import com.example.demo.models.Customer;
import com.example.demo.models.Product;
import com.example.demo.models.Role;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.OrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{

            AppUser user = userRepository.findByEmail(username);
            log.info("user {}", user);
            Collection<Role> roles = user.getRoles();
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            roles.stream().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            });
            return new User(user.getEmail(), user.getPassword(), authorities);
        }catch(Exception exception){
            log.error("error : {}", exception.getMessage());
            return null;
        }
    }

    @Override
    public AppUser registerUser(AppUser user) {
        return userRepository.save(user);
    }

    @Override
    public Role registerRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public AppUser addRoleTOUser(String email, String roleName) {
        return null;
    }

    @Override
    public AppUser findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Collection<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Collection<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Product saveProduct(Product product) {
        return (Product) productRepository.save(product);
    }

    @Override
    public Customer saveCustomer(Customer cUstomer) {
        return (Customer) customerRepository.save(cUstomer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Collection<OrderResponse> getJoin(){
        return  customerRepository.getJOin();
    }
}
