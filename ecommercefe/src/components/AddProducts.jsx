import axios from "axios";
import { useState } from "react";

const AddProducts = () => {
    let [name,setname]=useState("")
    let [brand,setbrand]=useState("")
    let [category,setcategory]=useState("")
    let [description,setdescription]=useState("")
    let [cost,setcost]=useState(0)
    let [image_url,setimage]=useState("")
    let data={name,brand,category,description,cost,image_url}
    let admin=JSON.parse(localStorage.getItem("Merchant"))
    let addProduct=(e)=>{
        // e.preventDefault()
        axios.post(`http://localhost:8080/products/${admin.id}`,data)
        .then((res)=>{
            console.log(res.data);
            // localStorage.setItem("Product",JSON.stringify(res.data.body))
            alert("Product added successfully")

        })
        .catch((err)=>{
            console.log(err.data);
            alert("Something went wrong")
        })
    }
    return (
        <div className="addProducts">
            <form action="" onSubmit={addProduct}>
                <label htmlFor="">Name:</label>
                <input value={name} onChange={(e)=>{setname(e.target.value)}} type="text" placeholder="Enter the name"/>
                <label htmlFor="">Brand:</label>
                <input type="text" placeholder="enter the brand" onChange={(e)=>{setbrand(e.target.value)}} value={brand}/>
                <label htmlFor="">Category:</label>
                <input type="text" placeholder="enter the category" onChange={(e)=>{setcategory(e.target.value)}} value={category} />
                <label htmlFor="">Description:</label>
                <input type="text-area" name="" id="" placeholder="enter the description" onChange={(e)=>{setdescription(e.target.value)}} value={description}/>
                <label htmlFor="">Cost:</label>
                <input type="text" placeholder="enter the cost"  onChange={(e)=>{setcost(e.target.value)}} value={cost}/>
                <label htmlFor="">Image_URL:</label>
                <input type="text" placeholder="enter the image_url" onChange={(e)=>{setimage(e.target.value)}} value={image_url}/>
                <button className="btn btn-info">Add</button>
            </form>

        </div>
    );
}

export default AddProducts;