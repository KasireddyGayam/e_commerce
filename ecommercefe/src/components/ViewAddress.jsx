import axios from "axios";
import { useEffect, useState } from "react";
// import Dropdown from 'react-bootstrap/Dropdown';
const ViewAddress = () => {
    let [address,setaddress]=useState([])
    useEffect(()=>{
        axios.get(`http://localhost:8080/addresses`)
        .then((res)=>{
            setaddress(res.data.body);
            // console.log({address});
        })
        .catch((err)=>{
            console.log(err.data);
        })
    },[])
    return ( 
        <div className="disp">
        {address.map((x) => {
            return (
                <div className="search" key={x.id}>
                    <div className="addressview">
                        
                        <div className="desc">
                            <h4 id="name">{x.buildingName} || {x.house_number}</h4>
                        </div>
                    </div>
                    </div>
                    )
        })
    }
        </div>
            );
     
}
 
export default ViewAddress;