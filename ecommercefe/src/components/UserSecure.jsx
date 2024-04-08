import { Navigate } from "react-router-dom";

const UserSecure = ({Parent}) => {
    let x=localStorage.getItem("User")
    let verifyProtect=()=>{
        if(x==null)
         return false;
        else
         return true;
    }
    return ( 
        <div>
        {verifyProtect()?<Parent/>:<Navigate to="/user"/>}
    </div>
     );
}
 
export default UserSecure;