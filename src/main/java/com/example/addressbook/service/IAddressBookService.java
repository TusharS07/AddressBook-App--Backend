package com.example.addressbook.service;

import com.example.addressbook.dto.AddressBookDTO;
import com.example.addressbook.model.AddressBookModel;

import java.util.List;

public interface IAddressBookService {
    String addAddressBook(AddressBookDTO addressBookDTO);
    List<AddressBookModel> showAddressBookData();
    AddressBookModel getAddressBookById(int id);
    AddressBookModel editAddressBook(int id, AddressBookDTO addressBookDTO);
    String deleteAddressBook(int id);
    List<AddressBookModel> searchAddressBookByState(String state);
    List<AddressBookModel> searchAddressBookByCity(String city);

}
