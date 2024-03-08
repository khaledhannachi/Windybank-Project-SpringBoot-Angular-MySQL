package com.dojo.youthbankserver.services;

import com.dojo.youthbankserver.dtos.InvoiceDTO;
import com.dojo.youthbankserver.dtos.ProductDTO;
import com.dojo.youthbankserver.entities.Business;
import com.dojo.youthbankserver.entities.Invoice;
import com.dojo.youthbankserver.entities.Product;
import com.dojo.youthbankserver.exceptions.BusinessNotFoundException;
import com.dojo.youthbankserver.exceptions.InvoiceNotFoundException;
import com.dojo.youthbankserver.mappers.InvoiceMapper;
import com.dojo.youthbankserver.mappers.ProductMapper;
import com.dojo.youthbankserver.repositories.BusinessRepository;
import com.dojo.youthbankserver.repositories.InvoiceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class InvoiceService {
    private  InvoiceRepository invoiceRepository;
    private BusinessRepository businessRepository;
    private  InvoiceMapper invoiceMapper;
    private ProductMapper productMapper;
    public InvoiceDTO saveInvoice(InvoiceDTO invoiceDTO, Long businessId) throws BusinessNotFoundException {
        log.info("Saving new Invoice");

        // Retrieve the Business entity
        Business business = businessRepository.findById(businessId)
                .orElseThrow(() -> {
                    log.error("Business not found with id: {}", businessId);
                    return new BusinessNotFoundException("Business not found with id: " + businessId);
                });

        // Map from InvoiceDTO to Invoice entity
        Invoice invoice = invoiceMapper.fromInvoiceDTO(invoiceDTO);

        // Set the Business entity in the Invoice
        invoice.setBusinessInvoices(business);

        // Save the Invoice entity
        Invoice savedInvoice = invoiceRepository.save(invoice);

        // Map from Invoice entity to DTO and return
        return invoiceMapper.fromInvoice(savedInvoice);
    }

    public List<InvoiceDTO> listInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        List<InvoiceDTO> invoiceDTOS = new ArrayList<>();
        for (Invoice invoice : invoices) {
            InvoiceDTO invoiceDTO = invoiceMapper.fromInvoice(invoice);
            invoiceDTOS.add(invoiceDTO);
            List<Product> products=invoice.getChoosedProducts();
            List<ProductDTO>productsDTO=products.stream().map(pro->productMapper.fromProduct(pro)).collect(Collectors.toList());
            invoiceDTO.setChoosedProductsDTO(productsDTO);
        }
        return invoiceDTOS;
    }

    public InvoiceDTO getInvoice(Long invoiceId) throws InvoiceNotFoundException {
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(() -> new InvoiceNotFoundException("Invoice not found with id: " + invoiceId));
        InvoiceDTO invoiceDTO=invoiceMapper.fromInvoice(invoice);
        List<Product> products=invoice.getChoosedProducts();
        List<ProductDTO>productsDTO=products.stream().map(pro->productMapper.fromProduct(pro)).collect(Collectors.toList());
        invoiceDTO.setChoosedProductsDTO(productsDTO);
        return invoiceDTO;
    }



    public void deleteInvoice(Long invoiceId) {
        invoiceRepository.deleteById(invoiceId);
    }
    public InvoiceDTO updateInvoice(InvoiceDTO invoiceDTO, Long invoiceId) throws InvoiceNotFoundException {
        log.info("Updating Invoice with id: {}", invoiceId);
        Invoice existingInvoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new InvoiceNotFoundException("Invoice not found with id: " + invoiceId));
        Invoice updatedInvoice = invoiceMapper.fromInvoiceDTO(invoiceDTO);
        updatedInvoice.setId(existingInvoice.getId());
        Invoice savedInvoice = invoiceRepository.save(updatedInvoice);
        return invoiceMapper.fromInvoice(savedInvoice);
    }

//
//    public InvoiceDTO addProductToInvoice(Long invoiceId, ProductDTO productDTO) throws InvoiceNotFoundException {
//        // Retrieve the invoice entity from the database using its ID
//        Invoice invoice = invoiceRepository.findById(invoiceId)
//                .orElseThrow(() -> new InvoiceNotFoundException("Invoice not found with id: " + invoiceId));
//        Product product = productMapper.fromProductDTO(productDTO);
//        if (invoice.getChoosedProducts() == null) {
//            invoice.setChoosedProducts(new ArrayList<>());
//        }
//        // Add the product to the list of chosen products in the invoice
//        invoice.getChoosedProducts().add(product);
//        // Save the updated invoice entity
//        invoice = invoiceRepository.save(invoice);
//        // Map the updated invoice entity to an InvoiceDTO
//        InvoiceDTO invDTO = invoiceMapper.fromInvoice(invoice);
//        // Map the added product to a ProductDTO
////        ProductDTO chosenProductDTO = productMapper.fromProduct(product);
////        List<ProductDTO> productDTOList=new ArrayList<>();
////        productDTOList.add(chosenProductDTO);
//        // Add the product DTO to the list of chosen products DTO in the invoice DTO
////        invDTO.getChoosedProductsDTO().add(chosenProductDTO);
//        List<Product> products=invoice.getChoosedProducts();
//        List<ProductDTO>productsDTO=products.stream().map(pro->productMapper.fromProduct(pro)).collect(Collectors.toList());
//        invDTO.setChoosedProductsDTO(productsDTO);
//        return invDTO;
//    }

    public InvoiceDTO addToTable(Long invoiceId, ProductDTO productDTO) throws InvoiceNotFoundException {
        // Retrieve the invoice by its ID
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new InvoiceNotFoundException("Invoice not found with id: " + invoiceId));
        Product product=productMapper.fromProductDTO(productDTO);
        invoice.getChoosedProducts().add(product);
        invoiceRepository.save(invoice);
        InvoiceDTO invoiceDTO=invoiceMapper.fromInvoice(invoice);
        List<Product> productList=invoice.getChoosedProducts();
        List<ProductDTO> productListDTO=productList.stream().map(pro->productMapper.fromProduct(pro)).collect(Collectors.toList());
        invoiceDTO.setChoosedProductsDTO(productListDTO);
        return invoiceDTO;
    }
}
