import axios from "axios";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import FavoriteIcon from '@mui/icons-material/Favorite';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { colors } from "@mui/material";
import { red } from "@mui/material/colors";
const ReadContent = () => {
   let [data, setdata] = useState("")
   let param = useParams();
   let user = JSON.parse(localStorage.getItem("User"))
   //  console.log(param.id);
   useEffect(() => {
      axios.get(`http://localhost:8080/products/find-by-id/${param.id}`)
         .then((res) => {
            console.log(res.data.body);
            setdata(res.data.body)
         })
         .catch((err) => {
            console.log(err);
         })
   }, [])

   function addToCart(e) {
      e.preventDefault()
      axios.put(`http://localhost:8080/products/add-to-cart/${user.id}/${param.id}`, data)
         .then((res) => {
            console.log(res);
            toast.success("Product added to cart")
         })
         .catch((err) => {
            console.log(err);
            toast.error("Something went wrong")
         })
   }

   function addToWishlist(e) {
      e.preventDefault()
      axios.put(`http://localhost:8080/products/add-to-wishlist/${user.id}/${param.id}`)
         .then((res) => {
            console.log(res);
            toast.success("Product added to WishList")
         })
         .catch((err) => {
            console.log(err);
            toast.error("Product already added ")
         })

   }


   return (
      <div className="read_disp">
         <FavoriteIcon id="icon" onClick={addToWishlist} fill={colors=red}></FavoriteIcon>
         <div className="image">
            <img src={data.image_url} alt="" />
         </div>
         <div className="details">
            <h3>{data.name}</h3>
            <h2>{data.brand}</h2>
            <br />
            <strike>M.R.P :{data.cost}</strike>
            <sup><sup>₹</sup></sup><span id="amount">{data.cost * .88}</span>
            <br />
            <br />
            <div className="desc">
               <p>{data.description}</p>
            </div>
            {/* <p>{data.description}</p> */}
            <button id="cart" onClick={addToCart} className="btn btn-warning">Add To Cart</button>
            <ToastContainer />
         </div>
      </div>
   );
}

export default ReadContent;