import axios from "axios"
import { useEffect } from "react";
import { useState } from "react";
import "../styles/LandingPage.css"
import { Link, useNavigate, useParams } from "react-router-dom";
import Dropdown from 'react-bootstrap/Dropdown';
import EditIcon from '@mui/icons-material/Edit';
import DeleteOutlineIcon from '@mui/icons-material/DeleteOutline';
const UserProducts = () => {
    let navigate=useNavigate()
    let [item, setItem] = useState([]);
    // let { id } = useParams()
    useEffect(() => {
        axios.get(`http://localhost:8080/products`)
            .then((res) => {
                console.log((res.data.body));
                setItem((res.data.body))
                console.log(item);
            })
            .catch((err) => {
                console.log(err.data);

            })
    }, [])

    let searchByBrand=(brand)=>{
        axios.get(`http://localhost:8080/products/find-by-brand/${brand}`)
        .then((res)=>{
            console.log(res.data.body);
            setItem(res.data.body)

        })
        .catch((err)=>{
            console.log(err.data);
        })
    }

    let searchByCategory=(category)=>{
        axios.get(`http://localhost:8080/products/find-by-category/${category}`)
        .then((res)=>{
            console.log(res.data.body);
            setItem(res.data.body)

        })
        .catch((err)=>{
            console.log(err.data);
        })
    }
    
    let read=(id)=>{
        navigate(`/user-home-page/read-content/${id}`)
    }

    return (
        <div className="disp">
            {item.map((x) => {
                return (
                    <div className="search" key={x.id}>
                        <div className="brand">
                            <Dropdown>
                                <Dropdown.Toggle variant="success" id="dropdown-basic">
                                    Search
                                </Dropdown.Toggle>

                                <Dropdown.Menu>
                                    <Dropdown.Item onClick={()=>{searchByBrand(x.brand)}}>By Brand</Dropdown.Item>
                                    <Dropdown.Item onClick={()=>{searchByCategory(x.category)}}>By Category</Dropdown.Item>
                                </Dropdown.Menu>
                            </Dropdown>
                        </div>
                        <div className="productView">
                            <div className="image">
                                <div id="category">{x.category}</div>
                                <img src={x.image_url} alt="" />
                            </div>
                            <div className="desc" >
                                <h4 id="name" onClick={()=>{read(x.id)}}>{x.name} || {x.brand}</h4>
                                <span id="cost"><sup><b>₹</b></sup>{x.cost}</span>
                                <br />
                                <span id="desc">{x.description} </span>
                            </div>
                            
                        </div>
                        </div>
                        )
            })
        }
            </div>
                );
        }
 
export default UserProducts;