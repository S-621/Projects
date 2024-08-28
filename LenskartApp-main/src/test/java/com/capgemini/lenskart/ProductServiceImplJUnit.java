package com.capgemini.lenskart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
 
import java.util.Optional;
 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
 
import com.capgemini.lenskart.dto.ProductDTO;
import com.capgemini.lenskart.entity.Category;
import com.capgemini.lenskart.entity.Product;
import com.capgemini.lenskart.repository.CategoryRepository;
import com.capgemini.lenskart.repository.ProductRepository;
import com.capgemini.lenskart.service.impl.ProductServiceImpl;
 
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
 
    @Mock
    private ProductRepository productRepository;
 
    @Mock
    private CategoryRepository categoryRepository;
 
    @Mock
    private ModelMapper modelMapper;
 
    @InjectMocks
    private ProductServiceImpl productService;
 
    @Test
    void testGetById() {
        // Mocking data
        int productId = 1;
        ProductDTO expectedProductDTO = new ProductDTO();
        expectedProductDTO.setProductId(productId);
        Product dbProduct = new Product();
        dbProduct.setProductId(productId);
 
        when(productRepository.findByProductIdAndIsActiveTrue(productId)).thenReturn(dbProduct);
        when(modelMapper.map(dbProduct, ProductDTO.class)).thenReturn(expectedProductDTO);
 
        // Test the method
        ProductDTO resultProductDTO = productService.getById(productId);
 
        // Assertions
        assertNotNull(resultProductDTO);
        assertEquals(productId, resultProductDTO.getProductId());
    }
 
    @Test
    void testUpdateProduct() {
        // Similar to getById, mock the necessary data and test the updateProduct method
    }
    @Test
    void testDeleteProduct() {
        // Similar to getById, mock the necessary data and test the deleteProduct method
    }
    @Test
    void testAddProduct() {
        // Mocking data
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductName("TestProduct");
        productDTO.setCategory(new Category());
        //productDTO.getCategory().setCategoryId(1L);
 
        Product dbProduct = new Product();
 
        when(productRepository.findByProductName(productDTO.getProductName())).thenReturn(null);
        when(categoryRepository.findById(productDTO.getCategory().getCategoryId()))
            .thenReturn(Optional.of(new Category()));
        when(modelMapper.map(productDTO, Product.class)).thenReturn(dbProduct);
        when(productRepository.save(dbProduct)).thenReturn(dbProduct);
 
        // Test the method
        ProductDTO resultProductDTO = productService.addProduct(productDTO);
 
        // Assertions
        assertNotNull(resultProductDTO);
        assertNotNull(resultProductDTO.getProductId());
    }
 
    @Test
    void testFindAll() {
        // Similar to getById, mock the necessary data and test the findAll method
    }
 
    @Test
    void testGetProductByCustomer() {
        // Similar to getById, mock the necessary data and test the getProductByCustomer method
    }
 
}
