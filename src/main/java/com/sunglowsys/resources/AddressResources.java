package com.sunglowsys.resources;

import com.sunglowsys.domain.Address;
import com.sunglowsys.service.AddressService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AddressResources {

    private final AddressService addressService;

    public AddressResources(AddressService addressService) {
        this.addressService = addressService;
    }
    @PostMapping("/addresses")
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        Address result = addressService.save(address);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @PutMapping("/addresses")
    public ResponseEntity<Address> updateAddress(@RequestBody Address address) {
        if (address.getId()==null) {
            throw new RuntimeException("it must be not null");
        }
        Address result = addressService.update(address);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @GetMapping("/addresses")
    public ResponseEntity<Page<Address>> findAll(Pageable pageable) {
        Page<Address> result = addressService.finalAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @GetMapping("/addresses/{id}")
    public ResponseEntity<Optional<Address>> findOne(@PathVariable Long id) {
        Optional<Address> result = addressService.findOne(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
