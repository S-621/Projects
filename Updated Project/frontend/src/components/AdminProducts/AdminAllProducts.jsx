import React, { useState, useEffect } from "react";
import axios from "axios";
import { Button } from "react-bootstrap";
 import { GetLocalStorage } from "../LocalStorage";

const AdminAllProduct = () => {
  const [products, setProducts] = useState([]);
  const token = GetLocalStorage("token");
 
  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const response = await fetch("http://localhost:8080/product/getAllProducts",{
          method:"GET",
          headers: {
            'Authorization': `Bearer ${token}`
          }
        });
        const data = await response.json();
        setProducts(data);
      } catch (error) {
        console.error("Error fetching products:", error);
      }
    };
 
    fetchProduct();
  }, []);
 
  const handleDelete = async (productId) => {
    try {
      await axios.delete(`http://localhost:8080/product/deleteProduct/${productId}`, {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      });
      // Update products state after deletion
      setProducts(products.filter(product => product.productId !== productId));
    } catch (error) {
      console.log(error);
    }
  };
 
  return (
    <>
      <div className="adminOrder" style={{ paddingBottom: '3rem' }}>
        <h1>All Products</h1>
        <table className="admin-order-table">
          <thead>
            <tr>
              <th>Product Id</th>
              <th>Product Name</th>
              <th>Product Price</th>
              <th>Delete Product</th>
            </tr>
          </thead>
          <tbody>
            {products.map((product) => (
              <tr key={product.productId}>
                <td>{product.productId}</td>
                <td>{product.productName}</td>
                <td>{product.productPrice}</td>
                <td>
                  <Button variant="danger" onClick={() => handleDelete(product.productId)}>Delete</Button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </>
  );
};
 
export default AdminAllProduct;