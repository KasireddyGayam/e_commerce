// import "../styles/UserHomePage.css"
import { Route, Routes } from "react-router-dom";
import UserNavBar from "./UserNavBar";
import ViewOrders from "./ViewOrders";
import UpdateUser from "./UpdateUser";
import ViewAddress from "./ViewAddress";
import UpdateAddress from "./AddAddress";
import AddAddress from "./AddAddress";
import ProductView  from "./ProductView"
import ReadContent from "./ReadContent";
import UserProducts from "./UserProducts";
const UserHomePage = () => {
    return ( 
        <div className="uhp">
          <UserNavBar></UserNavBar>
          {/* <UserProducts/> */}
          <Routes>
            <Route path="/" element={<UserProducts/>}/>
            <Route path="/view-address" element={<ViewAddress/>}/>
            <Route path="/add-address" element={<AddAddress/>}/>
            <Route path="/update-user" element={<UpdateUser/>}/>
            <Route path="/read-content/:id" element={<ReadContent/>}/>
          </Routes>
        </div>
     );
}
 
export default UserHomePage;