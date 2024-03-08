package com.dojo.youthbankserver.mappers;

import com.dojo.youthbankserver.dtos.InvoiceDTO;
import com.dojo.youthbankserver.entities.Invoice;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class InvoiceMapper {
    public InvoiceDTO fromInvoice(Invoice invoice){
        InvoiceDTO invoiceDTO=new InvoiceDTO();
        BeanUtils.copyProperties(invoice,invoiceDTO);
        return  invoiceDTO;
    }
    public Invoice fromInvoiceDTO(InvoiceDTO invoiceDTO){
        Invoice invoice = new Invoice();
        BeanUtils.copyProperties(invoiceDTO,invoice);
        return  invoice;
    }

}
