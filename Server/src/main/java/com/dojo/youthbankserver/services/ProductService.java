package com.dojo.youthbankserver.services;


import com.dojo.youthbankserver.dtos.ProductDTO;
import com.dojo.youthbankserver.entities.Business;
import com.dojo.youthbankserver.entities.Product;
import com.dojo.youthbankserver.exceptions.ProductNotFoundException;
import com.dojo.youthbankserver.mappers.InvoiceMapper;
import com.dojo.youthbankserver.mappers.ProductMapper;
import com.dojo.youthbankserver.repositories.BusinessRepository;
import com.dojo.youthbankserver.repositories.InvoiceRepository;
import com.dojo.youthbankserver.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class ProductService {
    private  ProductRepository productRepository;
    private  ProductMapper productMapper;
    private InvoiceMapper invoiceMapper;
    private BusinessRepository businessRepository;
    private InvoiceRepository invoiceRepository;
    public ProductDTO saveProduct(ProductDTO productDTO, Long businessId) {
        log.info("Saving new Product");
        // Retrieve the Business entity
        Business business = businessRepository.findById(businessId).orElse(null);
        if (business == null) {
            log.error("Business not found with id: {}", businessId);
            // Handle the error appropriately
        }
        // Map from ProductDTO to Product entity
        Product product = productMapper.fromProductDTO(productDTO);
        // Set the Business entity in the Product
        product.setBusinessProducts(business);
        // Save the product entity
        Product savedProduct = productRepository.save(product);
        // Map from product entity to DTO and return
        return productMapper.fromProduct(savedProduct);
    }

    public List<ProductDTO> listProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product : products) {
            ProductDTO productDTO = productMapper.fromProduct(product);
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }
    public ProductDTO getProduct(Long productId) throws ProductNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));
        return productMapper.fromProduct(product);
    }

    public ProductDTO updateProduct(ProductDTO productDTO, Long productId) throws ProductNotFoundException {
        log.info("Updating Product with id: {}", productId);
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));
        Product updatedProduct = productMapper.fromProductDTO(productDTO);
        updatedProduct.setId(existingProduct.getId()); // Ensure the ID remains the same
        Product savedProduct = productRepository.save(updatedProduct);
        return productMapper.fromProduct(savedProduct);
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

//    public List<ProductDTO> getProductsByInvoiceId(Long invoiceId) {
//        List<Product> products = productRepository.findProductsByInvoiceId(invoiceId);
//        List<ProductDTO> productDTOs = products.stream()
//                .map(product -> {
//                    ProductDTO dto = productMapper.fromProduct(product);
//                    dto.setInvoiceProductsDTO(invoiceMapper.fromInvoice(product.getInvoiceProducts()));
//
//                    return dto;
//                })
//                .collect(Collectors.toList());
//        return productDTOs;
//    }



//    public InvoiceDTO addToTable(@PathVariable Long InvoiceId, @PathVariable Long productId) throws InvoiceNotFoundException, ProductNotFoundException {
//        // Retrieve the Invoice entity from the repository
//        Invoice invoice = invoiceRepository.findById(InvoiceId)
//                .orElseThrow(() -> new InvoiceNotFoundException("Invoice not found with id: " + InvoiceId));
//        InvoiceDTO invoiceDTO = invoiceMapper.fromInvoice(invoice);
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));
//        ProductDTO productDTO = productMapper.fromProduct(product);
//        invoiceDTO.getChoosedProductsDTO().add(productDTO);
//
//        return invoiceDTO;
//    }
    
//    public InvoiceDTO addToTable(Long invoiceId, List<ProductDTO> productDTOs) throws InvoiceNotFoundException {
//        // Retrieve the invoice by its ID
//        Invoice invoice = invoiceRepository.findById(invoiceId)
//                .orElseThrow(() -> new InvoiceNotFoundException("Invoice not found with id: " + invoiceId));
//        // Map ProductDTOs to Product entities
//        List<Product> products = productDTOs.stream()
//                .map(productMapper::fromProductDTO)
//                .collect(Collectors.toList());
//        // Add products to the invoice
//        invoice.getChoosedProducts().addAll(products);
//// Save the updated invoice entity
//        invoiceRepository.save(invoice);
//        // Map the updated invoice entity to DTO
//        InvoiceDTO updatedInvoiceDTO = invoiceMapper.fromInvoice(invoice);
//        List<ProductDTO> productDTOss = products.stream()
//                .map(productMapper::fromProduct)
//                .collect(Collectors.toList());
//        updatedInvoiceDTO.getChoosedProductsDTO().addAll(productDTOss);
//        return updatedInvoiceDTO;
//    }
//    public InvoiceDTO addToTable(Long invoiceId, ProductDTO productDTO) throws InvoiceNotFoundException {
//        // Retrieve the invoice by its ID
//        Invoice invoice = invoiceRepository.findById(invoiceId)
//                .orElseThrow(() -> new InvoiceNotFoundException("Invoice not found with id: " + invoiceId));
//        Product product=productMapper.fromProductDTO(productDTO);
//        invoice.getChoosedProducts().add(product);
//        invoiceRepository.save(invoice);
//        InvoiceDTO invoiceDTO=invoiceMapper.fromInvoice(invoice);
//        List<Product> productList=invoice.getChoosedProducts();
//        List<ProductDTO> productListDTO=productList.stream().map(pro->productMapper.fromProduct(pro)).collect(Collectors.toList());
//        invoiceDTO.setChoosedProductsDTO(productListDTO);
//        return invoiceDTO;
//    }
}







