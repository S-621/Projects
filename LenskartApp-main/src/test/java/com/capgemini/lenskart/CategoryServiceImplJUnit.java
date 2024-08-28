package com.capgemini.lenskart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
 
import com.capgemini.lenskart.dto.CategoryDTO;
import com.capgemini.lenskart.entity.Category;
import com.capgemini.lenskart.repository.CategoryRepository;
import com.capgemini.lenskart.service.impl.CategoryServiceImpl;
 
@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {
 
    @Mock
    private CategoryRepository categoryRepository;
 
    @Mock
    private ModelMapper modelMapper;
 
    @InjectMocks
    private CategoryServiceImpl categoryService;
 
    @Test
    void testAddCategory() {
        // Mocking data
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryName("Test Category");
 
        Category dbCategory = new Category();
        dbCategory.setCategoryName("Test Category");
 
        when(categoryRepository.findByCategoryName("Test Category")).thenReturn(null);
        when(modelMapper.map(categoryDTO, Category.class)).thenReturn(dbCategory);
        when(categoryRepository.save(dbCategory)).thenReturn(dbCategory);
 
        // Test the method
        String result = categoryService.addCategory(categoryDTO);
 
        // Assertions
        assertEquals("Category Added!!", result);
    }
 
    @Test
    void testRemoveCategory() {
        // Mocking data
        int categoryId = 1;
        Optional<Category> dbCategory = Optional.of(new Category());
 
        when(categoryRepository.findById(categoryId)).thenReturn(dbCategory);
 
        // Test the method
        String result = categoryService.removeCategory(categoryId);
 
        // Assertions
        assertEquals("Category deleted!!", result);
    }
 
    @Test
    void testUpdateCategory() {
        // Mocking data
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(1);
 
        Optional<Category> dbCategory = Optional.of(new Category());
 
        when(categoryRepository.findById(1)).thenReturn(dbCategory);
        when(modelMapper.map(categoryDTO, Category.class)).thenReturn(dbCategory.get());
        when(categoryRepository.save(dbCategory.get())).thenReturn(dbCategory.get());
 
        // Test the method
        String result = categoryService.updateCategory(categoryDTO);
 
        // Assertions
        assertEquals("Category updated!!", result);
    }
 
    @Test
    void testSearchCategoryByName() {
        // Mocking data
        String categoryName = "Test Category";
        CategoryDTO expectedCategoryDTO = new CategoryDTO();
        expectedCategoryDTO.setCategoryName(categoryName);
 
        Category dbCategory = new Category();
        dbCategory.setCategoryName(categoryName);
 
        when(categoryRepository.findByCategoryName(categoryName)).thenReturn(dbCategory);
        when(modelMapper.map(dbCategory, CategoryDTO.class)).thenReturn(expectedCategoryDTO);
 
        // Test the method
        CategoryDTO resultCategoryDTO = categoryService.searchCategoryByName(categoryName);
 
        // Assertions
        assertNotNull(resultCategoryDTO);
        assertEquals(categoryName, resultCategoryDTO.getCategoryName());
    }
 
    @Test
    void testSearchCategoryById() {
        // Mocking data
        int categoryId = 1;
        CategoryDTO expectedCategoryDTO = new CategoryDTO();
        expectedCategoryDTO.setCategoryId(categoryId);
 
        Category dbCategory = new Category();
        dbCategory.setCategoryId(categoryId);
 
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(dbCategory));
        when(modelMapper.map(dbCategory, CategoryDTO.class)).thenReturn(expectedCategoryDTO);
 
        // Test the method
        CategoryDTO resultCategoryDTO = categoryService.searchCategoryById(categoryId);
 
        // Assertions
        assertNotNull(resultCategoryDTO);
        assertEquals(categoryId, resultCategoryDTO.getCategoryId());
    }
 
    @Test
    void testFindAllCategory() {
        // Mocking data
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category());
        categoryList.add(new Category());
 
        when(categoryRepository.findAll()).thenReturn(categoryList);
 
        // Test the method
        List<Category> resultCategoryList = categoryService.findAllCategory();
 
        // Assertions
        assertNotNull(resultCategoryList);
        assertEquals(2, resultCategoryList.size());
    }
}
