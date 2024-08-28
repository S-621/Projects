import { Header } from "../components/Header";
import { toast } from 'react-toastify';
import { useState } from "react";
import { SetLocalStorage } from "../components/LocalStorage";


export const AdminLogin=()=>{

    const onToast = (s) => {
        if ('Login Successfull!!' === s) {
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
        }else{
          toast.error(s, {
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
      }
    
    
      const [user, setUser] = useState({
        email: "",
        password: "",
      });
      

      const handleLogin = async (e) => {
        e.preventDefault();
        const res = await fetch("http://localhost:8080/auth/token", {
          method: "POST",
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
          },
          body: JSON.stringify(user) 
        });
        try{
        const data = await res.json(); 
        const adminId = data.user.id;
        const role = data.user.role;
        const token = data.token;
          if(role==="ROLE_ADMIN"){
          onToast('Login Successfull!!');
          SetLocalStorage("AdminId",adminId);
          SetLocalStorage("token",token);
          window.location.href = "/adminpanel";
          }
          else{
            onToast('UnAuthorised User');
          }
      }catch{
        onToast('Invalid Credentials');
      }
      };


    return(
        <>
        <Header />
        <div className="d-flex flex-column justify-content-center" id="login-box">
      <div className="login-box-header">
        <h4
          style={{
            color: "rgb(139,139,139)",
            marginBottom: 0,
            fontWeight: 400,
            fontSize: 27
          }}
        >
         Admin Login
        </h4>
      </div>
      <div className="d-flex flex-row align-items-center login-box-seperator-container">
        <div className="login-box-seperator" />
        <div className="login-box-seperator" />
      </div>
      <div className="email-login" style={{ backgroundColor: "#ffffff" }}>
        <input
          type="email"
          className="email-imput form-control"
          style={{ marginTop: 10 }}
          required=""
          placeholder="Email"
          name="email"
          onChange={(e) => setUser({ ...user, email: e.target.value })}
          value={user.email}
          minLength={6}
        />
        <input
          type="password"
          className="password-input form-control"
          style={{ marginTop: 10 }}
          required=""
          placeholder="Password"
          name="password"
                onChange={(e) => setUser({ ...user, password: e.target.value })}
                value={user.password}
          minLength={6}
        />
      </div>
      <div className="submit-row" style={{ marginBottom: 8, paddingTop: 0 }}>
        <button
          className="btn btn-primary d-block box-shadow w-100"
          id="submit-id-submit"
          type="submit"
          onClick={(e)=>handleLogin(e)}
        >
          Login
        </button>
        <div className="d-flex justify-content-between">
          <div className="form-check form-check-inline" id="form-check-rememberMe">
           
           
          </div>
          <a id="forgot-password-link" href="#">
            Forgot Password?
          </a>
        </div>
      </div>
      <div
        id="login-box-footer"
        style={{ padding: "10px 20px", paddingBottom: 23, paddingTop: 18 }}
      >
         <p style={{ marginBottom: 1 }}>
          <a id="register-link" href="login">
           Login as Customer!
          </a>
        </p>
       
      </div>
    </div>
     
        </>

    )
}