package com.dojo.youthbankserver.controllers;

import com.dojo.youthbankserver.dtos.ProductDTO;
import com.dojo.youthbankserver.exceptions.ProductNotFoundException;
import com.dojo.youthbankserver.services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j

@AllArgsConstructor
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> listProducts() {
        return ResponseEntity.ok().body(productService.listProducts());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable(name = "id") Long productId) throws ProductNotFoundException {
        return ResponseEntity.ok().body(productService.getProduct(productId));
    }

    @PostMapping("/newproduct")
    public ResponseEntity<ProductDTO> saveProduct(@Valid @RequestBody ProductDTO productDTO,Long businessId ) {
        return ResponseEntity.ok().body(productService.saveProduct(productDTO,businessId ));
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<ProductDTO> updateProduct(@Valid @PathVariable Long id, @RequestBody ProductDTO productDTO) throws ProductNotFoundException {
        return ResponseEntity.ok().body(productService.updateProduct(productDTO, id));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }


//    @GetMapping("/addtotable/{InvoiceId}")
//    public ResponseEntity<List<ProductDTO>> addToTable(@PathVariable(name = "InvoiceId") Long id,@PathVariable(name = "productId") Long productId ) throws InvoiceNotFoundException, ProductNotFoundException {
//        return ResponseEntity.ok().body(productService.getProduct(productId));
//    }


//    @PostMapping("/{invoiceId}/addproducts")
//    public ResponseEntity<?> addProductsToInvoice(@PathVariable Long invoiceId, @RequestBody List<ProductDTO> productDTOs) {
//        try {
//            productService.addToTable(invoiceId, productDTOs);
//            return ResponseEntity.ok().build();
//        } catch (InvoiceNotFoundException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }

//    @PostMapping("/{invoiceId}/addproducts")
//    public ResponseEntity<Object> addProductsToInvoice(@PathVariable Long invoiceId, ProductDTO productDTO) throws InvoiceNotFoundException {
//            productService.addToTable(invoiceId,productDTO);
//            return ResponseEntity.ok().build();
//
//    }



//    @PostMapping("/{invoiceId}/products/add")
//    public ResponseEntity<InvoiceDTO> addProductToInvoice(@RequestParam Long invoiceId, @RequestBody ProductDTO productDTO) {
//        try {
//            InvoiceDTO invoiceDTO = invoiceService.addProductToInvoice(invoiceId, productDTO);
//            return ResponseEntity.ok().body(invoiceDTO);
//        } catch (InvoiceNotFoundException e) {
//            // Handle exception
//            return ResponseEntity.notFound().build();
//        }
    }



