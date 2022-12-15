package com.example.addressbook.service;

import com.example.addressbook.dto.AddressBookDTO;
import com.example.addressbook.exception.AddressBookException;
import com.example.addressbook.model.AddressBookModel;
import com.example.addressbook.repositery.AddressBookRepositery;
import com.example.addressbook.utility.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressBookService  implements IAddressBookService {

    @Autowired
    AddressBookRepositery addressBookRepositery;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    EmailService emailService;

    //--------------------------------- Add New Contact ---------------------------------
    @Override
    public String addAddressBook(AddressBookDTO addressBookDTO) {
        if (addressBookRepositery.findByEmail(addressBookDTO.getEmail()) == null) {
            AddressBookModel addressBookModel = modelMapper.map(addressBookDTO, AddressBookModel.class);
            addressBookRepositery.save(addressBookModel);
            emailService.sendMail(addressBookDTO.getEmail(), "Your Data Successfully Saved In Address_Book");
            return "AddressBook Added";
        }
        throw new AddressBookException("This email-Id is already exist"+" \nPlease Try with another emai-Id");
    }

    //--------------------------------- Get All By Contact ---------------------------------
    @Override
    public List<AddressBookModel> showAddressBookData() {
        return addressBookRepositery.findAll();
    }

    //--------------------------------- Get Contact By Id ---------------------------------

    @Override
    public AddressBookModel getAddressBookById(int id) {
        if (addressBookRepositery.findById(id).isPresent()) {
            return addressBookRepositery.findById(id).get();
        }
        throw new AddressBookException("AddressBook Not Found"+"\nInvalid Id");
    }

    //--------------------------------- Update Contact ---------------------------------
    @Override
    public AddressBookModel editAddressBook(int id, AddressBookDTO addressBookDTO) {
        if (addressBookRepositery.findById(id).isPresent()) {
            AddressBookModel model = addressBookRepositery.findById(id).get();
            AddressBookModel edit = modelMapper.map(addressBookDTO, AddressBookModel.class);
            edit.setId(id);
            if (addressBookRepositery.findByEmail(addressBookDTO.getEmail()) == null) {
                if (edit.getFullName() == null) {
                    edit.setFullName(model.getFullName());
                }
                if (edit.getAddress() == null) {
                    edit.setAddress(model.getAddress());
                }
                if (edit.getEmail() == null) {
                    edit.setEmail(model.getEmail());
                }
                if (edit.getCity() == null) {
                    edit.setCity(model.getCity());
                }
                if (edit.getState() == null) {
                    edit.setState(model.getState());
                }
                if (edit.getZipCode() == 0) {
                    edit.setZipCode(model.getZipCode());
                }
                if (edit.getPhoneNo() == 0) {
                    edit.setPhoneNo(model.getPhoneNo());
                }
                return addressBookRepositery.save(edit);
            }
            throw new AddressBookException("This email_id is already exist"+"\nplease try with another email id");
        }
        throw new AddressBookException("Address Not Found" + "\nInvalid Id");
    }

    //--------------------------------- Delete Contact ---------------------------------
    @Override
    public String deleteAddressBook(int id) {
        if (addressBookRepositery.findById(id).isPresent()) {
            addressBookRepositery.deleteById(id);
            return "AddressBook Deleted Successful";
        }
        throw new AddressBookException("AddressBook Not Found " + "\n Invalid Id");
    }

    //--------------------------------- Get Contact By State ---------------------------------
    @Override
    public List<AddressBookModel> searchAddressBookByState(String state) {
        List<AddressBookModel> addressBookModelList = addressBookRepositery.findByState(state);
        if (addressBookModelList.isEmpty()) {
            throw new AddressBookException("Address Book with State " + state + " not found!");
        }
        return addressBookModelList;
    }

    //--------------------------------- Get Contact By City ---------------------------------
    @Override
    public List<AddressBookModel> searchAddressBookByCity(String city) {
        List<AddressBookModel> addressBookModelList = addressBookRepositery.findByCity(city);
        if (addressBookModelList.isEmpty()) {
            throw new AddressBookException("Address Book with City " + city + " not found!");
        }
        return addressBookModelList;
    }
}
