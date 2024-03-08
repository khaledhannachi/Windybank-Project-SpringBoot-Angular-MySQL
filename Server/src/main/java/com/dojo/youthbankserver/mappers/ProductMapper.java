package com.dojo.youthbankserver.mappers;

import com.dojo.youthbankserver.dtos.ProductDTO;
import com.dojo.youthbankserver.entities.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

	   public ProductDTO fromProduct(Product product){
		   ProductDTO productDTO=new ProductDTO();
	        BeanUtils.copyProperties(product,productDTO);
	        return  productDTO;
	    }
	    public Product fromProductDTO(ProductDTO productDTO){
			Product product=new Product();
	        BeanUtils.copyProperties(productDTO,product);
	        return  product;
	    }
	
}
