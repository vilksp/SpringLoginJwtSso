import "./register.css";
import React, { Component } from "react";

class Register extends Component {
	render() {
		return (
			<div className="login-box">
				<h1>Register</h1>
				<div className="text-box">
					<input type="text" placeholder="Username" value=""></input>
				</div>
				<div className="text-box">
					<input type="text" placeholder="Email" value=""></input>
				</div>
				<div className="text-box">
					<input type="text" placeholder="FirstName" value=""></input>
				</div>
				<div className="text-box">
					<input type="text" placeholder="LastName" value=""></input>
				</div>
				<div className="text-box">
					<input type="password" placeholder="Password" value=""></input>
				</div>
				<input className="btn" type="button" value="Sign in" />

				<button className="btn">Already have account? Login here</button>
			</div>
		);
	}
}

export default Register;
