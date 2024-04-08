import axios from "axios"
import { useEffect, useState } from "react"
import { useParams } from "react-router-dom"

const EditProduct = () => {
    let [name,setname]=useState("")
    let [brand,setbrand]=useState("")
    let [category,setcategory]=useState("")
    let [description,setdescription]=useState("")
    let [cost,setcost]=useState("")
    let [image_url,setimage]=useState("")
    let [id,setid]=useState(0)
    let param=useParams()

    useEffect(()=>{
        axios.get(`http://localhost:8080/products/find-by-id/${param.id}`)
        .then((res)=>{
            console.log(res.data.body);
            setid(res.data.body.id)
            setname(res.data.body.name)
            setbrand(res.data.body.brand)
            setcategory(res.data.body.category)
            setcost(res.data.body.cost)
            setdescription(res.data.body.description)
            setimage(res.data.body.image_url)
        })
        .catch((err)=>{
            console.log(err);
        })
    },[])

    
    let data={name,brand,category,description,cost,image_url,id}
    let updateProduct=()=>{
        // e.preventDefault()
        axios.put(`http://localhost:8080/products`,data)
        .then((res)=>{
            console.log(res.data);
            alert("Product updated")
        })
        .catch((err)=>{
            console.log(err.data);
            alert("something went wrong")
        })
    }
   
    return (  
        <div className="editProduct">
              <form action="" onSubmit={updateProduct}>
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
                <button className="btn btn-info">Edit Product</button>
    </form>
        </div>
    ); 
}
 
export default EditProduct
