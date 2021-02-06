import React, { Component } from "react";
import "./login.css";
class Login extends Component {
	render() {
		return (
			<div className="login-box">
				<h1>Login</h1>
				<div className="text-box">
					<input type="text" placeholder="Username" value=""></input>
				</div>
				<div className="text-box">
					<input type="password" placeholder="Password" value=""></input>
				</div>
				<input className="btn" type="button" value="Sign in" />
				<button className="btn">Login with GitHub</button>
				<button className="btn">Don't have an account? Register here</button>
			</div>
		);
	}
}

export default Login;
