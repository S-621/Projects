package com.capgemini.lenskart.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.lenskart.dto.AdminDTO;
import com.capgemini.lenskart.dto.CategoryDTO;
import com.capgemini.lenskart.dto.ProductDTO;
import com.capgemini.lenskart.dto.SignInDto;
import com.capgemini.lenskart.entity.Admin;
import com.capgemini.lenskart.entity.Category;
import com.capgemini.lenskart.entity.Product;
import com.capgemini.lenskart.entity.User;
import com.capgemini.lenskart.exception.CustomException;
import com.capgemini.lenskart.repository.AdminRepository;
import com.capgemini.lenskart.repository.CategoryRepository;
import com.capgemini.lenskart.repository.ProductRepository;
import com.capgemini.lenskart.response.CommonResponse;
import com.capgemini.lenskart.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public String addAdmin(AdminDTO adminDTO) throws CustomException {
		Admin user = new Admin();
		Admin dbUser = adminRepository.findByEmail(adminDTO.getEmail());
		if (dbUser != null) {
			throw new CustomException("User already Found!!");
		}
		user = modelMapper.map(adminDTO, Admin.class);
		User saveUser = adminRepository.save(user);
		adminDTO.setUserid(saveUser.getUserid());
		return "Admin created";
	}

	@Override
	public AdminDTO getAdminByEmail(String email) throws CustomException {
		AdminDTO userDTO = new AdminDTO();
		Admin dbUser = adminRepository.findByEmail(email);
		if (dbUser == null) {
			throw new CustomException("User Not Found!!");
		}
		if (dbUser != null) {
			Boolean isActive = dbUser.getIsActive();
			if (isActive == false) {
				throw new CustomException("User is Not active!!");
			}

		}
		userDTO = modelMapper.map(dbUser, AdminDTO.class);
		adminRepository.save(dbUser);
		return userDTO;
	}

	@Override
	public ProductDTO updateProduct(ProductDTO productDTO) throws CustomException {
		Product dbproduct = productRepository.findByProductIdAndIsActiveTrue(productDTO.getProductId());
		if (dbproduct == null) {
			throw new CustomException("Product Not Found!!");
		}
		dbproduct = modelMapper.map(productDTO, Product.class);
		productRepository.save(dbproduct);
		return productDTO;
	}

	@Override
	public ProductDTO addProduct(ProductDTO productDTO) throws CustomException {
		Product product = new Product();

		Product dbproduct = productRepository.findByProductName(productDTO.getProductName());
		if (dbproduct != null) {
			throw new CustomException("Product already Found!!");
		}
		product = modelMapper.map(productDTO, Product.class);
		Optional<Category> dbCategory = categoryRepository.findById(productDTO.getCategory().getCategoryId());

		if (dbCategory.isEmpty()) {
			throw new CustomException("Category id is not found: " + productDTO.getCategory().getCategoryId());
		}
		product.setCategory(dbCategory.get());
		Product saveproduct = productRepository.save(product);
		productDTO.setProductId(saveproduct.getProductId());
		return productDTO;
	}

	@Override
	public String addCategory(CategoryDTO categoryDTO) throws CustomException {
		Category category1 = new Category();

		Category dbCategory = categoryRepository.findByCategoryName(categoryDTO.getCategoryName());
		if (dbCategory != null) {
			throw new CustomException("Category already Found!!");
		}
		category1 = modelMapper.map(categoryDTO, Category.class);
		Category saveCategory = categoryRepository.save(category1);
		categoryDTO.setCategoryId(saveCategory.getCategoryId());
		return "Category Added!!";
	}

	@Override
	public String updateCategory(CategoryDTO categoryDTO) throws CustomException {
		Optional<Category> dbCategory = categoryRepository.findById(categoryDTO.getCategoryId());

		if (dbCategory.isEmpty()) {
			throw new CustomException("Category id is not found: " + categoryDTO.getCategoryId());
		}
		Category category = dbCategory.get();
		category = modelMapper.map(categoryDTO, Category.class);
		categoryRepository.save(category);
		return "Category updated!!";
	}

	@Override
	public AdminDTO updateAdminById(Integer userId, AdminDTO adminDTO) throws CustomException {
		Optional<Admin> admin = adminRepository.findById(userId);
		if (admin.isPresent()) {
			Admin dbAdmin = admin.get();
			dbAdmin.setFirstName(adminDTO.getFirstName());
			dbAdmin.setLastName(adminDTO.getLastName());
			dbAdmin.setNumber(adminDTO.getNumber());
			dbAdmin.setAddress(adminDTO.getAddress());
			dbAdmin.setUserName(adminDTO.getUserName());
			dbAdmin.setPassword(adminDTO.getPassword());
			dbAdmin.setEmail(adminDTO.getEmail());
			dbAdmin.setRole("Admin");
			adminRepository.save(dbAdmin);
		} else {
			throw new CustomException("No Customer Found");
		}

		return adminDTO;
	}

	@Override
	public String deleteAdminById(Integer userId) throws CustomException {
		if (adminRepository.existsById(userId)) {
			adminRepository.deleteById(userId);
			return "Deleted Successfully";
		} else {
			throw new CustomException("No Admin Found with id " + userId);
		}
	}

	@Override
	public String deleteProductById(Integer productId) throws CustomException {
		if (productRepository.existsById(productId)) {
			productRepository.deleteById(productId);
			return "Deleted product successfully";
		} else {
			throw new CustomException("No Product Found");
		}
	}

	@Override
	public String deleteCategoryById(Integer categoryId) throws CustomException {
		if (categoryRepository.existsById(categoryId)) {
			categoryRepository.deleteById(categoryId);
			return "Deleted Successfully";
		} else {
			throw new CustomException("Invalid Category id");
		}
	}

	@Override
	public AdminDTO updateAdmin(AdminDTO adminDTO)throws CustomException {
		Admin dbUser = adminRepository.findByEmail(adminDTO.getEmail());
		if (dbUser == null) {
			throw new CustomException("User Not Found!!");
		}
		dbUser = modelMapper.map(adminDTO, Admin.class);
		adminRepository.save(dbUser);
		return adminDTO;
	}

	@Override
	public CommonResponse signIn(SignInDto signRequestDTO)throws CustomException {
		Admin user = adminRepository.findByEmail(signRequestDTO.getEmail());
		if (user != null) {
			String passwrd = signRequestDTO.getPassword();
			String userPasswrd = user.getPassword();
			boolean flag = passwrd.matches(userPasswrd);
			if (flag) {
				return new CommonResponse("Login Successful");
			} else {
				throw new CustomException("password incorrect");
			}
		} else {
			throw new CustomException("No User found");
		}
	}
}
