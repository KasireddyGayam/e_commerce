import axios from "axios"
import { useEffect, useState } from "react"
const UpdateMerchant = () => {
    let merchant=JSON.parse(localStorage.getItem("Merchant"))
    let [name,setname]=useState("")
    let [email,setemail]=useState("")
    let [phone,setphone]=useState("")
    let [gst_number,setgst_number]=useState("")
    let [password,setpassword]=useState("")
    let [id,setid]=useState("")
    // console.log(x);
    useEffect(()=>{
        setname(merchant.name);
        setemail(merchant.email);
        setgst_number(merchant.gst_number);
        setphone(merchant.phone);
        setpassword(merchant.password);
        setid(merchant.id);
    },[])
    
    let data={name,email,phone,gst_number,password}

    let updateMerchant=(e)=>{
        e.preventDefault();
        axios.put(`http://localhost:8080/merchants`,data)
        .then((resp)=>{
            console.log(resp.data);
           alert("Merchant updated successfully");
        })
        .catch((err)=>{
            console.log(err.data);
            alert("Invalid details");
        })
    }
    return ( 
        <div className="update-merchant">
             <form onSubmit={updateMerchant}>
                <label htmlFor="">Name</label>
                <input required type="text" value={name} onChange={(e)=>{setname(e.target.value)}} placeholder="enter the Name"/>
                <label htmlFor="">Email</label>
                <input required value={email} onChange={(e)=>{setemail(e.target.value)}} type="email" placeholder="enter email"/>
                <label htmlFor="">Phone Number</label>
                <input required value={phone} onChange={(e)=>{setphone(e.target.value)}} type="tel" placeholder="enter phone number" />
                <label htmlFor="">GST Number</label>
                <input required value={gst_number} onChange={(e)=>{setgst_number(e.target.value)}} type="text" placeholder="enter GST number" />
                <label htmlFor="">Password</label>
                <input required value={password} onChange={(e)=>{setpassword(e.target.value)}} type="text" placeholder="enter password" />
                <button className="btn btn-outline-info">Submit</button>
            </form>
        </div>
     );
}
 
export default UpdateMerchant;