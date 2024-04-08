import { Link, Route, Routes, useNavigate } from "react-router-dom";
import "../styles/MerchantHomePage.css"
import MerchantsNavBar from "./MerchantsNavBar";
import ProductView from "./ProductView";
import UpdateMerchant from "./UpdateMerchant";
import AddProducts from "./AddProducts";
import EditProduct from "./EditProduct";
import DeleteProduct from "./DeleteProduct";
import ReadContent from "./ReadContent";

const MerchantHomePage = () => {
   
    return ( 
        <div className="merchantHomePage">
            <MerchantsNavBar/>
            <Routes>
                <Route path="/product-view" element={<ProductView/>}/>
                <Route path="/update-merchant" element={<UpdateMerchant/>}/>
                <Route path="/add-products" element={<AddProducts/>}/>
                <Route path="/edit-product/:id" element={<EditProduct/>}/>
                <Route path="/delete-product" element={<DeleteProduct/>}/>
                <Route path="/read-content/:id" element={<ReadContent/>}/>
            </Routes>
        </div>
     );
}
 
export default MerchantHomePage;