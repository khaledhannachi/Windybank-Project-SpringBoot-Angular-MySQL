package com.dojo.youthbankserver.controllers;

import com.dojo.youthbankserver.dtos.InvoiceDTO;
import com.dojo.youthbankserver.dtos.ProductDTO;
import com.dojo.youthbankserver.exceptions.BusinessNotFoundException;
import com.dojo.youthbankserver.exceptions.InvoiceNotFoundException;
import com.dojo.youthbankserver.services.InvoiceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1/invoices")
@CrossOrigin(origins = "*")
public class InvoiceController {
    private InvoiceService invoiceService;

    @GetMapping("")
    public ResponseEntity<List<InvoiceDTO>> listInvoices() {
        return ResponseEntity.ok().body(invoiceService.listInvoices());
    }
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDTO> getInvoice(@PathVariable(name = "id") Long invoiceId) throws InvoiceNotFoundException {
        return ResponseEntity.ok().body(invoiceService.getInvoice(invoiceId));
    }


    @PostMapping("/newinvoice/{id}")
    public ResponseEntity<InvoiceDTO> saveInvoice(@Valid @RequestBody InvoiceDTO invoiceDTO, @PathVariable Long id) throws BusinessNotFoundException {
        return ResponseEntity.ok().body(invoiceService.saveInvoice(invoiceDTO, id));
    }


    @PutMapping("/{id}/edit")
    public ResponseEntity<InvoiceDTO> updateInvoice(@Valid @PathVariable Long id, @RequestBody InvoiceDTO invoiceDTO) throws InvoiceNotFoundException {
        return ResponseEntity.ok().body(invoiceService.updateInvoice(invoiceDTO, id));
    }
    @DeleteMapping("/{id}")
    public void deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
    }


    @PostMapping("/{invoiceId}/addproducts")
    public ResponseEntity<Object> addProductsToInvoice(@PathVariable Long invoiceId,@RequestBody ProductDTO productDTO) throws InvoiceNotFoundException {

        return ResponseEntity.ok().body( invoiceService.addToTable(invoiceId,productDTO));
    }


}
