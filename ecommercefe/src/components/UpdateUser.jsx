import axios from "axios"
import { useEffect, useState } from "react"
// import { useNavigate } from "react-router-dom"

const UpdateUser = () => {
    let [name, setname] = useState("")
    let [email, setemail] = useState("")
    let [phone, setphone] = useState("")
    let [password, setpassword] = useState("")
    let [age, setage] = useState("")
    let [gender, setgender] = useState("")
    let [id, setid] = useState(0)
    let data = { name, age, gender, email, phone, password }
    // let navigate = useNavigate();
    let user = JSON.parse(localStorage.getItem("User"))
    useEffect(() => {
        setname(user.name)
        setemail(user.email)
        setphone(user.phone)
        setpassword(user.password)
        setage(user.age)
        setgender(user.gender)
        setid(user.id)
    }, [])
    let updateUser = (e) => {
        e.preventDefault()
        axios.put(`http://localhost:8080/users`, data)
            .then((res) => {
                console.log(res.data);
                alert("user updated successfully")
            })
            .catch((err) => {
                console.log(err.data);
                alert("Invalid details")
            })
    }
    return (
        <div className="update-user">
            {/* <h1>Update user</h1> */}
            <form onSubmit={updateUser}>
                <label htmlFor="">Name</label>
                <input required type="text" value={name} onChange={(e) => { setname(e.target.value) }} placeholder="enter the Name" />
                <label htmlFor="">Email</label>
                <input required value={email} onChange={(e) => { setemail(e.target.value) }} type="email" placeholder="enter email" />
                <label htmlFor="">Phone Number</label>
                <input required value={phone} onChange={(e) => { setphone(e.target.value) }} type="tel" placeholder="enter phone number" />
                <label htmlFor="">Age</label>
                <input required value={age} onChange={(e) => { setage(e.target.value) }} type="text" placeholder="enter Age" />
                <label htmlFor="">Gender</label>
                <input type="text" onChange={(e) => { setgender(e.target.value) }} value={gender} placeholder="enter gender" />
                <label htmlFor="">Password</label>
                <input required value={password} onChange={(e) => { setpassword(e.target.value) }} type="password" placeholder="enter password" />
                <button className="btn btn-outline-info">Submit</button>
            </form>
        </div>
    );
}

export default UpdateUser;