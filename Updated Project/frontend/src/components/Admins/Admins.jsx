import { useState,useEffect } from "react";
import { AdminHeader } from "../AdminComponent/AdminHeader";
import { Footer } from "../Footer";
import { GetLocalStorage } from "../LocalStorage";

export const Admins=()=>{
    const [adminData, setAdminData] = useState([]);
    const token = GetLocalStorage("token");

    useEffect(() => {
        const fetchAdminData = async () => {
          try {
            const response = await fetch("http://localhost:8080/auth/fetchByRole/ROLE_ADMIN",{
              headers: {
                'Authorization': `Bearer ${token}`
              }
            });
            const data = await response.json();
            setAdminData(data);
          } catch (error) {
            console.error("Error fetching orders:", error);
          }
        };
    
        fetchAdminData();
      }, []);
    
    return(
        <>
        <AdminHeader />
      <div style={{paddingBottom:'3rem'}}>
        <h1>All Admins </h1>

        <table className="admin-order-table">
          <thead>
            <tr>
              <th>Admin Id</th>
              <th>Admin Username</th>
              <th>Email</th>
              <th>Phone Number</th>
              <th>Address</th>
            </tr>
          </thead>
          <tbody>
            {adminData.map((admin) => (
              <tr key={admin.id}>
                <td>{admin.id}</td>
                <td>{admin.username}</td>
                <td>{admin.email}</td>
                <td>{admin.number}</td>
                <td>{admin.address}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <Footer />

        </>
    )
}