import Dropdown from 'react-bootstrap/Dropdown';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import { Link } from 'react-router-dom';
const UserNavBar = () => {
    return ( 
        <nav>
           <div className="logo">
                <h1>ShoppeeQ</h1>
            </div>
            <div className="va">
                <button id="b1" className="btn btn-danger"><Link to="/user-home-page/view-address">View Address</Link></button>
            </div>

            <div className="ua">
                <button id="b1" className="btn btn-danger"><Link to="/user-home-page/add-address">Add Address</Link></button>
            </div>
            
            <Dropdown>
      <Dropdown.Toggle variant="success" id="dropdown-basic">
        <AccountCircleIcon/>Account
      </Dropdown.Toggle>

      <Dropdown.Menu>
        <Dropdown.Item href="/user-home-page/update-user">Edit Account</Dropdown.Item>
        <Dropdown.Item href="/">Log Out</Dropdown.Item>
        
      </Dropdown.Menu>
    </Dropdown>
        </nav>
     );
}
 
export default UserNavBar;