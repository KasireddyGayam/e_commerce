import { Link } from "react-router-dom";
import "../styles/LandingPage.css"
const LandingPage=()=>{
    return(
        <div className="landingPage">
            <Link to="/merchant">
                <img src="https://th.bing.com/th/id/OIP.zLd9kcUq7j9a5s-7gMMNwAHaIM?w=151&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7"></img>Merchant</Link>
            <Link to="/user">
                <img src="https://th.bing.com/th/id/OIP.7UCcUcXQG2Upl3dFEjnsLgHaJZ?w=146&h=186&c=7&r=0&o=5&dpr=1.5&pid=1.7"></img>
                User</Link>
        </div>
    );
}
export default LandingPage;
