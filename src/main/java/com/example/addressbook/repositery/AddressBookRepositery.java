package com.example.addressbook.repositery;

import com.example.addressbook.model.AddressBookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressBookRepositery extends JpaRepository<AddressBookModel, Integer> {
    AddressBookModel findByEmail(String email);
    //AddressBookModel findByCity(String city);
    List<AddressBookModel> findByState(String state);
    List<AddressBookModel> findByCity(String city);
}
