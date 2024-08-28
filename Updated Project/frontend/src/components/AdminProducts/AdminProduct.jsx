import { AdminHeader } from "../AdminComponent/AdminHeader";
import { Footer } from "../Footer";
import axios from "axios";
import { useState } from "react";
import './AdminProduct.css';
import { toast } from 'react-toastify';
import AdminAllProduct from "./AdminAllProducts";
import { GetLocalStorage } from "../LocalStorage";

export const AdminProduct=()=>{
    const [productName, setProductName] = useState('');
    const [productPrice, setProductPrice] = useState('');
    const [categoryId, setCategoryId] = useState('');
    const token = GetLocalStorage("token");
    const [selectedCategory,setSelectedCategory] = useState('');

    const onToast = (s) => {
        toast.success(s, {
          position: "top-center",
          autoClose: 5000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
          theme: "light",
          });
    }

    const handleCategoryChange = (e) => {
        const selectedCategoryId = e.target.value;
        const selectedCategoryLabel = e.target.options[e.target.selectedIndex].text;
        setCategoryId(selectedCategoryId);
        setSelectedCategory(selectedCategoryLabel);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/product/addProduct', {
                productName,
                productPrice,
                categoryId
            },
            {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
            onToast("Product Added Successfully");
            setProductName('');
            setProductPrice('');
            setCategoryId('');
       
        } catch (error) {
            console.error('Error adding product:', error);
        }
    };

    return (
        <>
          <AdminHeader />
          <AdminAllProduct/>
          <div className="container1">
                <h2>Add Product</h2>
                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label htmlFor="productName">Product Name</label>
                        <input
                            type="text"
                            className="form-control"
                            id="productName"
                            placeholder="Enter product name"
                            value={productName}
                            onChange={(e) => setProductName(e.target.value)}
                        />
                    </div>
 
                    <div className="form-group">
                        <label htmlFor="productPrice">Product Price</label>
                        <input
                            type="number"
                            className="form-control"
                            id="productPrice"
                            placeholder="Enter product price"
                            value={productPrice}
                            onChange={(e) => setProductPrice(e.target.value)}
                        />
                    </div>
 
                    <div className="form-group">
                        <label htmlFor="categoryId" >Category</label>
                        <div className="custom-select">
                            <select
                               
                                id="categoryId"
                                value={categoryId}
                                onChange={handleCategoryChange}
                            >
                                <option value="">Select category</option>
                                <option value="1">Fresh vegetables</option>
                                <option value="2">Fish and Meat</option>
                                <option value="3">Healthy Fruit</option>
                                <option value="4">Dairy Products</option>
                            </select>
                        </div>
                    </div>
 
                    <button type="submit" className="btn btn-primary">Add Product</button>
                </form>
            </div>
            <Footer />
        </>
    );
};