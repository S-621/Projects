package com.capgemini.lenskart.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.lenskart.dto.ProductDTO;
import com.capgemini.lenskart.entity.Category;
import com.capgemini.lenskart.entity.Product;
import com.capgemini.lenskart.exception.CustomException;
import com.capgemini.lenskart.repository.CategoryRepository;
import com.capgemini.lenskart.repository.ProductRepository;
import com.capgemini.lenskart.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ProductDTO getById(int id) {

		ProductDTO productDTO = new ProductDTO();
		Product dbproduct = productRepository.findByProductIdAndIsActiveTrue(id);
		if (dbproduct == null) {
			throw new CustomException("Product Not Found!!");
		}
		//Product product = dbproduct.get();
		productDTO = modelMapper.map(dbproduct, ProductDTO.class);
		return productDTO;
	}
	@Override
	public ProductDTO updateProduct(ProductDTO productDTO) {
		Product dbproduct = productRepository.findByProductIdAndIsActiveTrue(productDTO.getProductId());
		if (dbproduct == null) {
			throw new CustomException("Product Not Found!!");
		}
		dbproduct = modelMapper.map(productDTO, Product.class);
		productRepository.save(dbproduct);
		return productDTO;
	}
	@Override
	public boolean deleteProduct(ProductDTO productDTO) {
		Product dbproduct = productRepository.findByProductName(productDTO.getProductName());
		if (dbproduct == null) {
			throw new CustomException("Product Not Found!!");
		}
		dbproduct.setIsActive(false);
		productRepository.delete(dbproduct);
		return true;
	}
	@Override
	public ProductDTO addProduct(ProductDTO productDTO)throws CustomException {
		Product product = new Product();

		Product dbproduct = productRepository.findByProductName(productDTO.getProductName());
		if (dbproduct != null) {
			throw new CustomException("Product already Found!!");
		}
		product = modelMapper.map(productDTO, Product.class);
		Optional<Category> dbCategory = categoryRepository.findById(productDTO.getCategory().getCategoryId());

		if (dbCategory.isEmpty()) {
			throw new CustomException("Category id is not found: " +productDTO.getCategory().getCategoryId());
		}
		product.setCategory(dbCategory.get());
		Product saveproduct = productRepository.save(product);
		productDTO.setProductId(saveproduct.getProductId());
		return productDTO;
	}
	@Override
	public List<ProductDTO> findAll() {
		List<Product> dbproduct = productRepository.findByIsActiveTrue();
		return modelMapper.map(dbproduct, new TypeToken<List<ProductDTO>>() {
		}.getType());
	}
	@Override
	public List<ProductDTO> getProductByBrand(String brandName) {
		List<Product> dbproduct = productRepository.findByBrandAndIsActiveTrue(brandName);
		return modelMapper.map(dbproduct, new TypeToken<List<ProductDTO>>() {
		}.getType());
	}
	@Override
	public String deleteProduct(Integer productId) {
		Optional<Product> product = productRepository.findById(productId);
		if(product.isPresent()) {
			productRepository.deleteById(productId);
		}else {
			throw new CustomException("No Product Found");
		}
		
		return "Deleted Successfully";
	}
}
