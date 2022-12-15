package com.example.addressbook.controller;

import com.example.addressbook.Response;
import com.example.addressbook.dto.AddressBookDTO;
import com.example.addressbook.model.AddressBookModel;
import com.example.addressbook.repositery.AddressBookRepositery;
import com.example.addressbook.service.IAddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/AddressBook")
public class AddressBookController {
    @Autowired
    AddressBookRepositery addressBookRepositery;

    @Autowired
    IAddressBookService iAddressBookService;

    //--------------------------------- Add New Contact ---------------------------------

    @PostMapping("/Add_UserData")
    public ResponseEntity<Response> addUserDataInAddressBook(@RequestBody AddressBookDTO addressBookDTO) {
        iAddressBookService.addAddressBook(addressBookDTO);
        Response response = new Response(addressBookDTO, "User Data Successfully Added In AddressBook");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //--------------------------------- Get All By Contact ---------------------------------

    @GetMapping("/Show_All_AddressBook_Data")
    public ResponseEntity<Response> showAllData() {
        List<AddressBookModel> addressBookModelList = iAddressBookService.showAddressBookData();
        Response response = new Response(addressBookModelList, "AddressBook Data");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //--------------------------------- Get Contact By Id ---------------------------------
    @GetMapping("/Get_AddressBook_Data_ByID")
    public ResponseEntity<Response> getDataById(@RequestParam int id) {
        AddressBookModel addressBookData = iAddressBookService.getAddressBookById(id);
        Response response = new Response(addressBookData, "Address Book Data");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //--------------------------------- Update Contact ---------------------------------
    @PutMapping("/Update_AddressBook")
    public ResponseEntity<Response> updateAddressBook(@RequestParam int id, @RequestBody AddressBookDTO addressBookDTO) {
        AddressBookModel update = iAddressBookService.editAddressBook(id, addressBookDTO);
        Response response = new Response(update, "Address book update successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //--------------------------------- Delete Contact ---------------------------------
    @DeleteMapping("/Delete_AddressBook")
    public ResponseEntity<Response> updateAddressBook(@RequestParam int id) {
        iAddressBookService.deleteAddressBook(id);
        Response response = new Response("Deleted AddressBook: " + id, "Deleted Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //--------------------------------- Get Contact By City ---------------------------------
    @GetMapping("/Search_By_City")
    public ResponseEntity<Response> serchByCity(@RequestParam String city) {
        List<AddressBookModel> addressBookModelList = iAddressBookService.searchAddressBookByCity(city);
        Response response = new Response(addressBookModelList, "AddressBooks Data For City "+ city);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //--------------------------------- Get Contact By State ---------------------------------
    @GetMapping("/Search_By_State")
    public ResponseEntity<Response> serchByState(@RequestParam String state) {
        List<AddressBookModel> addressBookModelList = iAddressBookService.searchAddressBookByState(state);
        Response response = new Response(addressBookModelList, "AddressBooks Data For State "+ state);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
